package com.victor.poo.view;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.victor.poo.model.Cupom;
import com.victor.poo.model.Produto;

public class EntradaSaida {
    public static void exibiMsgEncerraPrograma() {
        JOptionPane.showMessageDialog(null, "O programa será encerrado!");
    }

    public static int solicitarOperacao() {
        String[] opcoes = { "Cadastrar Produto", "Visualizar quantidade e produtos cadastrados",
                "Informar a quantidade de produtos no estoque",
                "Vender produto", "Visualizar todos os cupons",
                "Valor total vendido", "Sair do programa" };
        JComboBox<String> boxMenu = new JComboBox<String>(opcoes);
        boxMenu.setSelectedIndex(0);

        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, boxMenu,
                    "Bem vindo a loja de materias de construção!", JOptionPane.OK_CANCEL_OPTION);
            if (resultado != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null,
                        "Você deve selecionar uma das opções para continuar.",
                        "Seleção Obrigatória", JOptionPane.WARNING_MESSAGE);
            }
        } while (resultado != JOptionPane.OK_OPTION);
        return (int) boxMenu.getSelectedIndex();
    }

    public static String solicitarCodigoDoProduto() {
        String codigo;
        codigo = JOptionPane.showInputDialog("Por favor digite o código do produto:");
        return codigo;
    }

    public static String solicitarNomeProduto() {
        String nome;
        nome = JOptionPane.showInputDialog("Por favor digite o nome do produto:");
        return nome;
    }

    public static Double solicitaPreco() {
        Double valorProduto;
        valorProduto = Double.parseDouble(JOptionPane.showInputDialog(null, "Por favor digite o valor do produto"));
        return valorProduto;
    }

    public static void listarProdutos(ArrayList<Produto> listaProdutos) {
        if (listaProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Estoque vazio.");
            return;
        }
        StringBuilder lista = new StringBuilder("Produtos em estoque:\n");
        for (Produto produto : listaProdutos) {
            lista.append("Nome: ").append(produto.getNomeProduto()).append(" | Preço: R$").append(produto.getPreco())
                    .append(" | Quantidade: ").append(produto.getQuantidade()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString(), "Lista de Produtos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void listarCupons(ArrayList<Cupom> listaCupons) {
        if (listaCupons.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cupom fiscal foi realizado.");
            return;
        }
        StringBuilder lista = new StringBuilder("Cupons Gerados:\n");
        for (Cupom cupom : listaCupons) {
            lista.append(cupom.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString(), "Lista de Cupons", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void exibirTotalVendas(double totalVendas) {
        String mensagem = String.format("Valor total das vendas: R$ %.2f", totalVendas);
        JOptionPane.showMessageDialog(null, mensagem, "Total de Vendas", JOptionPane.INFORMATION_MESSAGE);
    }

}
