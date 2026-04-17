import ConexaoBD.BancoSQLite;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Connection connection = BancoSQLite.connection();
        if (connection != null) {
            System.out.println("Conexão estabelecida com sucesso!");
        } else {
            System.out.println("Falha ao estabelecer conexão.");
        }
    }
}