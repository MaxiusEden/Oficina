/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.Servico;
import persistencia.ServicoDAO;
import java.util.List;

public class ServicoServico {
    private ServicoDAO servicoDAO;

    public ServicoServico() {
        this.servicoDAO = new ServicoDAO();
    }

    public void salvar(Servico servico) {
        validarServico(servico);
        
        if (servico.getIdServico() == 0) {
            servicoDAO.inserir(servico);
        } else {
            servicoDAO.atualizar(servico);
        }
    }

    private void validarServico(Servico servico) {
        if (servico.getNome() == null || servico.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do serviço é obrigatório");
        }

        if (servico.getValor() <= 0) {
            throw new IllegalArgumentException("Valor do serviço deve ser maior que zero");
        }
    }

    

    public Servico buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return servicoDAO.buscarPorId(id);
    }

    public List<Servico> listarTodos() {
        return servicoDAO.listarTodos();
    }
}

