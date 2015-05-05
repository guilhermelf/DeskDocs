/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.deskdoc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fraga
 */
public class UsuariosCadastrados extends UnicastRemoteObject implements IUsuariosCadastrados {
    private List<Usuario> usuarios;
    
    public UsuariosCadastrados() throws RemoteException {
        super();
        usuarios = new ArrayList<Usuario>();
    }
    
    @Override
    public boolean cadastrar(Usuario usuario) throws RemoteException {
        boolean novo = true;
        
        for (Usuario user : usuarios) {
            if(user.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                novo = false;
            }
        }
        if(novo) {
            this.usuarios.add(usuario);
            System.out.println("Usuário: " + usuario.getEmail() + " cadastrado com sucesso!");
            
            return novo;
        } else {
            return false;
        }
    }

    @Override
    public Usuario logar(String email, String senha) throws RemoteException {
        Usuario usuario = null;
        
        System.out.println(email + " - " + senha);
        
        for (Usuario user: usuarios) {           
            if(email.equalsIgnoreCase(user.getEmail()) && senha.equals(user.getSenha())) {
                usuario = user;
                break;
            }
        }       
        return usuario;
    }
    
    @Override
    public Usuario buscarUsuario(String email) {
        Usuario usuario = null;
        
        for(Usuario user: usuarios) {
            if(user.getEmail().equalsIgnoreCase(email)) {
                usuario = user;
                break;
            }
        }     
        return usuario;
    }

    @Override
    public List<Documento> buscarDocumentos(Usuario usuario) throws RemoteException {
        
        Usuario user = buscarUsuario(usuario.getEmail());
        
        List<Documento> documentos = user.getDocumentos();
        
        return documentos;
    }

    @Override
    public Documento criarDocumento(Usuario usuario, String nome) throws RemoteException {
        Documento doc = new Documento(nome);        
        
        Usuario user = buscarUsuario(usuario.getEmail());
        user.getDocumentos().add(doc);       
        
        System.out.println("Documento " + doc.getNome() + " criado.");
        return doc;
    } 

    @Override
    public Documento buscarDocumento(Documento documento, Usuario usuario) throws RemoteException {
        Usuario user = buscarUsuario(usuario.getEmail());
        
        List<Documento> documentos = user.getDocumentos();
        for (Documento temp : documentos) {
            if(temp.getNome().equalsIgnoreCase(documento.getNome())) {
                return temp;
            }
        }       
        return null;
    }

    @Override
    public boolean compartilharDocumento(Documento documento, String email) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
