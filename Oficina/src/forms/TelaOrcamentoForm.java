/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forms;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import modelo.ItensPeca;
import modelo.ItensServico;
import modelo.Peca;
import modelo.Servico;
import servico.ItensPecaServico;
import servico.ItensServicoServico;
import servico.PecaServico;
import servico.ServicoServico;

public class TelaOrcamentoForm extends JDialog {
    private ItensPecaServico itensPecaServico;
    private ItensServicoServico itensServicoServico;
    private PecaServico pecaServico;
    private ServicoServico servicoServico;
    private ItensPeca itensPeca;
    private ItensServico itensServico;
    private boolean editando;
    private int idOs;

    public TelaOrcamentoForm(Frame parent, boolean modal, int idOs) {
        super(parent, modal);
        this.idOs = idOs;
        setTitle("Novo Item");
        inicializarServicos();
        initComponents();
        configurarComponentes();
    }

    public TelaOrcamentoForm(Frame parent, boolean modal, int idOs, String tipo) {
        super(parent, modal);
        setTitle("Editar Item");
        inicializarServicos();
        editando = true;
        initComponents();
        configurarComponentes();
        carregarItem(idOs, tipo);
    }

    private void inicializarServicos() {
        itensPecaServico = new ItensPecaServico();
        itensServicoServico = new ItensServicoServico();
        pecaServico = new PecaServico();
        servicoServico = new ServicoServico();
        itensPeca = new ItensPeca();
        itensServico = new ItensServico();
    }

