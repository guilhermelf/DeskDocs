package com.senac.deskdocs;

import com.senac.deskdocs.gui.JanelaInicial;
import static java.lang.System.exit;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    private static Usuario usuario;
    private static Documento documento;
    private static IUsuariosCadastrados stub;
    
    public Cliente() {       
    }
    
    public static void main(String[] args) throws RemoteException {
        try {
            stub = (IUsuariosCadastrados) Naming.lookup("Usuarios");
            
            JanelaInicial inicial = new JanelaInicial();
            inicial.setVisible(true);         
       
        } catch (NotBoundException | MalformedURLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean cadastrar(String email, String senha, String nome) {          
        try {                                  
            return stub.cadastrar(new Usuario(email, senha, nome));           
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }       
    }
    
    public static Usuario logar(String email, String senha) {
        try {      
            Cliente.usuario = stub.logar(email, senha);
            return Cliente.usuario;
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }       
    } 
    
    public static List<Documento> buscarDocumentos() {
        try {       
            return stub.buscarDocumentos(Cliente.usuario);
        
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;    
        }
    } 
    
    public static Documento criarDocumento(String nome) {
        try {
            Documento doc = stub.criarDocumento(Cliente.usuario, nome);
            Cliente.documento = doc;          
            return doc;
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Documento buscarDocumento(String nomeDocumento) {
        try {       
            return stub.buscarDocumento(nomeDocumento, Cliente.usuario);
        
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;    
        }
    } 
    
    public static boolean compartilharDocumento(String email) {
        try {                
            return stub.compartilharDocumento(Cliente.documento, Cliente.usuario, email);
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }     
    }
    
    public static void salvarDocumento(String texto) {
        try {                
            stub.salvarDocumento(Cliente.documento, Cliente.usuario, texto);
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static Documento atualizarDocumento() {
        try {       
            Documento doc = stub.atualizarDocumento(Cliente.documento, Cliente.usuario);
            Cliente.documento = doc;
            return doc;
        
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;    
        }
    }
    
    public static void sair() {
        exit(0);
    }  

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Cliente.usuario = usuario;
    }

    public static Documento getDocumento() {
        return documento;
    }

    public static void setDocumento(Documento documento) {
        Cliente.documento = documento;
    } 
}