/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.Posse;
import persistencia.PosseDAO;
import java.util.List;
import java.util.Date;

public class PosseServico {
    private PosseDAO posseDAO;

    public PosseServico() {
        this.posseDAO = new PosseDAO();
    }

    public void salvar(Posse posse) {
        validarPosse(posse);
        
        if (posse.getIdPosse() == 0) {
            posseDAO.inserir(posse);
        } else {
            posseDAO.atualizar(posse);
        }
    }

    private void validarPosse(Posse posse) {
        if (posse.getPlacaVeiculo() == null || posse.getPlacaVeiculo().trim().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória");
        }

        if (posse.getIdCliente() <= 0) {
            throw new IllegalArgumentException("Cliente inválido");
        }

        if (posse.getDataAquisicao() == null) {
            throw new IllegalArgumentException("Data de aquisição é obrigatória");
        }
    }

    

    public Posse buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return posseDAO.buscarPorId(id);
    }

    public List<Posse> listarTodos() {
        return posseDAO.listarTodos();
    }
}

