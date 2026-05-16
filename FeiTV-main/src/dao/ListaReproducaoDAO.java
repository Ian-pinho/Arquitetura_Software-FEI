package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ListaReproducao;
import model.Usuario;
import model.Video;
import model.Filme;
import model.Serie;

public class ListaReproducaoDAO {
    private Conexao conexao;

    public ListaReproducaoDAO() {
        this.conexao = new Conexao();
    }

    public List<ListaReproducao> listarPorUsuario(Usuario usuario) throws SQLException {
        List<ListaReproducao> listas = new ArrayList<>();
        String sql = "SELECT id, nome FROM lista_reproducao WHERE usuario_id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getIdUsuario());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listas.add(new ListaReproducao(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        usuario
                ));
            }
        }
        return listas;
    }

    public void criar(ListaReproducao lista) throws SQLException {
        String sql = "INSERT INTO lista_reproducao (nome, usuario_id) VALUES (?, ?)";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lista.getNomeLista());
            stmt.setInt(2, lista.getUsuario().getIdUsuario());
            stmt.executeUpdate();
        }
    }

    public void editar(ListaReproducao lista) throws SQLException {
        String sql = "UPDATE lista_reproducao SET nome = ? WHERE id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lista.getNomeLista());
            stmt.setInt(2, lista.getIdLista());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idLista) throws SQLException {
        String sql = "DELETE FROM lista_reproducao WHERE id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLista);
            stmt.executeUpdate();
        }
    }

    public List<Video> listarVideosDaLista(int idLista) throws SQLException {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT v.* FROM video v JOIN lista_video lv ON v.id = lv.video_id WHERE lv.lista_id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLista);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Video video;

                if (tipo.equalsIgnoreCase("filme")) {
                    video = new Filme(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("anoLancamento"),
                        rs.getInt("duracao")
                    );
                } else if (tipo.equalsIgnoreCase("serie")) {
                    video = new Serie(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("anoLancamento"),
                        rs.getInt("temporadas")
                    );
                } else {
                    continue;
                }
                videos.add(video);
            }
        }
        return videos;
    }

    public void adicionarVideo(int idLista, int idVideo) throws SQLException {
        String sql = "INSERT INTO lista_video (lista_id, video_id) VALUES (?, ?)";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLista);
            stmt.setInt(2, idVideo);
            stmt.executeUpdate();
        }
    }

    public void removerVideo(int idLista, int idVideo) throws SQLException {
        String sql = "DELETE FROM lista_video WHERE lista_id = ? AND video_id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLista);
            stmt.setInt(2, idVideo);
            stmt.executeUpdate();
        }
    }
}
