/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.OrdemServico;
import persistencia.OrdemServicoDAO;
import java.util.List;

public class OrdemServicoServico {
    private OrdemServicoDAO osDAO;

    public OrdemServicoServico() {
        this.osDAO = new OrdemServicoDAO();
    }

    public void salvar(OrdemServico os) {
        validarOS(os);
        
        if (os.getIdOs() == 0) {
            osDAO.inserir(os);
        } else {
            osDAO.atualizar(os);
        }
    }

    private void validarOS(OrdemServico os) {
        if (os.getPlacaVeiculo() == null || os.getPlacaVeiculo().trim().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória");
        }

        if (os.getValorTotal() < 0) {
            throw new IllegalArgumentException("Valor total não pode ser negativo");
        }

        if (os.getPrevisaoEntrega() == null) {
            throw new IllegalArgumentException("Previsão de entrega é obrigatória");
        }

        if (os.getStatus() == null || os.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Status é obrigatório");
        }
    }

    

    public OrdemServico buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return osDAO.buscarPorId(id);
    }

    public List<OrdemServico> listarTodos() {
        return osDAO.listarTodos();
    }
}

