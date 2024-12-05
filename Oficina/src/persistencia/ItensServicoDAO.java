/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import persistencia.ConexaoBD;
import modelo.ItensServico;
import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItensServicoDAO implements ICRUD<ItensServico> {
    @Override
    public void inserir(ItensServico item) {
        String sql = "INSERT INTO itens_servicos (id_servico, id_os, id_funcionario, " +
                    "valor_unitario, valor_total, quantidade) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, item.getIdServico());
            stmt.setInt(2, item.getIdOs());
            stmt.setInt(3, item.getIdFuncionario());
            stmt.setDouble(4, item.getValorUnitario());
            stmt.setDouble(5, item.getValorTotal());
            stmt.setInt(6, item.getQuantidade());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir item de serviço: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(ItensServico item) {
        String sql = "UPDATE itens_servicos SET id_servico=?, id_os=?, id_funcionario=?, " +
                    "valor_unitario=?, valor_total=?, quantidade=? WHERE id_itens_servico=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, item.getIdServico());
            stmt.setInt(2, item.getIdOs());
            stmt.setInt(3, item.getIdFuncionario());
            stmt.setDouble(4, item.getValorUnitario());
            stmt.setDouble(5, item.getValorTotal());
            stmt.setInt(6, item.getQuantidade());
            stmt.setInt(7, item.getIdItensServico());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar item de serviço: " + e.getMessage());
        }
    }

    

    @Override
    public ItensServico buscarPorId(int id) {
        String sql = "SELECT * FROM itens_servicos WHERE id_itens_servico = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarItensServico(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item de serviço: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ItensServico> listarTodos() {
        List<ItensServico> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens_servicos";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                itens.add(criarItensServico(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens de serviço: " + e.getMessage());
        }
        return itens;
    }

    private ItensServico criarItensServico(ResultSet rs) throws SQLException {
        ItensServico item = new ItensServico();
        item.setIdItensServico(rs.getInt("id_itens_servico"));
        item.setIdServico(rs.getInt("id_servico"));
        item.setIdOs(rs.getInt("id_os"));
        item.setIdFuncionario(rs.getInt("id_funcionario"));
        item.setValorUnitario(rs.getDouble("valor_unitario"));
        item.setValorTotal(rs.getDouble("valor_total"));
        item.setQuantidade(rs.getInt("quantidade"));
        return item;
    }
}

