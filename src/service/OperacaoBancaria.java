package service;

import java.math.BigDecimal;

import model.ContaBancaria;

public interface OperacaoBancaria {
    void depositar(ContaBancaria conta, BigDecimal valor) throws Exception;
    void sacar(ContaBancaria conta, BigDecimal valor) throws Exception;
    void transferir(ContaBancaria contaOrigem, ContaBancaria contaDestino, BigDecimal valor) throws Exception;
    void alterarLimite(ContaBancaria conta, BigDecimal novoLimite) throws Exception;
}
