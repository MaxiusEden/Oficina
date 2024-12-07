/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Marca;

public class MarcaDAO implements ICRUD<Marca> {
    @Override
    public void inserir(Marca marca) {
        String sql = "INSERT INTO marca (nome) VALUES (?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, marca.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir marca: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Marca marca) {
        String sql = "UPDATE marca SET nome=? WHERE id_marca=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, marca.getNome());
            stmt.setInt(2, marca.getIdMarca());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar marca: " + e.getMessage());
        }
    }

    

    @Override
    public Marca buscarPorId(Object id) {
        String sql = "SELECT * FROM marca WHERE id_marca = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, Integer.parseInt(id.toString()));
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarMarca(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar marca: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Marca> listarTodos() {
        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT * FROM marca ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                marcas.add(criarMarca(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar marcas: " + e.getMessage());
        }
        return marcas;
    }

    private Marca criarMarca(ResultSet rs) throws SQLException {
        Marca marca = new Marca();
        marca.setIdMarca(rs.getInt("id_marca"));
        marca.setNome(rs.getString("nome"));
        return marca;
    }
}
