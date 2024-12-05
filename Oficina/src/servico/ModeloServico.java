/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.Modelo;
import persistencia.ModeloDAO;
import java.util.List;

public class ModeloServico {
    private ModeloDAO modeloDAO;

    public ModeloServico() {
        this.modeloDAO = new ModeloDAO();
    }

    public void salvar(Modelo modelo) {
        if (modelo.getNome() == null || modelo.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do modelo é obrigatório");
        }
        
        if (modelo.getIdMarca() <= 0) {
            throw new IllegalArgumentException("Marca inválida");
        }

        if (modelo.getIdModelo() == 0) {
            modeloDAO.inserir(modelo);
        } else {
            modeloDAO.atualizar(modelo);
        }
    }

    

    public Modelo buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return modeloDAO.buscarPorId(id);
    }

    public List<Modelo> listarTodos() {
        return modeloDAO.listarTodos();
    }

}

