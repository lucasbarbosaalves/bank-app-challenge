package model;

public record Cliente(String nome, String cpf, String telefone, String email) {

    public static Cliente newCliente(String nome, String cpf, String telefone, String email) {
        return new Cliente(nome, cpf, telefone, email);
    }

    public String toString() {
        return String.format("Cliente[nome=%s, cpf=%s, telefone=%s, email=%s]", nome, cpf, telefone, email);
    }
}