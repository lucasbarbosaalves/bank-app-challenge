package service.impl;

import java.io.FileWriter;
import java.io.PrintWriter;

import model.ContaBancaria;
import model.Transacao;
import service.Historico;

public class HistoricoService implements Historico {

    @Override
    public void exportarHistorico(ContaBancaria conta, String caminhoArquivo) throws Exception {
        try (PrintWriter wirter = new PrintWriter(new FileWriter(caminhoArquivo))) {
            wirter.println("ID;Data/Hora;Tipo;Valor;Descrição;Conta Origem;Conta Destino");
            for (Transacao transacao : conta.getHistorico()) {
                wirter.println(transacao.toCSV());
            }
        }
    }
}
