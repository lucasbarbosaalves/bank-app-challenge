package service;

import model.ContaBancaria;

public interface Historico {
    void exportarHistorico(ContaBancaria conta, String caminhoArquivo) throws Exception;
}
