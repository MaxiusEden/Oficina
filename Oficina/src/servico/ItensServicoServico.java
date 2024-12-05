/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.ItensServico;
import persistencia.ItensServicoDAO;
import java.util.List;

public class ItensServicoServico {
    private ItensServicoDAO itensServicoDAO;

    public ItensServicoServico() {
        this.itensServicoDAO = new ItensServicoDAO();
    }

    public void salvar(ItensServico item) {
        validarItemServico(item);
        
        if (item.getIdItensServico() == 0) {
            itensServicoDAO.inserir(item);
        } else {
            itensServicoDAO.atualizar(item);
        }
    }

    private void validarItemServico(ItensServico item) {
        if (item.getIdServico() <= 0) {
            throw new IllegalArgumentException("ID do serviço inválido");
        }

        if (item.getIdOs() <= 0) {
            throw new IllegalArgumentException("ID da ordem de serviço inválido");
        }

        if (item.getIdFuncionario() <= 0) {
            throw new IllegalArgumentException("ID do funcionário inválido");
        }

        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (item.getValorUnitario() <= 0) {
            throw new IllegalArgumentException("Valor unitário deve ser maior que zero");
        }
    }

   

    public ItensServico buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return itensServicoDAO.buscarPorId(id);
    }

    public List<ItensServico> listarTodos() {
        return itensServicoDAO.listarTodos();
    }
}

