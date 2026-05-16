package controller;

import dao.UsuarioDAO;
import dao.Conexao;
import model.Usuario;
import view.Cadastro;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControleCadastro {
    private Cadastro tela3;
    
    public ControleCadastro(Cadastro tela3){
        this.tela3 = tela3;
    }
    
    public void salvarUsuario(){
        // pega os dados da tela
        String nome = tela3.getTxtNome().getText();
        String email = tela3.getTxtUsuario().getText(); // campo de login
        String senha = tela3.getTxtSenha().getText();

        // cria objeto Usuario (sem id, pois será gerado pelo banco)
        Usuario usuario = new Usuario(nome, email, senha);
        
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            UsuarioDAO dao = new UsuarioDAO(); // usa o DAO correto
            dao.cadastrarUsuario(usuario); // método do DAO

            JOptionPane.showMessageDialog(tela3, 
                "Usuário cadastrado com sucesso!", 
                "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(tela3, 
                "Erro ao cadastrar usuário: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
