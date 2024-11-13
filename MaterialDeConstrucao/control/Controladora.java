package com.victor.poo.control;

import com.victor.poo.model.Estoque;
import com.victor.poo.model.Produto;
import com.victor.poo.view.EntradaSaida;

public class Controladora {
    private Estoque estoque = new Estoque();

    public void exibirMenu() {
        int opcao;
        do {
            opcao = EntradaSaida.solicitarOperacao();
            switch (opcao) {
                case 0:
                    Produto produto = Produto.cadastrarProduto();
                    estoque.adicionarProduto(produto);
                    // Cadastrar produto
                    break;

                    case 1:
                    EntradaSaida.listarProdutos(estoque.getListaProdutos());
                    break;
                
                case 2:
                    estoque.informarQuantidadeEstoque();
                    // Informar o estoque dos produtos cadastrados
                    break;
                case 3:
                    estoque.venderProduto();
                    // Vender produto
                    break;
                case 4:
                    EntradaSaida.listarCupons(estoque.getListaCupons());
                    // Ver todos os cupons gerados
                    break;
                case 5:
                    double totalVendas = estoque.calcularTotalVendas();
                    EntradaSaida.exibirTotalVendas(totalVendas);
                    // Valor total das vendas
                    break;
            }
        } while (opcao != 6);
        EntradaSaida.exibiMsgEncerraPrograma();
        System.exit(0);
    }
}
