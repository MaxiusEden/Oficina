/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.Veiculo;
import persistencia.VeiculoDAO;
import java.util.List;

public class VeiculoServico {
    private VeiculoDAO veiculoDAO;

    public VeiculoServico() {
        this.veiculoDAO = new VeiculoDAO();
    }

    public void salvar(Veiculo veiculo) {
        validarVeiculo(veiculo);
        
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            veiculoDAO.inserir(veiculo);
        } else {
            veiculoDAO.atualizar(veiculo);
        }
    }

    private void validarVeiculo(Veiculo veiculo) {
        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória");
        }
        
        if (veiculo.getIdModelo() <= 0) {
            throw new IllegalArgumentException("Modelo inválido");
        }
        
        if (veiculo.getAnoFabricacao() <= 0) {
            throw new IllegalArgumentException("Ano de fabricação inválido");
        }
        
        if (veiculo.getAnoModelo() <= 0) {
            throw new IllegalArgumentException("Ano do modelo inválido");
        }
        
        if (veiculo.getNumeroChassi() == null || veiculo.getNumeroChassi().trim().isEmpty()) {
            throw new IllegalArgumentException("Número do chassi é obrigatório");
        }
    }

    

    public Veiculo buscarPorPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("Placa inválida para busca");
        }
        return veiculoDAO.buscarPorId(Integer.parseInt(placa));
    }

    public List<Veiculo> listarTodos() {
        return veiculoDAO.listarTodos();
    }
}

