/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.ItensPeca;
import persistencia.ItensPecaDAO;
import java.util.List;

public class ItensPecaServico {
    private ItensPecaDAO itensPecaDAO;

    public ItensPecaServico() {
        this.itensPecaDAO = new ItensPecaDAO();
    }

    public void salvar(ItensPeca item) {
        if (item.getIdPeca() <= 0) {
        throw new RuntimeException("ID da peça inválido");
    }
    if (item.getQuantidade() <= 0) {
        throw new RuntimeException("Quantidade inválida");
    }
    if (item.getValorUnitario() <= 0) {
        throw new RuntimeException("Valor unitário inválido");
    }
    if (item.getValorTotal() <= 0) {
        throw new RuntimeException("Valor total inválido");
    }

    if (item.getIdItensPeca() == 0) {
        itensPecaDAO.inserir(item);
    } else {
        itensPecaDAO.atualizar(item);
    }
    }


    

    public ItensPeca buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return itensPecaDAO.buscarPorId(id);
    }

    public List<ItensPeca> listarTodos() {
        return itensPecaDAO.listarTodos();
    }
}

