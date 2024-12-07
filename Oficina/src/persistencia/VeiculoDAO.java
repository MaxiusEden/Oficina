/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Veiculo;

public class VeiculoDAO implements ICRUD<Veiculo> {
    
    @Override
    public void inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, id_modelo, data_cadastro, ano_fabricacao, " +
                     "ano_modelo, numero_chassi, patrimonio, quilometragem) VALUES (?, ?, CURRENT_DATE, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, veiculo.getPlaca());
            stmt.setInt(2, veiculo.getIdModelo());
            stmt.setInt(3, veiculo.getAnoFabricacao());
            stmt.setInt(4, veiculo.getAnoModelo());
            stmt.setString(5, veiculo.getNumeroChassi());
            stmt.setString(6, veiculo.getPatrimonio());
            stmt.setDouble(7, veiculo.getQuilometragem());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir veículo: " + e.getMessage(), e);
        }
    }

   @Override
public void atualizar(Veiculo veiculo) {
    String sql = "UPDATE veiculo SET id_modelo=?, ano_fabricacao=?, " +
                 "ano_modelo=?, numero_chassi=?, patrimonio=?, quilometragem=? WHERE placa=?";
    
    try (Connection conn = ConexaoBD.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, veiculo.getIdModelo());
        stmt.setInt(2, veiculo.getAnoFabricacao());
        stmt.setInt(3, veiculo.getAnoModelo());
        stmt.setString(4, veiculo.getNumeroChassi());
        stmt.setString(5, veiculo.getPatrimonio());
        stmt.setDouble(6, veiculo.getQuilometragem());
        stmt.setString(7, veiculo.getPlaca());
        
        stmt.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao atualizar veículo: " + e.getMessage(), e);
    }
}

    @Override
public Veiculo buscarPorId(Object id) {
    if (id == null) {
        return null;
    }
    
    String placa = id.toString();
    String sql = "SELECT * FROM veiculo WHERE placa = ?";
    
    try (Connection conn = ConexaoBD.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, placa);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return criarVeiculo(rs);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar veículo por placa: " + e.getMessage(), e);
    }
    
    return null;
}

    @Override
    public List<Veiculo> listarTodos() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                veiculos.add(criarVeiculo(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veículos: " + e.getMessage());
        }
        return veiculos;
    }

    private Veiculo criarVeiculo(ResultSet rs) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setIdModelo(rs.getInt("id_modelo"));
        veiculo.setDataCadastro(rs.getDate("data_cadastro"));
        veiculo.setAnoFabricacao(rs.getInt("ano_fabricacao"));
        veiculo.setAnoModelo(rs.getInt("ano_modelo"));
        veiculo.setNumeroChassi(rs.getString("numero_chassi"));
        veiculo.setPatrimonio(rs.getString("patrimonio"));
        veiculo.setQuilometragem(rs.getDouble("quilometragem"));
        return veiculo;
    }
}

