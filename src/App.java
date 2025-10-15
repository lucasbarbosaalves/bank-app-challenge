import java.util.Scanner;

import model.types.TipoConta;
import service.impl.ContaBancariaService;
import service.impl.HistoricoService;
import service.impl.OperacaoBancariaService;
import utils.Menu;

public class App {

    private ContaBancariaService contaService;
    private OperacaoBancariaService operacaoService;
    private HistoricoService historicoService;
    private Scanner scanner;

    public App() {
        this.contaService = new ContaBancariaService();
        this.operacaoService = new OperacaoBancariaService();
        this.historicoService = new HistoricoService();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }


    private void exibirMenu() {
        System.out.println("=== Sistema Bancário ===");
        System.out.println("1. Cadastrar Conta");
        System.out.println("2. Depositar");
        System.out.println("3. Sacar");
        System.out.println("4. Transferir");
        System.out.println("5. Alterar Limite");
        System.out.println("6. Consultar Saldo");
        System.out.println("7. Ver Histórico");
        System.out.println("8. Exportar Historico (CSV)");
        System.out.println("9. Listar Contas");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
        System.out.print("Escolha uma opção: ");
    }

    // Scanner read

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opção inválida
        }
    }


    private void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> cadastrarConta();
                case 2 -> depositar();
                case 3 -> sacar();
                case 4 -> transferir();
                case 5 -> alterarLimite();
                case 6 -> consultarSaldo();
                case 7 -> verHistorico();
                case 8 -> exportarHistorico();
                case 9 -> listarContas();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

            if (opcao != 0) {
                Menu.pausar(scanner);
            }
        } catch (Exception e) {
            Menu.exibirMensagemErro(e.getMessage());
            Menu.pausar(scanner);
            Menu.limparTela();
        }
    }

    private void cadastrarConta() {
        System.out.println("--- Cadastro de Conta ---");
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Saldo inicial: ");
        java.math.BigDecimal saldo = new java.math.BigDecimal(scanner.nextLine());
        System.out.print("Limite: ");
        java.math.BigDecimal limite = new java.math.BigDecimal(scanner.nextLine());
        System.out.println("Tipo de conta: 1-Corrente 2-Poupança 3-Salário");
        int tipo = Integer.parseInt(scanner.nextLine());
        TipoConta tipoConta = switch (tipo) {
            case 1 -> TipoConta.CORRENTE;
            case 2 -> TipoConta.POUPANCA;
            case 3 -> TipoConta.SALARIO;
            default -> TipoConta.CORRENTE;
        };
        model.Cliente cliente = model.Cliente.newCliente(nome, cpf, telefone, email);
        var conta = contaService.criarConta(cliente, saldo, limite, tipoConta);
        System.out.println("Conta criada: " + conta);
    }

    private void depositar() throws Exception {
        System.out.println("--- Depósito ---");
        var conta = buscarContaPorInput();
        System.out.print("Valor do depósito: ");
        java.math.BigDecimal valor = new java.math.BigDecimal(scanner.nextLine());
        operacaoService.depositar(conta, valor);
        System.out.println("Depósito realizado com sucesso!");
    }

    private void sacar() throws Exception {
        System.out.println("--- Saque ---");
        var conta = buscarContaPorInput();
        System.out.print("Valor do saque: ");
        java.math.BigDecimal valor = new java.math.BigDecimal(scanner.nextLine());
        operacaoService.sacar(conta, valor);
        System.out.println("Saque realizado com sucesso!");
    }

    private void transferir() throws Exception {
        System.out.println("--- Transferência ---");
        System.out.println("Conta de origem:");
        var contaOrigem = buscarContaPorInput();
        System.out.println("Conta de destino:");
        var contaDestino = buscarContaPorInput();
        System.out.print("Valor da transferência: ");
        java.math.BigDecimal valor = new java.math.BigDecimal(scanner.nextLine());
        operacaoService.transferir(contaOrigem, contaDestino, valor);
        System.out.println("Transferência realizada com sucesso!");
    }

    private void alterarLimite() throws Exception {
        System.out.println("--- Alterar Limite ---");
        var conta = buscarContaPorInput();
        System.out.print("Novo limite: ");
        java.math.BigDecimal novoLimite = new java.math.BigDecimal(scanner.nextLine());
        operacaoService.alterarLimite(conta, novoLimite);
        System.out.println("Limite alterado com sucesso!");
    }

    private void consultarSaldo() throws Exception {
        System.out.println("--- Consultar Saldo ---");
        var conta = buscarContaPorInput();
        System.out.println("Saldo disponível: R$ " + conta.getSaldoDisponivel());
    }

    private void verHistorico() throws Exception {
        System.out.println("--- Histórico de Transações ---");
        var conta = buscarContaPorInput();
        var historico = conta.getHistorico();
        if (historico.isEmpty()) {
            System.out.println("Nenhuma transação encontrada.");
        } else {
            historico.forEach(System.out::println);
        }
    }

    private void exportarHistorico() throws Exception {
        System.out.println("--- Exportar Histórico (CSV) ---");
        var conta = buscarContaPorInput();
        System.out.print("Caminho do arquivo CSV: ");
        String caminho = scanner.nextLine();
        historicoService.exportarHistorico(conta, caminho);
        System.out.println("Histórico exportado com sucesso!");
    }

    private void listarContas() {
        System.out.println("--- Lista de Contas ---");
        var contas = contaService.listarContas();
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            contas.forEach(System.out::println);
        }
    }

    private model.ContaBancaria buscarContaPorInput() throws Exception {
        System.out.print("Agência: ");
        String agencia = scanner.nextLine();
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();
        return contaService.buscarConta(numero, agencia);
    }


    public static void main(String[] args) throws Exception {
        App app = new App();
        app.iniciar();
    }
}
