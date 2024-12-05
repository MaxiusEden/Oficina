/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visao;
import forms.TelaClienteForm;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import persistencia.ClienteDAO;
import java.util.List;

public class TelaClientes extends javax.swing.JFrame {
     private DefaultTableModel modeloTabela;
    private ClienteDAO clienteDAO;
    
    public TelaClientes() {
        initComponents();
        configurarTabela();
        clienteDAO = new ClienteDAO();
        carregarDados();
    }
    
    private void configurarTabela() {
        String[] colunas = {
            "ID", "Tipo", "Nome", "Telefone", "Logradouro", "Número", 
            "Complemento", "Email", "CPF", "CNPJ", "Contato", "Inscrição Estadual"
        };
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela.setModel(modeloTabela);
        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        tabela.getColumnModel().getColumn(1).setPreferredWidth(70);  // Tipo
        tabela.getColumnModel().getColumn(2).setPreferredWidth(200); // Nome
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100); // Telefone
        tabela.getColumnModel().getColumn(4).setPreferredWidth(200); // Logradouro
        tabela.getColumnModel().getColumn(5).setPreferredWidth(70);  // Número
        tabela.getColumnModel().getColumn(6).setPreferredWidth(150); // Complemento
        tabela.getColumnModel().getColumn(7).setPreferredWidth(150); // Email
        tabela.getColumnModel().getColumn(8).setPreferredWidth(100); // CPF
        tabela.getColumnModel().getColumn(9).setPreferredWidth(100); // CNPJ
        tabela.getColumnModel().getColumn(10).setPreferredWidth(150); // Contato
        tabela.getColumnModel().getColumn(11).setPreferredWidth(120); // Inscrição
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnNovo.setText("Novo Cliente");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabela);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 884, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(242, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void carregarDados() {
    modeloTabela.setRowCount(0);
    List<Cliente> clientes = clienteDAO.listarTodos();
    for (Cliente c : clientes) {
        modeloTabela.addRow(new Object[]{
            c.getIdCliente(),
            c.getNome(),
            c.getTipoCliente(),
            c.getTelefone(),
            c.getLogradouro(),
            c.getNumero(),
            c.getComplemento(),
            c.getEmail(),
            c.getCpf(),
            c.getCnpj(),
            c.getContato(),
            c.getInscricaoEstadual()
        });
    }
}
    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        TelaClienteForm form = new TelaClienteForm(this, true);
        form.setVisible(true);
        carregarDados();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
 int linha = tabela.getSelectedRow();
    if (linha >= 0) {
        int id = (int) tabela.getValueAt(linha, 0);
        TelaClienteForm form = new TelaClienteForm(this, true, id);
        form.setVisible(true);
        carregarDados();
    } else {
        JOptionPane.showMessageDialog(this, 
            "Selecione um cliente para editar",
            "Editar Cliente",
            JOptionPane.WARNING_MESSAGE);
        
        }    }//GEN-LAST:event_btnEditarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
