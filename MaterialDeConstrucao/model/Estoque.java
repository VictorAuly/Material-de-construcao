package com.victor.poo.model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Estoque {

    private ArrayList<Produto> listaProdutos = new ArrayList<>();
    private ArrayList<Cupom> listaCupons = new ArrayList<>();
    private double totalVendas = 0.0;

    public double getTotalVendas() {
        return totalVendas;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    public ArrayList<Cupom> getListaCupons() {
        return listaCupons;
    }

    public void adicionarCupom(String nomeProduto, int quantidade, double valorTotal) {
        Cupom cupom = new Cupom(nomeProduto, quantidade, valorTotal);
        listaCupons.add(cupom);
    }

    public boolean adicionarQuantidadeAoProduto(String nomeProduto, int quantidade) {
        for (Produto produto : listaProdutos) {
            if (produto.getNomeProduto().equals(nomeProduto)) {
                produto.setQuantidade(produto.getQuantidade() + quantidade);
                return true;
            }
        }
        return false;
    }

    public void informarQuantidadeEstoque() {

        if (listaProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String[] nomesProdutos = listaProdutos.stream()//vai crir um steam da lista de produto
                .map(Produto::getNomeProduto)//vai mapear pelo nome de cada produtos
                .toArray(String[]::new);//retorna um array fixo do tipo strind

        String produtoSelecionado = (String) JOptionPane.showInputDialog(null,
                "Selecione um produto para adicionar quantidade:",
                "Selecionar Produto", JOptionPane.PLAIN_MESSAGE, null, nomesProdutos, nomesProdutos[0]);

        if (produtoSelecionado != null) {
            String quantidadeStr = JOptionPane.showInputDialog("Informe a quantidade a ser adicionada:");
            try {
                int quantidade = Integer.parseInt(quantidadeStr);
                boolean sucesso = adicionarQuantidadeAoProduto(produtoSelecionado, quantidade);
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Quantidade adicionada com sucesso!", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void venderProduto() {
        String listaDeProdutos = listarProdutosComEstoque();
        if (listaDeProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto disponível para venda.");
            return;
        }

        ArrayList<String> nomesProdutos = new ArrayList<>();
        for (Produto produto : listaProdutos) {
            if (produto.getQuantidade() > 0) {
                nomesProdutos.add(produto.getNomeProduto());
            }
        }

        if (nomesProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os produtos estão com estoque zero. Não é possível realizar vendas.");
            return;
        }

        String produtoSelecionado = (String) JOptionPane.showInputDialog(
                null,
                "Selecione o produto que deseja vender:",
                "Venda de Produto",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomesProdutos.toArray(),
                nomesProdutos.get(0));

        if (nomesProdutos != null) {
            String quantidadeVenda = JOptionPane.showInputDialog("Digite a quantidade que deseja vender:");

            try {
                int quantidade = Integer.parseInt(quantidadeVenda);
                Produto produto = buscarProdutoPorNome(produtoSelecionado);
                if (produto != null && reduzirQuantidadeProduto(produtoSelecionado, quantidade)) {
                    double valorTotal = quantidade * produto.getPreco();
                    adicionarCupom(produtoSelecionado, quantidade, valorTotal);
                    JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Quantidade insuficiente ou produto não encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantidade inválida.");
            }
        }
    }

    private Produto buscarProdutoPorNome(String nomeProduto) {
        for (Produto produto : listaProdutos) {
            if (produto.getNomeProduto().equals(nomeProduto)) {
                return produto;
            }
        }
        return null;
    }

    public boolean reduzirQuantidadeProduto(String nomeProduto, int quantidade) {
        for (Produto produto : listaProdutos) {
            if (produto.getNomeProduto().equals(nomeProduto)) {
                if (produto.getQuantidade() >= quantidade) {
                    produto.setQuantidade(produto.getQuantidade() - quantidade);
                    return true;
                }
                break; //qtd insuficiente
            }
        }
        return false; //Produto nao encontrado
    }

    private String listarProdutosComEstoque() {
        StringBuilder lista = new StringBuilder();
        for (Produto produto : listaProdutos) {
            String status = produto.getQuantidade() > 0 ? "" : " (Esgotado)";
            lista.append(produto.getNomeProduto()).append(" - R$").append(produto.getPreco()).append(status)
                    .append("\n");
        }
        return lista.toString();
    }

    public double calcularTotalVendas() {
        double totalVendas = 0;
        for (Cupom cupom : listaCupons) {
            totalVendas += cupom.getValorTotal();
        }
        return totalVendas;
    }

}
