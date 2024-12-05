/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import persistencia.MarcaDAO;
import modelo.Marca;
import java.util.List;

public class MarcaServico {
    private MarcaDAO marcaDAO;

    public MarcaServico() {
        this.marcaDAO = new MarcaDAO();
    }

    public void salvar(Marca marca) {
        if (marca.getNome() == null || marca.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da marca é obrigatório");
        }
        
        if (marca.getIdMarca() == 0) {
            marcaDAO.inserir(marca);
        } else {
            marcaDAO.atualizar(marca);
        }
    }

    

    public Marca buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return marcaDAO.buscarPorId(id);
    }

    public List<Marca> listarTodos() {
        return marcaDAO.listarTodos();
    }
}

