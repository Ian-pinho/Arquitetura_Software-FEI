package controller;
 
import dao.ListaReproducaoDAO;
import model.ListaReproducao;
import model.Usuario;
import model.Video;
import view.ListasReproducao1;
 
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;
 
public class ControleListasReproducao1 {
 
    private ListasReproducao1 tela;
    private Usuario usuario;
 
    public ControleListasReproducao1(ListasReproducao1 tela, Usuario usuario) {
        this.tela = tela;
        this.usuario = usuario;
    }
 
    public List<ListaReproducao> carregarListas() {
        try {
            return new ListaReproducaoDAO().listarPorUsuario(usuario);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao carregar listas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
 
    public void criarLista(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Informe um nome.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            new ListaReproducaoDAO().criar(new ListaReproducao(0, nome.trim(), usuario));
            JOptionPane.showMessageDialog(tela, "Lista criada!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao criar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    public void editarLista(int idLista, String novoNome) {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Informe um nome.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            new ListaReproducaoDAO().editar(new ListaReproducao(idLista, novoNome.trim(), usuario));
            JOptionPane.showMessageDialog(tela, "Lista editada!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao editar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    public void excluirLista(int idLista) {
        int op = JOptionPane.showConfirmDialog(tela, "Deseja excluir esta lista?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (op != JOptionPane.YES_OPTION) return;
        try {
            new ListaReproducaoDAO().excluir(idLista);
            JOptionPane.showMessageDialog(tela, "Lista excluida!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao excluir: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    public List<Video> carregarVideosLista(int idLista) {
        try {
            return new ListaReproducaoDAO().listarVideosDaLista(idLista);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao carregar videos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
 
    public void removerVideoLista(int idLista, int idVideo) {
        try {
            new ListaReproducaoDAO().removerVideo(idLista, idVideo);
            JOptionPane.showMessageDialog(tela, "Video removido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao remover: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    public Usuario getUsuario() {
        return usuario;
    }
}
 