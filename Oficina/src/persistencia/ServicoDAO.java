/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import persistencia.ConexaoBD;
import modelo.Servico;
import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO implements ICRUD<Servico> {
    @Override
    public void inserir(Servico servico) {
        String sql = "INSERT INTO servico (nome, descricao, valor) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValor());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir serviço: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Servico servico) {
        String sql = "UPDATE servico SET nome=?, descricao=?, valor=? WHERE id_servico=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValor());
            stmt.setInt(4, servico.getIdServico());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar serviço: " + e.getMessage());
        }
    }

    

    @Override
    public Servico buscarPorId(int id) {
        String sql = "SELECT * FROM servico WHERE id_servico = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarServico(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviço: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Servico> listarTodos() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM servico ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                servicos.add(criarServico(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar serviços: " + e.getMessage());
        }
        return servicos;
    }

    private Servico criarServico(ResultSet rs) throws SQLException {
        Servico servico = new Servico();
        servico.setIdServico(rs.getInt("id_servico"));
        servico.setNome(rs.getString("nome"));
        servico.setDescricao(rs.getString("descricao"));
        servico.setValor(rs.getDouble("valor"));
        return servico;
    }
}


