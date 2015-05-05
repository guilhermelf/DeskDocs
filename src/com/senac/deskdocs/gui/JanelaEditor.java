/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.deskdocs.gui;

import com.senac.deskdocs.Cliente;
import com.senac.deskdocs.Documento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.text.Document;

/**
 *
 * @author fraga
 */
public class JanelaEditor extends javax.swing.JFrame {

    /**
     * Creates new form JanelaEditor
     */
    public JanelaEditor() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_conteudo = new javax.swing.JTextArea();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        botaoCompartilhar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DeskDocs");

        txt_conteudo.setColumns(20);
        txt_conteudo.setRows(5);
        txt_conteudo.setEnabled(false);
        txt_conteudo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_conteudoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txt_conteudo);

        jMenu3.setText("Arquivo");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Novo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        botaoCompartilhar.setText("Compartilhar");
        botaoCompartilhar.setEnabled(false);
        botaoCompartilhar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCompartilharActionPerformed(evt);
            }
        });
        jMenu3.add(botaoCompartilhar);
        jMenu3.add(jSeparator2);

        jMenuItem5.setText("Mudar usuário");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem3.setText("Sair");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);
        jMenu3.add(jSeparator1);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Sobre");
        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        List<Documento> docs = Cliente.buscarDocumentos();
        
        if(docs != null) {
            JanelaAbrir abrir = new JanelaAbrir(this, rootPaneCheckingEnabled);

            abrir.listarDocumentos(docs);
            abrir.setVisible(rootPaneCheckingEnabled);
            
            String nome = abrir.getNomeDocumento();
            
            if(nome != null) {               
                Documento doc = Cliente.buscarDocumento(nome);
                this.setTitle(doc.getNome() + " - DeskDocs");
                this.txt_conteudo.setText(doc.getTexto().getTexto());
                this.txt_conteudo.setEnabled(true);
                this.botaoCompartilhar.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Esse usuário, não possui arquivos!");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String nome = JOptionPane.showInputDialog("Digite o nome do documento:");
        Documento doc = Cliente.criarDocumento(nome);
        
        if(doc != null) {
            this.txt_conteudo.setEnabled(rootPaneCheckingEnabled);
        
            this.setTitle(nome + " - DeskDocs");
            this.botaoCompartilhar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Impossível criar o arquivo, usuário já possui documento com esse nome!");
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Cliente.sair();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JanelaInicial inicial = new JanelaInicial();
        inicial.setVisible(rootPaneCheckingEnabled);
        
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void botaoCompartilharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCompartilharActionPerformed
        List<Documento> docs = Cliente.buscarDocumentos();
        
        if(docs != null) {
        
            String nome = JOptionPane.showInputDialog("Digite o e-mail do usuário com quem deseja compartilhar:");

            if(Cliente.compartilharDocumento(nome)) {
                JOptionPane.showMessageDialog(null, "Documento compartilhado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao compartilhar o documento!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Esse usuário, não possui arquivos para compartilhar!");
        }
    }//GEN-LAST:event_botaoCompartilharActionPerformed

    private void txt_conteudoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_conteudoKeyPressed
        Cliente.salvarDocumento(this.txt_conteudo.getText());
    }//GEN-LAST:event_txt_conteudoKeyPressed

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
            java.util.logging.Logger.getLogger(JanelaEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaEditor().setVisible(true);
            }
        });
    }
    
    public String getConteudo() {  
        return txt_conteudo.getText();
    }
    
    public void setConteudo(String conteudo) {
        txt_conteudo.setText(conteudo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem botaoCompartilhar;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextArea txt_conteudo;
    // End of variables declaration//GEN-END:variables
}