    private void configurarComponentes() {
        btnCancelar.addActionListener(e -> dispose());
        
        cboTipo.addItem("Peça");
        cboTipo.addItem("Serviço");
        
        cboTipo.addActionListener(e -> atualizarComboItem());
        
        txtQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                calcularValorTotal();
            }
        });
        
        txtValorUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                calcularValorTotal();
            }
        });
        
        atualizarComboItem();
    }

    private void atualizarComboItem() {
        cboItem.removeAllItems();
        if (cboTipo.getSelectedItem().equals("Peça")) {
            lblItem.setText("Peça:");
            for (Peca p : pecaServico.listarTodos()) {
                cboItem.addItem(p); // Ensure Peca objects have valid IDs
            }
        } else {
            lblItem.setText("Serviço:");
            for (Servico s : servicoServico.listarTodos()) {
                cboItem.addItem(s); // Ensure Servico objects have valid IDs
            }
        }
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        lblItem = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        cboItem = new javax.swing.JComboBox<>();
        txtQuantidade = new javax.swing.JTextField();
        txtValorUnitario = new javax.swing.JTextField();
        txtValorTotal = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tipo:");
        lblItem.setText("Item:");
        jLabel3.setText("Quantidade:");
        jLabel4.setText("Valor Unitário:");
        jLabel5.setText("Valor Total:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(evt -> btnSalvarActionPerformed(evt));

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblItem)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cboItem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtQuantidade))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtValorUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtValorTotal)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addGap(0, 301, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblItem)
                    .addComponent(cboItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        if (!validarCampos()) {
            return;
        }

        try {
            if (cboTipo.getSelectedItem().equals("Peça")) {
                salvarItensPeca();
            } else {
                salvarItensServico();
            }
            
            JOptionPane.showMessageDialog(this, 
                "Item salvo com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao salvar item: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calcularValorTotal() {
        try {
            String quantidadeStr = txtQuantidade.getText();
            String valorUnitarioStr = txtValorUnitario.getText().replace(",", ".");
            
            double quantidade = Double.parseDouble(quantidadeStr);
            double valorUnitario = Double.parseDouble(valorUnitarioStr);
            
            double total = quantidade * valorUnitario;
            txtValorTotal.setText(String.format("%.2f", total).replace(".", ","));
        } catch (NumberFormatException e) {
            txtValorTotal.setText("0,00");
        }
    }

    private void salvarItensPeca() {
        Peca peca = (Peca) cboItem.getSelectedItem();
        if (peca != null) { // Check if the selected item is not null
            itensPeca = new ItensPeca();
            itensPeca.setIdPeca(peca.getIdPeca());
            itensPeca.setIdOs(this.idOs); // Set the OS ID
            itensPeca.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            itensPeca.setValorUnitario(Double.parseDouble(txtValorUnitario.getText().replace(",", ".")));
            itensPeca.setValorTotal(Double.parseDouble(txtValorTotal.getText().replace(",", ".")));
    
            // Debugging output to check IDs
            System.out.println("Saving Item Peca: ID=" + itensPeca.getIdPeca() + ", OS ID=" + this.idOs);
    
            itensPecaServico.salvar(itensPeca);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma peça válida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void salvarItensServico() {
        Servico servico = (Servico) cboItem.getSelectedItem();
        if (servico != null) { // Check if the selected item is not null
            itensServico = new ItensServico();
            itensServico.setIdServico(servico.getIdServico());
            itensServico.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            itensServico.setValorUnitario(Double.parseDouble(txtValorUnitario.getText().replace(",", ".")));
            itensServico.setValorTotal(Double.parseDouble(txtValorTotal.getText().replace(",", ".")));
    
            // Debugging output to check IDs
            System.out.println("Saving Item Servico: ID=" + itensServico.getIdServico());
    
            itensServicoServico.salvar(itensServico);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um serviço válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (cboItem.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um item!");
            cboItem.requestFocus();
            return false;
        }
        
        String valorUnitarioStr = txtValorUnitario.getText();
        if (!valorUnitarioStr.matches("^\\d+,\\d{2}$")) {
            JOptionPane.showMessageDialog(this, "Valor unitário deve estar no formato: 0000,00");
            txtValorUnitario.requestFocus();
            return false;
        }
        
        try {
            Integer.parseInt(txtQuantidade.getText());
            Double.parseDouble(valorUnitarioStr.replace(",", "."));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valores inválidos!");
            return false;
        }
        
        return true;
    }

    private void carregarItem(int idOs, String tipo) {
        if (tipo.equals("Peça")) {
            itensPeca = itensPecaServico.buscarPorId(idOs);
            if (itensPeca != null) {
                cboTipo.setSelectedItem("Peça");
                for (int i = 0; i < cboItem.getItemCount(); i++) {
                    Peca peca = (Peca) cboItem.getItemAt(i);
                    if (peca.getIdPeca() == itensPeca.getIdPeca()) {
                        cboItem.setSelectedIndex(i);
                        break;
                    }
                }
                txtQuantidade.setText(String.valueOf(itensPeca.getQuantidade()));
                txtValorUnitario.setText(String.format("%.2f", itensPeca.getValorUnitario()));
                txtValorTotal.setText(String.format("%.2f", itensPeca.getValorTotal()));
            }
        } else {
            itensServico = itensServicoServico.buscarPorId(idOs);
            if (itensServico != null) {
                cboTipo.setSelectedItem("Serviço");
                for (int i = 0; i < cboItem.getItemCount(); i++) {
                    Servico servico = (Servico) cboItem.getItemAt(i);
                    if (servico.getIdServico() == itensServico.getIdServico()) {
                        cboItem.setSelectedIndex(i);
                        break;
                    }
                }
                txtQuantidade.setText(String.valueOf(itensServico.getQuantidade()));
                txtValorUnitario.setText(String.format("%.2f", itensServico.getValorUnitario()));
                txtValorTotal.setText(String.format("%.2f", itensServico.getValorTotal()));
            }
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Using system default look and feel");
        }

        EventQueue.invokeLater(() -> {
            TelaOrcamentoForm dialog = new TelaOrcamentoForm(new JFrame(), true, 1); 
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
    }

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Object> cboItem;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblItem;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtValorTotal;
    private javax.swing.JTextField txtValorUnitario;
}

