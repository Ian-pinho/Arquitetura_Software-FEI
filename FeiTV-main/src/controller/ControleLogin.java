package controller;
 
import dao.UsuarioDAO;
import model.Usuario;
import view.Login;
import view.Principal1;
 
import java.sql.SQLException;
import javax.swing.JOptionPane;
 
public class ControleLogin {
    private final Login tela1;
 
    public ControleLogin(Login tela1) {
        this.tela1 = tela1;
    }
 
    public void loginUsuario() {
        String email = tela1.getTxtUsuario().getText();
        String senha = tela1.getTxtSenha().getText();
 
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuarioLogado = dao.login(email, senha);
 
            if (usuarioLogado != null) {
                JOptionPane.showMessageDialog(tela1,
                    "Login efetuado com sucesso!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
 
                Principal1 principal1 = new Principal1();
                principal1.setUsuario(usuarioLogado);
                principal1.setVisible(true);
                tela1.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(tela1,
                    "Login nao efetuado. Verifique usuario e senha.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela1,
                "Erro de conexao: " + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
 