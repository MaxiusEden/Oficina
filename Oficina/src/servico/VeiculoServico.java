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
    
    try {
        Veiculo veiculoExistente = buscarPorPlaca(veiculo.getPlaca());
        
        if (veiculoExistente != null) {
            veiculoDAO.atualizar(veiculo);
        } else {
            veiculoDAO.inserir(veiculo);
        }
    } catch (Exception e) {
        throw new RuntimeException("Erro ao salvar veículo: " + e.getMessage(), e);
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
        throw new IllegalArgumentException("Placa inválida");
    }
    
    try {
        return veiculoDAO.buscarPorId(placa);
    } catch (Exception e) {
        throw new RuntimeException("Erro ao buscar veículo: " + e.getMessage(), e);
    }
}

    public List<Veiculo> listarTodos() {
        return veiculoDAO.listarTodos();
    }
}

