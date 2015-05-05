/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.deskdocs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author fraga
 */
public interface IUsuariosCadastrados extends Remote{

    /**
     *
     * @param usuario
     * @return
     * @throws RemoteException
     */
    public boolean cadastrar(Usuario usuario) throws RemoteException;
    public Usuario logar(String email, String senha) throws RemoteException;
    public Usuario buscarUsuario(String email) throws RemoteException;
    public List<Documento> buscarDocumentos(Usuario usuario) throws RemoteException;
    public Documento buscarDocumento(String nomeDocumento, Usuario usuario) throws RemoteException;
    public Documento criarDocumento(Usuario usuario, String nome) throws RemoteException;
    public boolean compartilharDocumento(Documento documento, String email) throws RemoteException;
}
