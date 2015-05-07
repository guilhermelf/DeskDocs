/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.deskdocs;

import java.io.Serializable;

/**
 *
 * @author fraga
 */
public class Documento implements Serializable{
    private String texto;
    private String nome;
    
    public Documento() {
        this.texto = "";
    }
    
    public Documento(String nome) {
        this.nome = nome;
        this.texto = "";
    }
    
    public Documento(String nome, String texto) {
        this.texto = texto;
        this.nome = nome;

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    } 

}
