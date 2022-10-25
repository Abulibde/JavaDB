package _1_DBAppsIntroduction;

import java.sql.*;
import java.util.Properties;

public class _1_GetVilliansNames {
    private static final String GET_VILLAINS_NAMES =
            "SELECT v.name, count(distinct mv.minion_id) AS minions_count" +
                    " FROM villains v" +
                    " join minions_villains AS mv on v.id = mv.villain_id" +
                    " group by mv.villain_id" +
                    " having minions_count > ?" +
                    " order by minions_count";

    private static final String COLUMN_LABEL_NAME = "name";
    private static final String COLUMN_LABEL_MINIONS_COUNT = "minions_count";
    private static final String PRINT_FORMAT = "%s %d";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_NAMES);

        statement.setInt(1, 15);

        final ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            final String villainsName = resultSet.getString(COLUMN_LABEL_NAME);
            final int minionsCount = resultSet.getInt(COLUMN_LABEL_MINIONS_COUNT);

            System.out.printf(PRINT_FORMAT, villainsName, minionsCount);
        }
        connection.close();
    }
}
