/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDAO implements ICRUD<Cliente> {
    @Override
    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (tipo_cliente, nome, telefone, logradouro, numero, " +
                    "complemento, email, cpf, cnpj, contato, inscricao_estadual) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getTipoCliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getLogradouro());
            stmt.setString(5, cliente.getNumero());
            stmt.setString(6, cliente.getComplemento());
            stmt.setString(7, cliente.getEmail());
            stmt.setString(8, cliente.getCpf());
            stmt.setString(9, cliente.getCnpj());
            stmt.setString(10, cliente.getContato());
            stmt.setString(11, cliente.getInscricaoEstadual());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET tipo_cliente=?, nome=?, telefone=?, logradouro=?, " +
                    "numero=?, complemento=?, email=?, cpf=?, cnpj=?, contato=?, " +
                    "inscricao_estadual=? WHERE id_cliente=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getTipoCliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getLogradouro());
            stmt.setString(5, cliente.getNumero());
            stmt.setString(6, cliente.getComplemento());
            stmt.setString(7, cliente.getEmail());
            stmt.setString(8, cliente.getCpf());
            stmt.setString(9, cliente.getCnpj());
            stmt.setString(10, cliente.getContato());
            stmt.setString(11, cliente.getInscricaoEstadual());
            stmt.setInt(12, cliente.getIdCliente());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    
   
   @Override
public Cliente buscarPorId(Object id) {
    String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
    try (Connection conn = ConexaoBD.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, Integer.parseInt(id.toString()));
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return criarCliente(rs);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar cliente: " + e.getMessage());
    }
    return null;
}

    private Cliente criarCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setTipoCliente(rs.getString("tipo_cliente"));
        cliente.setNome(rs.getString("nome"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setLogradouro(rs.getString("logradouro"));
        cliente.setNumero(rs.getString("numero"));
        cliente.setComplemento(rs.getString("complemento"));
        cliente.setEmail(rs.getString("email"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setCnpj(rs.getString("cnpj"));
        cliente.setContato(rs.getString("contato"));
        cliente.setInscricaoEstadual(rs.getString("inscricao_estadual"));
        return cliente;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                clientes.add(criarCliente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }
}
