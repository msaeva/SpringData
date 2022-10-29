package jdbcexcercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrintAllMinionNames {
    private static final String GET_ALL_MINIONS_NAMES = "select name from minions";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();

        PreparedStatement allMinionsNamesStatement = connection.prepareStatement(GET_ALL_MINIONS_NAMES);

        ResultSet resultSet = allMinionsNamesStatement.executeQuery();

        ArrayList<String> minionsNames = new ArrayList<>();


        while (resultSet.next()) {
            minionsNames.add(resultSet.getString(Constants.COLUMN_LABEL_NAME));
        }

        for (int i = 0; i < minionsNames.size() / 2; i++) {
            if (minionsNames.size() % 2 != 0 && i == minionsNames.size() / 2) {
                System.out.println(minionsNames.get(i));
                break;
            }
            System.out.println(minionsNames.get(i));
            System.out.println(minionsNames.get(minionsNames.size() - 1 - i));
        }
    }
}

