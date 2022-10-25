package _1_DBAppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _2_GetMinionsNames {
    private static final String GET_MINIONS_NAME_AND_AGE_BY_VILLAIN_ID =
            "SELECT m.name, m.age" +
                    " FROM minions as m" +
                    " JOIN minions_villains mv on m.id = mv.minion_id" +
                    " WHERE villain_id = ?";
    private static final String GET_VILLAIN_NAME_BY_ID =
            "SELECT name" +
                    " from villains " +
                    " where id = ?";

    private static final String COLUMN_LABEL_AGE = "age";
    private static final String NO_VILLAIN_FORMAT = "No villain with ID %d exists in the database.";
    private static final String VILLAIN_FORMAT = "Villain: %s%n";
    private static final String MINION_FORMAT = "%d. %s %d%n";


    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final int villainId = new Scanner(System.in).nextInt();

        final PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);

        villainStatement.setInt(1, villainId);

        ResultSet villainSet = villainStatement.executeQuery();


        if (!villainSet.next()) {
            System.out.printf(NO_VILLAIN_FORMAT, villainId);
            connection.close();
            return;
        }


        final String villainName = villainSet.getString(Constants.COLUMN_LABEL_NAME);

        System.out.printf(VILLAIN_FORMAT, villainName);

        final PreparedStatement minionsStatement = connection.prepareStatement(GET_MINIONS_NAME_AND_AGE_BY_VILLAIN_ID);

        minionsStatement.setInt(1, villainId);

        final ResultSet minionsSet = minionsStatement.executeQuery();

        for (int index = 1; minionsSet.next(); index++) {
            final String minionsName = minionsSet.getString(Constants.COLUMN_LABEL_NAME);
            final int minionsAge = minionsSet.getInt(COLUMN_LABEL_AGE);

            System.out.printf(MINION_FORMAT, index, minionsName, minionsAge);
        }
        connection.close();
    }

}

