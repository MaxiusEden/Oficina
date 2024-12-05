/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import persistencia.ConexaoBD;
import modelo.Modelo;
import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloDAO implements ICRUD<Modelo> {
    @Override
    public void inserir(Modelo modelo) {
        String sql = "INSERT INTO modelo (nome, id_marca) VALUES (?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, modelo.getNome());
            stmt.setInt(2, modelo.getIdMarca());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir modelo: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Modelo modelo) {
        String sql = "UPDATE modelo SET nome=?, id_marca=? WHERE id_modelo=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, modelo.getNome());
            stmt.setInt(2, modelo.getIdMarca());
            stmt.setInt(3, modelo.getIdModelo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar modelo: " + e.getMessage());
        }
    }

    

    @Override
    public Modelo buscarPorId(int id) {
        String sql = "SELECT * FROM modelo WHERE id_modelo = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarModelo(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar modelo: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Modelo> listarTodos() {
        List<Modelo> modelos = new ArrayList<>();
        String sql = "SELECT * FROM modelo ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                modelos.add(criarModelo(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar modelos: " + e.getMessage());
        }
        return modelos;
    }

    private Modelo criarModelo(ResultSet rs) throws SQLException {
        Modelo modelo = new Modelo();
        modelo.setIdModelo(rs.getInt("id_modelo"));
        modelo.setNome(rs.getString("nome"));
        modelo.setIdMarca(rs.getInt("id_marca"));
        return modelo;
    }
}

