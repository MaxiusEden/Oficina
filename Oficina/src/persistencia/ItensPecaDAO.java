/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import persistencia.ConexaoBD;
import modelo.ItensPeca;
import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItensPecaDAO implements ICRUD<ItensPeca> {
    @Override
    public void inserir(ItensPeca item) {
        String sql = "INSERT INTO itens_peca (id_peca, id_os, valor_unitario, valor_total, quantidade) " +
                    "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, item.getIdPeca());
            stmt.setInt(2, item.getIdOs());
            stmt.setDouble(3, item.getValorUnitario());
            stmt.setDouble(4, item.getValorTotal());
            stmt.setInt(5, item.getQuantidade());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir item de peça: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(ItensPeca item) {
        String sql = "UPDATE itens_peca SET id_peca=?, id_os=?, valor_unitario=?, " +
                    "valor_total=?, quantidade=? WHERE id_itens_peca=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, item.getIdPeca());
            stmt.setInt(2, item.getIdOs());
            stmt.setDouble(3, item.getValorUnitario());
            stmt.setDouble(4, item.getValorTotal());
            stmt.setInt(5, item.getQuantidade());
            stmt.setInt(6, item.getIdItensPeca());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar item de peça: " + e.getMessage());
        }
    }

    

    @Override
    public ItensPeca buscarPorId(int id) {
        String sql = "SELECT * FROM itens_peca WHERE id_itens_peca = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarItensPeca(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item de peça: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ItensPeca> listarTodos() {
        List<ItensPeca> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens_peca";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                itens.add(criarItensPeca(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens de peça: " + e.getMessage());
        }
        return itens;
    }

    private ItensPeca criarItensPeca(ResultSet rs) throws SQLException {
        ItensPeca item = new ItensPeca();
        item.setIdItensPeca(rs.getInt("id_itens_peca"));
        item.setIdPeca(rs.getInt("id_peca"));
        item.setIdOs(rs.getInt("id_os"));
        item.setValorUnitario(rs.getDouble("valor_unitario"));
        item.setValorTotal(rs.getDouble("valor_total"));
        item.setQuantidade(rs.getInt("quantidade"));
        return item;
    }
}

