package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.types.TipoTransacao;

public class Transacao {
    private static long contadorId = 1;
    private long id;
    private LocalDateTime dataHora;
    private TipoTransacao tipo;
    private BigDecimal valor;
    private String descricao;
    private String contaOrigem;
    private String contaDestino;

    public Transacao(TipoTransacao tipo, BigDecimal valor, String descricao) {
        this.id = contadorId++;
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }
    
    public Transacao(TipoTransacao tipo, BigDecimal valor, String descricao, String contaOrigem, String contaDestino) {
        this(tipo, valor, descricao);
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
    }

    public String toCSV() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%d,%s,%s,%.2f,%s,%s,%s", id, dataHora.format(fmt), tipo.getDescricao(), valor, descricao, contaOrigem, contaDestino);
    }

    public static long getContadorId() {
        return contadorId;
    }

    public static void setContadorId(long contadorId) {
        Transacao.contadorId = contadorId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s - R$ %.2f - %s",
        dataHora.format(fmt), tipo.getDescricao(), valor, descricao);
    }
}
