/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import persistencia.ConexaoBD;
import modelo.Peca;
import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecaDAO implements ICRUD<Peca> {
    @Override
    public void inserir(Peca peca) {
        String sql = "INSERT INTO peca (nome, descricao, quantidade_estoque, valor_unitario, codigo_fabricante) " +
                    "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, peca.getNome());
            stmt.setString(2, peca.getDescricao());
            stmt.setInt(3, peca.getQuantidadeEstoque());
            stmt.setDouble(4, peca.getValorUnitario());
            stmt.setString(5, peca.getCodigoFabricante());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir peça: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Peca peca) {
        String sql = "UPDATE peca SET nome=?, descricao=?, quantidade_estoque=?, " +
                    "valor_unitario=?, codigo_fabricante=? WHERE id_peca=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, peca.getNome());
            stmt.setString(2, peca.getDescricao());
            stmt.setInt(3, peca.getQuantidadeEstoque());
            stmt.setDouble(4, peca.getValorUnitario());
            stmt.setString(5, peca.getCodigoFabricante());
            stmt.setInt(6, peca.getIdPeca());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar peça: " + e.getMessage());
        }
    }

    

    @Override
    public Peca buscarPorId(int id) {
        String sql = "SELECT * FROM peca WHERE id_peca = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarPeca(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar peça: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Peca> listarTodos() {
        List<Peca> pecas = new ArrayList<>();
        String sql = "SELECT * FROM peca ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                pecas.add(criarPeca(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar peças: " + e.getMessage());
        }
        return pecas;
    }

    private Peca criarPeca(ResultSet rs) throws SQLException {
        Peca peca = new Peca();
        peca.setIdPeca(rs.getInt("id_peca"));
        peca.setNome(rs.getString("nome"));
        peca.setDescricao(rs.getString("descricao"));
        peca.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
        peca.setValorUnitario(rs.getDouble("valor_unitario"));
        peca.setCodigoFabricante(rs.getString("codigo_fabricante"));
        return peca;
    }
}

