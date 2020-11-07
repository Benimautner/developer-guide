import java.sql.*;
import java.util.Map;

public class MysqlUtil {

    private static MysqlUtil instance = null;
    private Connection connection = null;

    private static String host = "";
    private static int port = 3306;
    private static String database = "";
    private static String user = "";
    private static String password = "";

    private MysqlUtil() {
        instance = this;
        openConnection();
        if (isConnected()) {
            testConnection();
        }
    }

    public static MysqlUtil getInstance() {
        if (instance == null) {
            new MysqlUtil();
        }
        return instance;
    }

    public static void disconnect() {
        if (instance != null) {
            instance.closeConnection();
            instance = null;
        }
    }

    // Set mySQL host data
    public static void setData(String host, int port, String database, String user, String password) {
        MysqlUtil.host = host;
        MysqlUtil.port = port;
        MysqlUtil.database = database;
        MysqlUtil.user = user;
        MysqlUtil.password = password;
    }

    private void openConnection() {
        if (!host.isEmpty() && !database.isEmpty()) {
            try {
                if (connection != null && !connection.isClosed()) {
                    // Connection already attemted
                    return;
                }
                synchronized (this) {
                    if (connection != null && !connection.isClosed()) {
                        return;
                    }
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?connectTimeout=3000&autoReconnect=true&useSSL=false", user, password);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                connection = null;
            }
        }
    }

    // Shows if connection is present
    public boolean isConnected() {
        return (connection != null);
    }

    // Closes the mySQL Connection
    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
        }
        connection = null;
    }

    // Tests the connection
    private void testConnection() {
        try {
            Statement statement = connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            closeConnection();
        }
    }

    public ResultSet executeQuery(String query) {
        return executeQuery(query, null);
    }

    public ResultSet executeQuery(String query, Map<Integer, String> parameters) {
        if (isConnected()) {
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                if (parameters != null && parameters.size() > 0) {
                    for (Map.Entry<Integer, String> param : parameters.entrySet()) {
                        statement.setString(param.getKey(), param.getValue());
                    }
                }
                if (query.toLowerCase().startsWith("update") || query.toLowerCase().startsWith("insert") || query.toLowerCase().startsWith("delete")) {
                    statement.execute();
                    return null;
                } else {
                    return statement.executeQuery();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    public int executeInsertWithReturnNewID(String query, Map<Integer, String> parameters, String idField) {
        if (isConnected()) {
            try {
                String[] generatedColumns = {idField};
                PreparedStatement statement = connection.prepareStatement(query, generatedColumns);
                if (parameters != null && parameters.size() > 0) {
                    for (Map.Entry<Integer, String> param : parameters.entrySet()) {
                        statement.setString(param.getKey(), param.getValue());
                    }
                }
                statement.execute();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return 0;
        } else {
            return 0;
        }
    }
}