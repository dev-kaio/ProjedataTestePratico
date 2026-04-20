package Classes;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(BigDecimal salario, String funcao, String nome, LocalDate dataNascimento) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        nf.setMinimumIntegerDigits(2);
        nf.setMinimumFractionDigits(2);
        return getNome() + " | " + getFuncao() + " | " +
                getDataNascimento().format(formatter) + " | " +
                nf.format(salario);
    }
}
