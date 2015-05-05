/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.deskdoc;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author fraga
 */
public interface IDocumento extends Remote {
    public void getConteudo(String texto) throws RemoteException;
    public Texto setConteudo() throws RemoteException; 
}
