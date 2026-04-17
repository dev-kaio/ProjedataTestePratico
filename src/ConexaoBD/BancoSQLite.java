package ConexaoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoSQLite {
    private static final String db = "jdbc:sqlite:funcionarios.db";

    public static Connection connection() {
        try {
            Connection con = DriverManager.getConnection(db);
            System.out.println("Conexão estabelecida com o banco de dados.");
            return con;
        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
            return null;
        }
    }
}
