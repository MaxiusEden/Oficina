/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.VinculoCarroAcessorio;

public class VinculoCarroAcessorioDAO implements ICRUD<VinculoCarroAcessorio> {
    
    @Override
    public void inserir(VinculoCarroAcessorio vinculo) {
        String sql = "INSERT INTO vinculo_carro_acessorio (placa_veiculo, id_acessorio) VALUES (?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vinculo.getPlacaVeiculo());
            stmt.setInt(2, vinculo.getIdAcessorio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir vínculo: " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizar(VinculoCarroAcessorio vinculo) {
        // Não é necessário implementar atualização pois a tabela possui chave composta
        throw new UnsupportedOperationException("Operação não suportada para vínculo carro-acessório");
    }

    @Override
    public VinculoCarroAcessorio buscarPorId(Object id) {
        throw new UnsupportedOperationException("Use o método buscarPorPlacaEAcessorio(String placa, int idAcessorio)");
    }

    public VinculoCarroAcessorio buscarPorPlacaEAcessorio(String placa, int idAcessorio) {
        String sql = "SELECT * FROM vinculo_carro_acessorio WHERE placa_veiculo = ? AND id_acessorio = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, placa);
            stmt.setInt(2, idAcessorio);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarVinculo(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vínculo: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<VinculoCarroAcessorio> listarTodos() {
        List<VinculoCarroAcessorio> vinculos = new ArrayList<>();
        String sql = "SELECT * FROM vinculo_carro_acessorio";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                vinculos.add(criarVinculo(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vínculos: " + e.getMessage(), e);
        }
        return vinculos;
    }

    private VinculoCarroAcessorio criarVinculo(ResultSet rs) throws SQLException {
        VinculoCarroAcessorio vinculo = new VinculoCarroAcessorio();
        vinculo.setPlacaVeiculo(rs.getString("placa_veiculo"));
        vinculo.setIdAcessorio(rs.getInt("id_acessorio"));
        return vinculo;
    }
}

