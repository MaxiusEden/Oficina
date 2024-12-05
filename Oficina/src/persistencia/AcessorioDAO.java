/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import modelo.Acessorio;
import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcessorioDAO implements ICRUD<Acessorio> {
    @Override
    public void inserir(Acessorio acessorio) {
        String sql = "INSERT INTO acessorio (nome, descricao) VALUES (?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, acessorio.getNome());
            stmt.setString(2, acessorio.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir acessório: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Acessorio acessorio) {
        String sql = "UPDATE acessorio SET nome=?, descricao=? WHERE id_acessorio=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, acessorio.getNome());
            stmt.setString(2, acessorio.getDescricao());
            stmt.setInt(3, acessorio.getIdAcessorio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar acessório: " + e.getMessage());
        }
    }

    

    @Override
    public Acessorio buscarPorId(int id) {
        String sql = "SELECT * FROM acessorio WHERE id_acessorio = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarAcessorio(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar acessório: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Acessorio> listarTodos() {
        List<Acessorio> acessorios = new ArrayList<>();
        String sql = "SELECT * FROM acessorio ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                acessorios.add(criarAcessorio(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar acessórios: " + e.getMessage());
        }
        return acessorios;
    }

    private Acessorio criarAcessorio(ResultSet rs) throws SQLException {
        Acessorio acessorio = new Acessorio();
        acessorio.setIdAcessorio(rs.getInt("id_acessorio"));
        acessorio.setNome(rs.getString("nome"));
        acessorio.setDescricao(rs.getString("descricao"));
        return acessorio;
    }
}
