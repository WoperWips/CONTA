public class Banco {
    private double saldo; // Saldo da conta
    private int contadorConsultas; // Contador de consultas

    // Construtor da classe
    public Banco(double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo.");
        }
        this.saldo = saldoInicial; // Inicializa o saldo
        this.contadorConsultas = 0; // Inicializa o contador de consultas
    }

    // Método para depósito
    public void deposito(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }
        double taxa = valor * 0.01; // Taxa de 1%
        saldo += (valor - taxa); // Adiciona o valor menos a taxa
    }

    // Método para saque
    public void saque(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        double taxa = valor * 0.005; // Taxa de 0,5%
        if (saldo < (valor + taxa)) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque.");
        }
        saldo -= (valor + taxa); // Deduz o valor do saque mais a taxa
    }

    // Método para consultar saldo
    public double consultaSaldo() {
        contadorConsultas++; // Incrementa o contador de consultas
        if (contadorConsultas % 5 == 0) {
            saldo -= 0.10; // Deduz 0,10 a cada 5 consultas
        }
        return saldo; // Retorna o saldo
    }

    // Método para obter o saldo (getter)
    public double getSaldo() {
        return saldo; // Retorna o saldo atual
    }

    public static void main(String[] args) {
        try {
            Banco conta = new Banco(1000.0); // Cria uma conta com saldo inicial

            conta.deposito(200.0); // Deposita 200
            System.out.println("Saldo após depósito: " + conta.getSaldo());

            conta.saque(50.0); // Saca 50
            System.out.println("Saldo após saque: " + conta.getSaldo());

            // Faz 5 consultas para verificar a taxa de consulta
            for (int i = 0; i < 5; i++) {
                conta.consultaSaldo();
            }
            System.out.println("Saldo após 5 consultas: " + conta.getSaldo());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Imprime mensagem de erro se ocorrer uma exceção
        }
    }
}
