import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = new Cliente();
        int op;
        int aux = 0;
        
        do {
            LimparConsole.limparConsole();
            if (aux > 0)
                System.out.println("Por favor, não deixe o nome vazio!");
            System.out.print("Digite o nome do títular: ");
            cliente.setNome(sc.nextLine());
            aux += 1;
        }while(cliente.getNome().equals(""));
        
        Conta contaCorrente = new ContaCorrente(cliente);
        Conta contaPoupanca = new ContaPoupanca(cliente);
        
        do {
            LimparConsole.limparConsole();
            System.out.println("O que você deseja fazer? (1 - Depositar | 2 - Sacar | 3 - Transferir | 4 - Imprimir Extrato)");
            op = sc.nextInt();
            if (op == 4) {
                contaCorrente.imprimirExtrato();
                contaPoupanca.imprimirExtrato();
            } else if (op == 1 || op == 2 || op == 3) {
                System.out.print("Informe o valor: ");
                double valor = sc.nextDouble();
                
                switch (op) {
                    case 1:
                        try {
                            contaCorrente.depositar(valor);
                        } catch (ValorInvalidoException e) {
                            System.out.println("Saldo insuficiente. Operação não realizada!");
                        }
                        break;
                    case 2:
                        try {
                            contaCorrente.sacar(valor);
                        } catch (SaldoInsuficienteException e) {
                            System.out.println("Valor inválido. Operação não realizada!");
                        }
                        break;
                    case 3:
                        try {
                            contaCorrente.transferir(valor, contaPoupanca);
                        } catch (SaldoInsuficienteException e) {
                            System.out.println("Saldo insuficiente. Operação não realizada!");
                        }
                        break;
                }
            } else {
                System.out.println("Por favor, digite novamente.");
            }

            do {
                System.out.println("Deseja realizar mais alguma operação? (1 - Sim | 2 - Não)");
                op = sc.nextInt();
            } while (op != 1 && op != 2);
        } while (op == 1);

        LimparConsole.limparConsole();
        System.out.println("Programa encerrado. Volte sempre!");
        
        sc.close();
    }
}
