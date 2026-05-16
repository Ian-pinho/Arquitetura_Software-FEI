package controller;

import dao.UsuarioDAO;
import dao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import view.Exclusao;

public class ControleExclusao {
    private Exclusao tela5;
    private Usuario usuario;
    
    public ControleExclusao(Exclusao tela5, Usuario usuario){
        this.tela5 = tela5;
        this.usuario = usuario;
    }
    
    public void remover(){
        int option = JOptionPane.showConfirmDialog(tela5, 
                "Deseja realmente excluir o cadastro?",
                "Aviso", JOptionPane.YES_NO_OPTION);
        
        if(option == JOptionPane.YES_OPTION){
            Conexao conexao = new Conexao();
            try{
                Connection conn = conexao.getConnection();
                UsuarioDAO dao = new UsuarioDAO(); // usa o DAO correto
                dao.remover(usuario);
                
                JOptionPane.showMessageDialog(tela5, 
                        "Usuário removido com sucesso!", 
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(tela5, 
                        "Falha de conexão: " + e.getMessage(), 
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
