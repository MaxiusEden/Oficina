/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import persistencia.ConexaoBD;
import modelo.Funcionario;
import interfaces.ICRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements ICRUD<Funcionario> {
    @Override
    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, telefone, email) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getTelefone());
            stmt.setString(3, funcionario.getEmail());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome=?, telefone=?, email=? WHERE id_funcionario=?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getTelefone());
            stmt.setString(3, funcionario.getEmail());
            stmt.setInt(4, funcionario.getIdFuncionario());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    

    @Override
    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarFuncionario(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionário: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                funcionarios.add(criarFuncionario(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage());
        }
        return funcionarios;
    }

    private Funcionario criarFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setTelefone(rs.getString("telefone"));
        funcionario.setEmail(rs.getString("email"));
        return funcionario;
    }
}
