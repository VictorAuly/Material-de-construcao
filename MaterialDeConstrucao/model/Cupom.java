package com.victor.poo.model;

import java.util.Date;

public class Cupom {

    private String nomeProduto;
    private int quantidade;
    private double valorTotal;
    private Date data;

    public Date getData() {
        return data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Cupom(String nomeProduto, int quantidade, double valorTotal) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.data = new Date();
    }

    @Override
    public String toString() {
        return String.format("Data: %s\nProduto: %s\nQuantidade: %d\nValor total R$: %.2f\n",
                data, nomeProduto, quantidade, valorTotal);
    }

}
