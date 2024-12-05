/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.Funcionario;
import persistencia.FuncionarioDAO;
import java.util.List;

public class FuncionarioServico {
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioServico() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public void salvar(Funcionario funcionario) {
        validarFuncionario(funcionario);
        
        if (funcionario.getIdFuncionario() == 0) {
            funcionarioDAO.inserir(funcionario);
        } else {
            funcionarioDAO.atualizar(funcionario);
        }
    }

    private void validarFuncionario(Funcionario funcionario) {
        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do funcionário é obrigatório");
        }

        if (funcionario.getTelefone() == null || funcionario.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do funcionário é obrigatório");
        }

        if (funcionario.getEmail() == null || funcionario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email do funcionário é obrigatório");
        }
    }

    

    public Funcionario buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return funcionarioDAO.buscarPorId(id);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioDAO.listarTodos();
    }
}

