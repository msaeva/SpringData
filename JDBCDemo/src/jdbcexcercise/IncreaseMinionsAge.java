package jdbcexcercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge {
    private static final String UPDATE_MINION_AGE_BY_ID = "update minions as m" +
            " set m.age = m.age + 1, name = lower(name)" +
            " where id = ?";
    private static final String GET_ALL_MINIONS = "select name, age from minions";

    public static void main(String[] args) throws SQLException {

        int[] minionsIds = Arrays.stream(new Scanner(System.in).nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Connection connection = Utils.getSQLConnection();

        PreparedStatement statement = null;

        for (int id : minionsIds) {
            statement = connection.prepareStatement(UPDATE_MINION_AGE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        }

        PreparedStatement allMinionsStatement = connection.prepareStatement(GET_ALL_MINIONS);
        ResultSet resultSet = allMinionsStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString(Constants.COLUMN_LABEL_NAME);
            int age = resultSet.getInt(Constants.COLUMN_LABEL_AGE);

            System.out.println(name + " " + age);
        }
        connection.close();
    }
}
