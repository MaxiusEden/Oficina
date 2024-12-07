/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import java.util.List;
import modelo.Peca;
import persistencia.PecaDAO;

public class PecaServico {
    private PecaDAO pecaDAO;

    public PecaServico() {
        this.pecaDAO = new PecaDAO();
    }

    public void salvar(Peca peca) {
        validarPeca(peca);
        
        if (peca.getIdPeca() == 0) {
            pecaDAO.inserir(peca);
        } else {
            pecaDAO.atualizar(peca);
        }
    }

    private void validarPeca(Peca peca) {
        if (peca.getNome() == null || peca.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da peça é obrigatório");
        }

        if (peca.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa");
        }

        if (peca.getValorUnitario() <= 0) {
            throw new IllegalArgumentException("Valor unitário deve ser maior que zero");
        }

        if (peca.getCodigoFabricante() == null || peca.getCodigoFabricante().trim().isEmpty()) {
            throw new IllegalArgumentException("Código do fabricante é obrigatório");
        }
    }

    public Peca buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return pecaDAO.buscarPorId(id);
    }

    public List<Peca> listarTodos() {
        return pecaDAO.listarTodos();
    }
}
