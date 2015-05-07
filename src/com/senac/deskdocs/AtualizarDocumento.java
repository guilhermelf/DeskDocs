/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.deskdocs;

import com.senac.deskdocs.gui.JanelaEditor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Caret;

/**
 *
 * @author fraga
 */
public class AtualizarDocumento implements Runnable {
    private JanelaEditor editor;
    
    public AtualizarDocumento(JanelaEditor editor) {
        this.editor = editor;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Documento doc = Cliente.atualizarDocumento();
               
                if(doc != null && editor.isDigitando() == false) {
                    if(!doc.getTexto().equalsIgnoreCase(editor.getTxt_conteudo().getText())) {
                        editor.getTxt_conteudo().setText(doc.getTexto());
                        editor.getTxt_conteudo().setCaretPosition(doc.getTexto().length());
                    }
                }
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(AtualizarDocumento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
}
