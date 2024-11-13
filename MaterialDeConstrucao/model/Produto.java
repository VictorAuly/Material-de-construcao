package com.victor.poo.model;

import com.victor.poo.view.EntradaSaida;

public class Produto {
    private String codigoProduto;
    private Double preco;
    private String nomeProduto;
    private int quantidade;

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public static Produto cadastrarProduto() {
        Produto produto = new Produto();
        produto.setCodigoProduto(EntradaSaida.solicitarCodigoDoProduto());
        produto.setPreco(EntradaSaida.solicitaPreco());
        produto.setNomeProduto(EntradaSaida.solicitarNomeProduto());
        produto.setQuantidade(0);
        return produto;
    }
}
