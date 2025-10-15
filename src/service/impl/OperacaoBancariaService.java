package service.impl;

import java.math.BigDecimal;

import exceptions.ValorInvalidoException;
import model.ContaBancaria;
import model.Transacao;
import model.types.TipoTransacao;
import service.OperacaoBancaria;

public class OperacaoBancariaService implements OperacaoBancaria {

    @Override
    public void alterarLimite(ContaBancaria conta, BigDecimal novoLimite) throws Exception {
        if (novoLimite.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorInvalidoException("O limite não pode ser negativo.");
            
        }
        BigDecimal limiteAnterior = conta.getLimite();
        conta.setLimite(novoLimite);

        Transacao transacao = new Transacao(TipoTransacao.ALTERACAO_LIMITE, novoLimite, String.format("Limite alterado de R$ %.2f para R$ %.2f", limiteAnterior, novoLimite));
        conta.adicionarTransacao(transacao);
    }

    @Override
    public void depositar(ContaBancaria conta, BigDecimal valor) throws Exception {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor do depósito deve ser maior que zero.");
        }
        conta.setSaldo(conta.getSaldo().add(valor));

        Transacao transacao = new Transacao(TipoTransacao.DEPOSITO, valor, "Depósito realizado");
        conta.adicionarTransacao(transacao);
        
    }

    @Override
    public void sacar(ContaBancaria conta, BigDecimal valor) throws Exception {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor do saque deve ser maior que zero.");
        }
        if (valor.compareTo(conta.getSaldoDisponivel()) > 0) {
            throw new ValorInvalidoException("Saldo insuficiente para o saque.");
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));

        Transacao transacao = new Transacao(TipoTransacao.SAQUE, valor, "Saque realizado");
        conta.adicionarTransacao(transacao);
    }

    @Override
    public void transferir(ContaBancaria contaOrigem, ContaBancaria contaDestino, BigDecimal valor) throws Exception {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor da transferência deve ser maior que zero.");
        }
        if (valor.compareTo(contaOrigem.getSaldoDisponivel()) > 0) {
            throw new ValorInvalidoException("Saldo insuficiente para a transferência.");
        }
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));

        Transacao transacaoOrigem = new Transacao(TipoTransacao.TRANSFERENCIA_ENVIADA, valor, String.format("Transferência enviada para %s", contaDestino.getChaveUnica()));
        contaOrigem.adicionarTransacao(transacaoOrigem);

        Transacao transacaoDestino = new Transacao(TipoTransacao.TRANSFERENCIA_RECEBIDA, valor, String.format("Transferência recebida de %s", contaOrigem.getChaveUnica()));
        contaDestino.adicionarTransacao(transacaoDestino);
    }
}
