/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.Acessorio;
import persistencia.AcessorioDAO;
import java.util.List;

public class AcessorioServico {
    private AcessorioDAO acessorioDAO;

    public AcessorioServico() {
        this.acessorioDAO = new AcessorioDAO();
    }

    public void salvar(Acessorio acessorio) {
        if (acessorio.getNome() == null || acessorio.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do acessório é obrigatório");
        }

        if (acessorio.getIdAcessorio() == 0) {
            acessorioDAO.inserir(acessorio);
        } else {
            acessorioDAO.atualizar(acessorio);
        }
    }

    

    public Acessorio buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return acessorioDAO.buscarPorId(id);
    }

    public List<Acessorio> listarTodos() {
        return acessorioDAO.listarTodos();
    }
}

