/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.deskdocs;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fraga
 */
public class Servidor {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            
            UsuariosCadastrados usuarios = new UsuariosCadastrados();
            Naming.rebind("Usuarios", usuarios);      
            
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
