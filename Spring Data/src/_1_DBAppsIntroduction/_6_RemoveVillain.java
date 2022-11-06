package _1_DBAppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _6_RemoveVillain {
    private static final String GET_VILLAIN_ID = "select v.name from villains as v where id = ?";
    private static final String GET_MINION_COUNT_BY_VILLAIN_ID =
            "select COUNT(mv.minion_id) minion_count from minions_villains as mv where mv.villain_id =?";
    private static final String DELETE_MINIONS_VILLAINS_BY_VILLAIN_ID =
            "delete from minions_villains as mv where mv.villain_id = ?";
    private static final String DELETE_VILLAIN_BY_ID = "delete from villains as v where id = ?";
    private static final String NO_SUCH_VILLAIN_MESSAGE = "No such villain was found";
    private static final String COLUMN_LABEL_MINIONS_COUNT = "minion_count";

    private static final String DELETED_VILLAIN_FORMAT = "%s was deleted%n";
    private static final String DELETED_COUNT_OF_MINIONS = "%d minions released";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        final int villainId = new Scanner(System.in).nextInt();

        final PreparedStatement getVillain = connection.prepareStatement(GET_VILLAIN_ID);
        getVillain.setInt(1, villainId);

        final ResultSet villainSet = getVillain.executeQuery();

        if (!villainSet.next()) {
            System.out.println(NO_SUCH_VILLAIN_MESSAGE);

            connection.close();
            return;
        }

        final String villainName = villainSet.getString(Constants.COLUMN_LABEL_NAME);

        final PreparedStatement getAllMinions = connection.prepareStatement(GET_MINION_COUNT_BY_VILLAIN_ID);
        getAllMinions.setInt(1, villainId);

        final ResultSet countOfMinionsSet = getAllMinions.executeQuery();
        countOfMinionsSet.next();

        final int countDeletedMinions = countOfMinionsSet.getInt(COLUMN_LABEL_MINIONS_COUNT);

        connection.setAutoCommit(false);
        try (PreparedStatement deleteMinionsStatement = connection.prepareStatement(DELETE_MINIONS_VILLAINS_BY_VILLAIN_ID);
             PreparedStatement deleteVillainStatement = connection.prepareStatement(DELETE_VILLAIN_BY_ID)) {

            deleteMinionsStatement.setInt(1, villainId);
            deleteMinionsStatement.executeUpdate();

            deleteVillainStatement.setInt(1, villainId);
            deleteVillainStatement.executeUpdate();

            connection.commit();
            System.out.printf(DELETED_VILLAIN_FORMAT, villainName);
            System.out.printf(DELETED_COUNT_OF_MINIONS, countDeletedMinions);

        } catch (SQLException e) {
            e.printStackTrace();

            connection.rollback();
        }

        connection.close();
    }
}
