package service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import exceptions.ContaNaoEncontradaException;
import model.Cliente;
import model.ContaBancaria;
import model.types.TipoConta;

public class ContaBancariaService {
    private Map<String, ContaBancaria> contas;
    private Random random;

    public ContaBancariaService() {
        this.contas = new HashMap<>();
        this.random = new Random();
    }

    public ContaBancaria criarConta(Cliente client, BigDecimal saldoInicial, BigDecimal limite, TipoConta tipoConta) {
        String numeroConta = gerarNumeroConta();
        String agencia = "0001"; // Agência padrão
        
        ContaBancaria conta = ContaBancaria.newContaBancaria(numeroConta, agencia, client, saldoInicial, limite, tipoConta, new java.util.ArrayList<>());
        
        contas.put(conta.getChaveUnica(), conta);
        return conta;
    }

    public ContaBancaria buscarConta(String numero, String agencia) throws ContaNaoEncontradaException {
        String chave = agencia + "-" + numero;
        final var conta = contas.get(chave);

        if (conta == null) {
            throw new ContaNaoEncontradaException("Conta não encontrada: " + chave);
        }
        return conta;
    }

    public List<ContaBancaria> listarContas() {
        return List.copyOf(contas.values());
    }

    private String gerarNumeroConta() {
        int numero = random.nextInt(1000000);
        return String.format("%06d", numero);
    }
}
