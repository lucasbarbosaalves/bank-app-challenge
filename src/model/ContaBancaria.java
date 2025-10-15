package model;

import java.math.BigDecimal;
import java.util.List;

import model.types.TipoConta;

public class ContaBancaria {
    private String numero;
    private String agencia;
    private Cliente cliente;
    private BigDecimal saldo;
    private BigDecimal limite;
    private TipoConta tipoConta;
    private List<Transacao> historico;

    public ContaBancaria(String numero, String agencia, Cliente cliente, BigDecimal saldo, BigDecimal limite, TipoConta tipoConta, List<Transacao> historico) {
        this.numero = numero;
        this.agencia = agencia;
        this.cliente = cliente;
        this.saldo = saldo;
        this.limite = limite;
        this.tipoConta = tipoConta;
        this.historico = historico;
    }

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

    public String getNumero() {
        return numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public List<Transacao> getHistorico() {
        return historico;
    }

    

    @Override
    public String toString() {
        return String.format("ContaBancaria[numero=%s, agencia=%s, cliente=%s, saldo=%s, limite=%s, tipoConta=%s]", numero, agencia, cliente, saldo, limite, tipoConta.getDescricao());
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setHistorico(List<Transacao> historico) {
        this.historico = historico;
    }
}