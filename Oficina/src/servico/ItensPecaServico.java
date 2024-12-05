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
        validarItemPeca(item);
        
        if (item.getIdItensPeca() == 0) {
            itensPecaDAO.inserir(item);
        } else {
            itensPecaDAO.atualizar(item);
        }
    }

    private void validarItemPeca(ItensPeca item) {
        if (item.getIdPeca() <= 0) {
            throw new IllegalArgumentException("ID da peça inválido");
        }

        if (item.getIdOs() <= 0) {
            throw new IllegalArgumentException("ID da ordem de serviço inválido");
        }

        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (item.getValorUnitario() <= 0) {
            throw new IllegalArgumentException("Valor unitário deve ser maior que zero");
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

