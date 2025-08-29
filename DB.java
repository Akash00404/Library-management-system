import java.sql.*;

class DB {
    private static Connection con;

    public static Connection get() throws Exception {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "Akash@123"
            );
        }
        return con;
    }
}
