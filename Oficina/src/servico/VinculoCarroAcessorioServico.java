/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.VinculoCarroAcessorio;
import persistencia.VinculoCarroAcessorioDAO;
import java.util.List;

public class VinculoCarroAcessorioServico {
    private VinculoCarroAcessorioDAO vinculoDAO;

    public VinculoCarroAcessorioServico() {
        this.vinculoDAO = new VinculoCarroAcessorioDAO();
    }

    public void salvar(VinculoCarroAcessorio vinculo) {
        if (vinculo.getPlacaVeiculo() == null || vinculo.getPlacaVeiculo().trim().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória");
        }

        if (vinculo.getIdAcessorio() <= 0) {
            throw new IllegalArgumentException("ID do acessório inválido");
        }

        vinculoDAO.inserir(vinculo);
    }

    public void excluir(String placa, int idAcessorio) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória");
        }

        if (idAcessorio <= 0) {
            throw new IllegalArgumentException("ID do acessório inválido");
        }

        vinculoDAO.excluir(placa, idAcessorio);
    }

    public VinculoCarroAcessorio buscarPorPlacaEAcessorio(String placa, int idAcessorio) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória");
        }

        if (idAcessorio <= 0) {
            throw new IllegalArgumentException("ID do acessório inválido");
        }

        return vinculoDAO.buscarPorPlacaEAcessorio(placa, idAcessorio);
    }

    public List<VinculoCarroAcessorio> listarTodos() {
        return vinculoDAO.listarTodos();
    }
}

