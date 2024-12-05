/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import persistencia.ConexaoBD;
import modelo.OrdemServico;
import interfaces.ICRUD;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoDAO implements ICRUD<OrdemServico> {
    @Override
    public void inserir(OrdemServico os) {
        String sql = "INSERT INTO os (placa_veiculo, valor_total, data_entrada, hora_inicio, " +
                    "previsao_entrega, status, valor_pago, diferenca) " +
                    "VALUES (?, ?, CURRENT_DATE, CURRENT_TIME, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, os.getPlacaVeiculo());
            stmt.setDouble(2, os.getValorTotal());
            stmt.setTimestamp(3, Timestamp.valueOf(os.getPrevisaoEntrega()));
            stmt.setString(4, os.getStatus());
            stmt.setDouble(5, os.getValorPago());
            stmt.setDouble(6, os.getDiferenca());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir ordem de serviço: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(OrdemServico os) {
        String sql = "UPDATE os SET valor_total=?, previsao_entrega=?, status=?, " +
                    "valor_pago=?, diferenca=? WHERE id_os=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDouble(1, os.getValorTotal());
            stmt.setTimestamp(2, Timestamp.valueOf(os.getPrevisaoEntrega()));
            stmt.setString(3, os.getStatus());
            stmt.setDouble(4, os.getValorPago());
            stmt.setDouble(5, os.getDiferenca());
            stmt.setInt(6, os.getIdOs());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar ordem de serviço: " + e.getMessage());
        }
    }

    

    @Override
    public OrdemServico buscarPorId(int id) {
        String sql = "SELECT * FROM os WHERE id_os = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarOS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar ordem de serviço: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<OrdemServico> listarTodos() {
        List<OrdemServico> ordens = new ArrayList<>();
        String sql = "SELECT * FROM os ORDER BY data_entrada DESC, hora_inicio DESC";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ordens.add(criarOS(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar ordens de serviço: " + e.getMessage());
        }
        return ordens;
    }

    private OrdemServico criarOS(ResultSet rs) throws SQLException {
        OrdemServico os = new OrdemServico();
        os.setIdOs(rs.getInt("id_os"));
        os.setPlacaVeiculo(rs.getString("placa_veiculo"));
        os.setValorTotal(rs.getDouble("valor_total"));
        os.setDataEntrada(rs.getDate("data_entrada").toLocalDate());
        os.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
        os.setPrevisaoEntrega(rs.getTimestamp("previsao_entrega").toLocalDateTime());
        os.setStatus(rs.getString("status"));
        os.setValorPago(rs.getDouble("valor_pago"));
        os.setDiferenca(rs.getDouble("diferenca"));
        return os;
    }
}

