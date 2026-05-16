package controller;
 
import dao.VideoDAO;
import model.Usuario;
import model.Video;
import view.Busca1;
import view.DetalhesVideo1;
 
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;
 
public class ControleBusca1 {
 
    private final Busca1 tela;
    private final Usuario usuario;
 
    public ControleBusca1(Busca1 tela, Usuario usuario) {
        this.tela = tela;
        this.usuario = usuario;
    }
 
    public List<Video> buscarVideos(String termo) {
        VideoDAO dao = new VideoDAO();
        try {
            return dao.buscarPorNome(termo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela,
                "Erro ao buscar videos: " + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
 
    public void abrirDetalhes(int indiceSelecionado, List<Video> resultados) {
        if (resultados == null || indiceSelecionado < 0 || indiceSelecionado >= resultados.size()) {
            JOptionPane.showMessageDialog(tela, "Selecione um video.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DetalhesVideo1 det = new DetalhesVideo1();
        det.setVideo(resultados.get(indiceSelecionado));
        det.setUsuario(usuario);
        det.setVisible(true);
    }
 
    public Usuario getUsuario() {
        return usuario;
    }
}
 