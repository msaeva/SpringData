package jdbcexcercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChangeTownNameCasing {
    private static final String UPDATE_TOWN_NAMES = "update towns set name = upper(name) where country = ?";
    private static final String GET_ALL_TOWN_NAMES_BY_COUNTRY_NAME = "select t.name from towns as t where country = ?";


    private static final String NO_TOWN_AFFECTED_MESSAGE = "No town names were affected.";
    private static final String COUNT_OF_AFFECTED_TOWNS_FORMAT = "%d town names were affected.%n";


    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final String townName = new Scanner(System.in).nextLine();

        final PreparedStatement statement = connection.prepareStatement(UPDATE_TOWN_NAMES);
        statement.setString(1, townName);
        final int updateCount = statement.executeUpdate();

        if (updateCount == 0) {
            System.out.println(NO_TOWN_AFFECTED_MESSAGE);
            connection.close();
            return;
        }
        System.out.printf(COUNT_OF_AFFECTED_TOWNS_FORMAT, updateCount);

        final PreparedStatement selectAllTowns = connection.prepareStatement(GET_ALL_TOWN_NAMES_BY_COUNTRY_NAME);

        selectAllTowns.setString(1, townName);
        ResultSet allTownsResult = selectAllTowns.executeQuery();

        ArrayList<String> towns = new ArrayList<>();

        while (allTownsResult.next()) {
            towns.add(allTownsResult.getString(Constants.COLUMN_LABEL_NAME));
        }

        System.out.println(towns);

    }
}
