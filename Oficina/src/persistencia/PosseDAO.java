/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import persistencia.ConexaoBD;
import modelo.Posse;
import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PosseDAO implements ICRUD<Posse> {
     @Override
    public void inserir(Posse posse) {
        String sql = "INSERT INTO posse (placa_veiculo, id_cliente, data_aquisicao, data_venda) " +
                    "VALUES (?, ?, CURRENT_DATE, NULL)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, posse.getPlacaVeiculo());
            stmt.setInt(2, posse.getIdCliente());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir posse: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Posse posse) {
        String sql = "UPDATE posse SET placa_veiculo=?, id_cliente=?, data_venda=CURRENT_DATE WHERE id_posse=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, posse.getPlacaVeiculo());
            stmt.setInt(2, posse.getIdCliente());
            stmt.setInt(3, posse.getIdPosse());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar posse: " + e.getMessage());
        }
    }

    

    @Override
    public Posse buscarPorId(int id) {
        String sql = "SELECT * FROM posse WHERE id_posse = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarPosse(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar posse: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Posse> listarTodos() {
        List<Posse> posses = new ArrayList<>();
        String sql = "SELECT * FROM posse ORDER BY data_aquisicao DESC";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                posses.add(criarPosse(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar posses: " + e.getMessage());
        }
        return posses;
    }

    private Posse criarPosse(ResultSet rs) throws SQLException {
        Posse posse = new Posse();
    posse.setIdPosse(rs.getInt("id_posse"));
    posse.setPlacaVeiculo(rs.getString("placa_veiculo"));
    posse.setIdCliente(rs.getInt("id_cliente"));
    posse.setDataAquisicao(rs.getDate("data_aquisicao"));
    posse.setDataVenda(rs.getDate("data_venda"));
    return posse;
    }
}