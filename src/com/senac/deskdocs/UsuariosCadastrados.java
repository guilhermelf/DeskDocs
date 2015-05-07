/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.deskdocs;

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
            System.out.println("Usuário: " + usuario.getNome() + ", e-mail " + usuario.getEmail() + " e senha " + usuario.getSenha() + " cadastrado com sucesso!");
            
            return novo;
        } else {
            return false;
        }
    }

    @Override
    public Usuario logar(String email, String senha) throws RemoteException {
        Usuario usuario = null;
        
        for (Usuario user: usuarios) {           
            if(email.equalsIgnoreCase(user.getEmail()) && senha.equals(user.getSenha())) {
                usuario = user;
                System.out.println("Usuário: " + usuario.getNome() + " (e-mail " + usuario.getEmail() + ") logado com sucesso!");
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
        
        if(documentos.isEmpty())
            return null;
        
        return documentos;
    }

    @Override
    public Documento criarDocumento(Usuario usuario, String nome) throws RemoteException {
        Documento doc = new Documento(nome);        
        
        Usuario user = buscarUsuario(usuario.getEmail());
        
        for(Documento documento: user.getDocumentos()) {
            if(documento.getNome().equalsIgnoreCase(doc.getNome())) {
                doc = null;
                break;
            }
        }
        if(doc != null) {
            user.getDocumentos().add(doc);
            System.out.println("Documento " + doc.getNome() + " criado.");
        }       
        
        return doc;
    } 

    @Override
    public Documento buscarDocumento(String nomeDocumento, Usuario usuario) throws RemoteException {
        Usuario user = buscarUsuario(usuario.getEmail());
        
        List<Documento> documentos = user.getDocumentos();
        for (Documento temp : documentos) {
            if(temp.getNome().equalsIgnoreCase(nomeDocumento)) {
                return temp;
            }
        }       
        return null;
    }

    @Override
    public boolean compartilharDocumento(Documento documento, Usuario proprietario, String email) throws RemoteException {
        
        Usuario compartilhado = buscarUsuario(email);
        
        if(compartilhado != null) {
            Usuario usuario = buscarUsuario(proprietario.getEmail());
            Documento doc = buscarDocumento(documento.getNome(), usuario);
            
            for (Documento doc2: compartilhado.getDocumentos()) {
                if(doc2.getNome().equalsIgnoreCase(doc.getNome())) {
                    return false;
                }
            }
            
            compartilhado.getDocumentos().add(doc);
            System.out.println("Documento: " + doc.getNome() + " do usuário " + usuario.getNome() + " foi compartilhado com o usuário " + compartilhado.getNome());
            return true;
            
        } else {
            return false;
        }
    }

    @Override
    public void salvarDocumento(Documento documento, Usuario proprietario, String texto) throws RemoteException {
        Usuario usuario = buscarUsuario(proprietario.getEmail());    
        Documento doc = buscarDocumento(documento.getNome(), usuario);   
        doc.setTexto(texto);
    }

    @Override
    public Documento atualizarDocumento(Documento documento, Usuario usuario) throws RemoteException {
        Usuario user = buscarUsuario(usuario.getEmail());
        Documento doc = buscarDocumento(documento.getNome(), usuario);
        
        List<Documento> documentos = user.getDocumentos();
        for (Documento temp : documentos) {
            if(temp.getNome().equalsIgnoreCase(doc.getNome())) {
                System.out.println("Cliente: " + usuario.getNome() + " recebeu o documento: " + documento.getNome() + " atualizado com sucesso!");
                return temp;
            }
        }       
        return null;
    }
}
