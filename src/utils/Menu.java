package utils;

import java.util.Scanner;

public class Menu {
    
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pausar(Scanner scanner) {
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    public static void exibirCabecalho(String titulo) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(centralizarTexto(titulo, 50));
        System.out.println("=".repeat(50));
    }

    public static void exibirMensagemSucesso(String mensagem) {
        System.out.println("\n[SUCCESS] " + mensagem);
    }

    public static void exibirMensagemErro(String mensagem) {
        System.out.println("\n[ERROR] " + mensagem);
    }

    private static String centralizarTexto(String texto, int largura) {
        int espacos = largura - texto.length();
        int espacosEsquerda = espacos / 2;
        int espacosDireita = espacos - espacosEsquerda;
        return " ".repeat(espacosEsquerda) + texto + " ".repeat(espacosDireita);
    }
}
