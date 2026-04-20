import Classes.Funcionario;
import ConexaoBD.BancoSQLite;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Connection con = BancoSQLite.connection();

        if (con == null) {
            System.out.println("Erro ao conectar com o banco.");
            return;
        }

        criarTabela(con);
        inserirDados(con);

        // 3.2 Remover João
        removerFuncionario(con, "João");

        // 3.4 Aumento de 10%
        aplicarAumento(con);

        List<Funcionario> funcionarios = listarFuncionarios(con);

        // 3.3 Imprimir
        System.out.println("\n--- Funcionários ---");
        funcionarios.forEach(System.out::println);

        // 3.5 Agrupar por função
        Map<String, List<Funcionario>> agrupados =
                funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 Imprimir agrupados
        System.out.println("\n--- Agrupados por Função ---");
        agrupados.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(System.out::println);
        });

        // 3.8 Aniversários 10 e 12
        System.out.println("\n--- Aniversariantes Outubro e Dezembro ---");
        funcionarios.stream()
                .filter(f -> {
                    int mes = f.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(System.out::println);

        // 3.9 Mais velho
        Funcionario maisVelho = Collections.min(funcionarios,
                Comparator.comparing(Funcionario::getDataNascimento));

        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();

        System.out.println("\n--- Mais velho ---");
        System.out.println(maisVelho.getNome() + " | " + idade + " anos");

        // 3.10 Ordem alfabética
        System.out.println("\n--- Ordem Alfabética ---");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        // 3.11 Total salários
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        System.out.println("\nTotal salários: " + nf.format(total));

        // 3.12 Salários mínimos
        BigDecimal salarioMin = new BigDecimal("1212");

        System.out.println("\n--- Salários mínimos ---");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(salarioMin, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + ": " + qtd + "salários mínimos");
        });
    }

    // BANCO

    public static void criarTabela(Connection con) {
        String sql = """
                CREATE TABLE IF NOT EXISTS funcionario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT,
                data_nascimento TEXT,
                salario REAL,
                funcao TEXT
                )
                """;

        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inserirDados(Connection con) {

        String sql = "INSERT INTO funcionario (nome, data_nascimento, salario, funcao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            if (!listarFuncionarios(con).isEmpty()) return;

            inserir(stmt, "Maria", "2000-10-18", "2009.44", "Operador");
            inserir(stmt, "João", "1990-05-12", "2284.38", "Operador");
            inserir(stmt, "Caio", "1961-05-02", "9836.14", "Coordenador");
            inserir(stmt, "Miguel", "1988-01-14", "19119.88", "Diretor");
            inserir(stmt, "Alice", "1995-01-05", "2234.68", "Recepcionista");
            inserir(stmt, "Heitor", "1999-11-19", "1582.72", "Operador");
            inserir(stmt, "Arthur", "1993-03-31", "4071.84", "Contador");
            inserir(stmt, "Laura", "1994-07-08", "3017.45", "Gerente");
            inserir(stmt, "Heloísa", "2003-05-24", "1606.85", "Eletricista");
            inserir(stmt, "Helena", "1996-09-02", "2799.93", "Gerente");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void inserir(PreparedStatement stmt, String nome, String data, String salario, String funcao) throws SQLException {
        stmt.setString(1, nome);
        stmt.setString(2, data);
        stmt.setBigDecimal(3, new BigDecimal(salario));
        stmt.setString(4, funcao);
        stmt.executeUpdate();
    }

    public static List<Funcionario> listarFuncionarios(Connection con) {
        List<Funcionario> lista = new ArrayList<>();

        String sql = "SELECT * FROM funcionario";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Funcionario(
                        rs.getBigDecimal("salario"),
                        rs.getString("funcao"),
                        rs.getString("nome"),
                        LocalDate.parse(rs.getString("data_nascimento"))
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static void removerFuncionario(Connection con, String nome) {
        String sql = "DELETE FROM funcionario WHERE nome = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void aplicarAumento(Connection con) {
        String sql = "UPDATE funcionario SET salario = salario * 1.10";

        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}