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

            

//            if(cadastrar("teste@teste", "1234", "nome")) {
//                System.out.println("Usuário cadastrado com sucesso!");
//            } else {
//                System.out.println("Erro, esse e-mail já foi cadastrado!");
//            }
//            
//            Usuario usr = logar("teste@teste", "1234");          
//            
//            if(usr == null) {
//                System.out.println("Usuário não encontrado!");
//            } else {
//                Cliente.usuario = usr;
//                System.out.println(usuario.getNome() + " logado com sucesso!");
//            }
//            
//            Documento doc = criarDocumento("Bostinha");
//            
//            List<Documento> docs = buscarDocumentos();
//            System.out.println("Lista de documentos: " + docs.toString());
//            
//            Documento doc2 = buscarDocumento(docs.get(0));
//            System.out.println("Documento de nome: " + doc2.getNome() + " de conteudo: " + doc2.getTexto().getTexto() + " retornado com sucesso!");
//            
       
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
            return stub.logar(email, senha);
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
            Cliente.documento = stub.criarDocumento(Cliente.usuario, nome);
            return Cliente.documento;
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Documento buscarDocumento(Documento documento) {
        try {       
            return stub.buscarDocumento(documento, Cliente.usuario);
        
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