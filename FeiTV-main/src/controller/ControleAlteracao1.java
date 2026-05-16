package controller;

import dao.UsuarioDAO;
import dao.Conexao;
import model.Usuario;
import view.Alteracao1;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;

public class ControleAlteracao1 {
    private final Alteracao1 tela6;
    private final Usuario usuario;
    
    public ControleAlteracao1(Alteracao1 tela6, Usuario usuario){
        this.tela6 = tela6;
        this.usuario = usuario;
    }
    
    public void atualizar(){
        // pega o email do usuário atual (login)
        String email = usuario.getEmail(); 
        // pega a nova senha digitada na tela
        String novaSenha = tela6.getTxtNovaSenha().getText();

        // cria um objeto Usuario atualizado
        Usuario usuarioAtualizado = new Usuario(
            usuario.getIdUsuario(), // mantém o mesmo ID
            usuario.getNome(),      // mantém o mesmo nome
            email,                  // mantém o mesmo email
            novaSenha               // atualiza a senha
        );

        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            UsuarioDAO dao = new UsuarioDAO(); // usa o padrão sem passar conn
            dao.atualizar(usuarioAtualizado);

            JOptionPane.showMessageDialog(tela6, 
                "Senha de Usuário atualizada com sucesso!", 
                "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(tela6, 
                "Falha de conexão: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
