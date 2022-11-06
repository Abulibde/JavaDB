package _1_DBAppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class _7_PrintAllMinionNames {

        private static final String SELECT_MINION_NAMES = "SELECT `name` FROM `minions`;";
        private static final String MINION_NAME_COLUMN = "name";

        public static void main(String[] args) throws SQLException {

            Connection connection = Utils.getSQLConnection();

            PreparedStatement selectMinionsStatement = connection.prepareStatement(SELECT_MINION_NAMES);
            ResultSet minionsNamesResult = selectMinionsStatement.executeQuery();

            ArrayDeque<String> minionsNames = new ArrayDeque<>();

            while(minionsNamesResult.next()) {
                minionsNames.offer(minionsNamesResult.getString(MINION_NAME_COLUMN));
            }

            while(minionsNames.size() > 2) {
                System.out.println(minionsNames.pollFirst());
                System.out.println(minionsNames.pollLast());
            }

            while(!minionsNames.isEmpty()) {
                System.out.println(minionsNames.pop());
            }

            connection.close();
    }
}
