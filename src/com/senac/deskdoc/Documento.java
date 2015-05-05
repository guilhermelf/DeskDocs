/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.deskdoc;

import java.io.Serializable;

/**
 *
 * @author fraga
 */
public class Documento implements Serializable{
    private Texto texto;
    private String nome;
    
    public Documento() {
    }
    
    public Documento(String nome) {
        texto = new Texto();
        texto.setTexto("");
        this.nome = nome;
    }
    
    public Documento(String nome, String texto) {
        this.texto = new Texto(texto);
        this.nome = nome;
    }

    public Texto getTexto() {
        return texto;
    }

    public void setTexto(Texto texto) {
        this.texto = texto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    } 
}
