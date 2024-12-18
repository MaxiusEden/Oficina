/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forms;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Date;
import modelo.Veiculo;
import modelo.Modelo;
import servico.VeiculoServico;
import servico.ModeloServico;

public class TelaVeiculoForm extends JDialog {
    private VeiculoServico veiculoServico;
    private ModeloServico modeloServico;
    private Veiculo veiculo;
    private boolean editando;

    public TelaVeiculoForm(Frame parent, boolean modal) {
        super(parent, modal);
    System.out.println("TelaVeiculoForm constructor called");
    setTitle("Novo Veículo");
    veiculoServico = new VeiculoServico();
    modeloServico = new ModeloServico();
    veiculo = new Veiculo();
    initComponents();
    configurarComponentes();
    }

    public TelaVeiculoForm(Frame parent, boolean modal, String placa) {
    super(parent, modal);
    setTitle("Editar Veículo");
    
    // Inicializar serviços
    veiculoServico = new VeiculoServico();
    modeloServico = new ModeloServico(); // Adicione esta linha
    
    initComponents();
    configurarComponentes();
    carregarModelos(); // Certifique-se de chamar este método para popular o combo de modelos
    
    try {
        carregarVeiculo(placa);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Erro ao carregar veículo: " + e.getMessage(), 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
        dispose();
    }
}

    private void configurarComponentes() {
        carregarModelos();
        btnCancelar.addActionListener(e -> dispose());
        txtDataCadastro.setValue(new Date());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        txtAnoFabricacao = new javax.swing.JTextField();
        txtChassi = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cboModelo = new javax.swing.JComboBox<>();
        txtDataCadastro = new javax.swing.JFormattedTextField();
        txtPatrimonio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtQuilometragem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtAnoModelo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Placa:");

        jLabel2.setText("Modelo:");

        jLabel3.setText("Data de Cadastro:");

        jLabel4.setText("Ano de Fabricação:");

        jLabel5.setText("Chassi:");

        jLabel6.setText("Patrimônio:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        jLabel7.setText("Quilometragem:");

        jLabel8.setText("Ano do Modelo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPatrimonio))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAnoFabricacao))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDataCadastro))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPlaca)
                                    .addComponent(cboModelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuilometragem))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtChassi, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAnoModelo)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(406, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnoFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtAnoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtChassi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addGap(63, 63, 63))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void carregarModelos() {
    try {
        List<Modelo> modelos = modeloServico.listarTodos();
        
        // Limpar combo existente
        cboModelo.removeAllItems();
        
        // Adicionar modelos ao combo
        for (Modelo modelo : modelos) {
            cboModelo.addItem(modelo);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Erro ao carregar modelos: " + e.getMessage(), 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
    }
}
   
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (!validarCampos()) {
        return;
    }

    veiculo.setPlaca(txtPlaca.getText());
    veiculo.setIdModelo(((Modelo) cboModelo.getSelectedItem()).getIdModelo());
    veiculo.setAnoFabricacao(Integer.parseInt(txtAnoFabricacao.getText()));
    veiculo.setAnoModelo(Integer.parseInt(txtAnoModelo.getText()));
    veiculo.setNumeroChassi(txtChassi.getText());
    veiculo.setPatrimonio(txtPatrimonio.getText());
    veiculo.setQuilometragem(Double.parseDouble(txtQuilometragem.getText()));
    
    if (txtDataCadastro.getValue() != null) {
        veiculo.setDataCadastro((Date) txtDataCadastro.getValue());
    }

    try {
        veiculoServico.salvar(veiculo);
        JOptionPane.showMessageDialog(this, 
            "Veículo salvo com sucesso!", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE);
        dispose();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Erro ao salvar veículo: " + e.getMessage(), 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnSalvarActionPerformed
private boolean validarCampos() {
        if (txtPlaca.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "A placa é obrigatória!");
            txtPlaca.requestFocus();
            return false;
        }
        if (cboModelo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um modelo!");
            cboModelo.requestFocus();
            return false;
        }
        try {
            Integer.valueOf(txtAnoFabricacao.getText());
            Integer.valueOf(txtAnoModelo.getText());
            Double.parseDouble(txtQuilometragem.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valores numéricos inválidos!");
            return false;
        }
        return true;
    }

    private void carregarVeiculo(String placa) {
    veiculo = veiculoServico.buscarPorPlaca(placa);
    
    if (veiculo != null) {
        txtPlaca.setText(veiculo.getPlaca());
        txtDataCadastro.setValue(veiculo.getDataCadastro());
        txtAnoFabricacao.setText(String.valueOf(veiculo.getAnoFabricacao()));
        txtAnoModelo.setText(String.valueOf(veiculo.getAnoModelo()));
        txtChassi.setText(veiculo.getNumeroChassi());
        txtPatrimonio.setText(veiculo.getPatrimonio());
        txtQuilometragem.setText(String.valueOf(veiculo.getQuilometragem()));
        
        // Configurar modelo
        for (int i = 0; i < cboModelo.getItemCount(); i++) {
            Modelo m = cboModelo.getItemAt(i);
            if (m.getIdModelo() == veiculo.getIdModelo()) {
                cboModelo.setSelectedIndex(i);
                break;
            }
        }
    } else {
        throw new RuntimeException("Veículo não encontrado");
    }
}
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
            java.util.logging.Logger.getLogger(TelaVeiculoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVeiculoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVeiculoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVeiculoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaVeiculoForm dialog = new TelaVeiculoForm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Modelo> cboModelo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtAnoFabricacao;
    private javax.swing.JTextField txtAnoModelo;
    private javax.swing.JTextField txtChassi;
    private javax.swing.JFormattedTextField txtDataCadastro;
    private javax.swing.JTextField txtPatrimonio;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtQuilometragem;
    // End of variables declaration//GEN-END:variables
}
