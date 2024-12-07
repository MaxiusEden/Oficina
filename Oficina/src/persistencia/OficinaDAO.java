/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Oficina;

public class OficinaDAO implements ICRUD<Oficina> {
    @Override
    public void inserir(Oficina oficina) {
        String sql = "INSERT INTO oficina (cnpj, nome, logradouro, numero, complemento, telefone1, telefone2, email) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, oficina.getCnpj());
            stmt.setString(2, oficina.getNome());
            stmt.setString(3, oficina.getLogradouro());
            stmt.setString(4, oficina.getNumero());
            stmt.setString(5, oficina.getComplemento());
            stmt.setString(6, oficina.getTelefone1());
            stmt.setString(7, oficina.getTelefone2());
            stmt.setString(8, oficina.getEmail());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir oficina: " + e.getMessage());
        }
    }  
    @Override
    public void atualizar(Oficina oficina) {
        String sql = "UPDATE oficina SET nome=?, logradouro=?, numero=?, complemento=?, " +
                    "telefone1=?, telefone2=?, email=? WHERE cnpj=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, oficina.getNome());
            stmt.setString(2, oficina.getLogradouro());
            stmt.setString(3, oficina.getNumero());
            stmt.setString(4, oficina.getComplemento());
            stmt.setString(5, oficina.getTelefone1());
            stmt.setString(6, oficina.getTelefone2());
            stmt.setString(7, oficina.getEmail());
            stmt.setString(8, oficina.getCnpj());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar oficina: " + e.getMessage());
        }
    }

    
    @Override
    public Oficina buscarPorId(Object id) {
        String sql = "SELECT * FROM oficina WHERE cnpj = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarOficina(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar oficina: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Oficina> listarTodos() {
        List<Oficina> oficinas = new ArrayList<>();
        String sql = "SELECT * FROM oficina";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                oficinas.add(criarOficina(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar oficinas: " + e.getMessage());
        }
        return oficinas;
    }
    private Oficina criarOficina(ResultSet rs) throws SQLException {
        Oficina oficina = new Oficina();
        oficina.setCnpj(rs.getString("cnpj"));
        oficina.setNome(rs.getString("nome"));
        oficina.setLogradouro(rs.getString("logradouro"));
        oficina.setNumero(rs.getString("numero"));
        oficina.setComplemento(rs.getString("complemento"));
        oficina.setTelefone1(rs.getString("telefone1"));
        oficina.setTelefone2(rs.getString("telefone2"));
        oficina.setEmail(rs.getString("email"));
        return oficina;
    }
}