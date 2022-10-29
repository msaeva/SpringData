package jdbcexcercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    private static final String CALL_PROCEDURE = "CALL usp_get_older(?)";
    private static final String GET_MINION_NAME_AND_AGE_BY_ID = "select name, age from minions where id = ?";

    public static void main(String[] args) throws SQLException {

        int minionId = new Scanner(System.in).nextInt();

        Connection connection = Utils.getSQLConnection();

        PreparedStatement callProcedureStatement = connection.prepareStatement(CALL_PROCEDURE);
        callProcedureStatement.setInt(1, minionId);
        callProcedureStatement.executeUpdate();

        PreparedStatement minionByIdStatement = connection.prepareStatement(GET_MINION_NAME_AND_AGE_BY_ID);
        minionByIdStatement.setInt(1, minionId);
        ResultSet resultSet = minionByIdStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println(resultSet.getString(Constants.COLUMN_LABEL_NAME) + " " +
                    resultSet.getInt(Constants.COLUMN_LABEL_AGE));
        }
        connection.close();
    }
}
