package model;

import java.math.BigDecimal;
import java.util.List;

import model.types.TipoConta;

public record ContaBancaria(String numero, String agencia, Cliente cliente, BigDecimal saldo, BigDecimal limite, TipoConta tipoConta, List<Transacao> historico) {
    public static ContaBancaria newContaBancaria(String numero, String agencia, Cliente cliente, BigDecimal saldo, BigDecimal limite, TipoConta tipoConta, List<Transacao> historico) {
        return new ContaBancaria(numero, agencia, cliente, saldo, limite, tipoConta, historico);
    }

    public BigDecimal getSaldoDisponivel() {
        return saldo.add(limite);
    }

    public void adicionarTransacao(Transacao transacao) {
        historico.add(transacao);
    }

    public String getChaveUnica() {
        return numero + "-" + agencia;
    }

    public String toString() {
        return String.format("ContaBancaria[numero=%s, agencia=%s, cliente=%s, saldo=%s, limite=%s, tipoConta=%s]", numero, agencia, cliente, saldo, limite, tipoConta.getDescricao());
    }
}
