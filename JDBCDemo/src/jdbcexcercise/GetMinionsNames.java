package jdbcexcercise;

import java.sql.*;
import java.util.Scanner;

public class GetMinionsNames {
    private static final String GET_MINIONS_NAME_AND_AGE_BY_VILLAIN_ID = "select m.name, m.age" +
            " from minions as m" +
            " join minions_villains mv on m.id = mv.minion_id" +
            " where mv.villain_id = ?";

    private static final String GET_VILLAIN_NAME_BY_ID = "select v.name from villains as v where id = ?";

    private static final String NO_VILLAIN_FORMAT = "No villain with ID %d exists in the database.";
    private static final String VILLAIN_FORMAT = "Villain: %s%n";
    private static final String MINION_FORMAT = "%d. %s %d%n";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        int villainID = new Scanner(System.in).nextInt();

        final PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);

        villainStatement.setInt(1, villainID);

        final ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()) {
            System.out.printf(NO_VILLAIN_FORMAT, villainID);
            connection.close();
            return;
        }

        final String villainName = villainSet.getString(Constants.COLUMN_LABEL_NAME);

        System.out.printf(VILLAIN_FORMAT, villainName);

        final PreparedStatement minionsStatement = connection.prepareStatement(GET_MINIONS_NAME_AND_AGE_BY_VILLAIN_ID);

        minionsStatement.setInt(1, villainID);

        final ResultSet minionsSet = minionsStatement.executeQuery();

        for (int index = 1; minionsSet.next(); index++) {
            final String minionName = minionsSet.getString(Constants.COLUMN_LABEL_NAME);
            final int minionAge = minionsSet.getInt(Constants.COLUMN_LABEL_AGE);

            System.out.printf(MINION_FORMAT, index, minionName, minionAge);
        }
        connection.close();
    }
}

