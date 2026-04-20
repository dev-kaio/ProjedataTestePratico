# Teste Prático - Gestão de Funcionários

Este projeto foi desenvolvido como solução para um teste prático de programação em Java, com foco em orientação a objetos e manipulação de dados.

---

## Tecnologias utilizadas

- Java 21
- JDBC
- SQLite
- BigDecimal
- Java Time API (LocalDate)
- Streams API

---

## Funcionalidades implementadas

O sistema realiza o gerenciamento de funcionários de uma indústria, incluindo:

### Cadastro inicial de funcionários
Inserção dos dados conforme tabela fornecida no teste.

### Remoção de funcionário
Remoção do funcionário "João" da base de dados.

### Aumento salarial
Aplicação de aumento de 10% para todos os funcionários.

### Listagem de funcionários
Exibição completa com:
- Nome
- Função
- Data de nascimento formatada (dd/MM/yyyy)
- Salário formatado no padrão brasileiro

### Agrupamento por função
Organização dos funcionários em um Map, agrupados por cargo.

### Aniversariantes
Filtro de funcionários que fazem aniversário nos meses 10 e 12.

### Funcionário mais velho
Identificação do funcionário com maior idade.

### Ordenação alfabética
Lista de funcionários ordenada por nome.

### Total de salários
Cálculo da soma total dos salários.

### Equivalência em salários mínimos
Cálculo de quantos salários mínimos cada funcionário recebe (considerando salário mínimo de 1212).

---

## Banco de dados

O projeto utiliza SQLite com criação automática da tabela ao executar o sistema (optei por usar banco de dados para ir além do básico e mostrar conhecimento de persistência de dados).

---

## Como executar

1. Clonar o repositório
2. Abrir em uma IDE Java (IntelliJ, Eclipse ou NetBeans)
3. Executar a classe `Main`
4. O banco SQLite será criado automaticamente

---

## Observações

- O projeto foi estruturado utilizando boas práticas de POO
- Uso de BigDecimal para precisão financeira
- Uso de Streams para manipulação de coleções
- Código organizado em camadas simples (modelo + conexão + execução)

---

## Autor
Kaio Manfro da Silva
