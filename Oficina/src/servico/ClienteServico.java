/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

import modelo.Cliente;
import persistencia.ClienteDAO;
import java.util.List;

public class ClienteServico {
    private ClienteDAO clienteDAO;

    public ClienteServico() {
        this.clienteDAO = new ClienteDAO();
    }

    public void salvar(Cliente cliente) {
        validarCliente(cliente);
        
        if (cliente.getIdCliente() == 0) {
            clienteDAO.inserir(cliente);
        } else {
            clienteDAO.atualizar(cliente);
        }
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }

        if (cliente.getTipoCliente() == null || cliente.getTipoCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo do cliente é obrigatório");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do cliente é obrigatório");
        }

        if ("PF".equals(cliente.getTipoCliente())) {
            if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
                throw new IllegalArgumentException("CPF é obrigatório para pessoa física");
            }
        } else if ("PJ".equals(cliente.getTipoCliente())) {
            if (cliente.getCnpj() == null || cliente.getCnpj().trim().isEmpty()) {
                throw new IllegalArgumentException("CNPJ é obrigatório para pessoa jurídica");
            }
            if (cliente.getInscricaoEstadual() == null || cliente.getInscricaoEstadual().trim().isEmpty()) {
                throw new IllegalArgumentException("Inscrição Estadual é obrigatória para pessoa jurídica");
            }
        }
    }

    

    public Cliente buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca");
        }
        return clienteDAO.buscarPorId(id);
    }

    public List<Cliente> listarTodos() {
        return clienteDAO.listarTodos();
    }
}

