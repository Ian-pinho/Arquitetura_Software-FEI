package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Video;
import model.Filme;
import model.Serie;

public class FavoritoDAO {
    private Conexao conexao = new Conexao();

    public void adicionarFavorito(int usuarioId, int videoId) throws SQLException {
        String sql = "INSERT INTO favorito (usuario_id, video_id) VALUES (?, ?)";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, videoId);
            stmt.executeUpdate();
        }
    }

    public void removerFavorito(int usuarioId, int videoId) throws SQLException {
        String sql = "DELETE FROM favorito WHERE usuario_id = ? AND video_id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, videoId);
            stmt.executeUpdate();
        }
    }

    public boolean jaFavoritou(int usuarioId, int videoId) throws SQLException {
        String sql = "SELECT 1 FROM favorito WHERE usuario_id = ? AND video_id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, videoId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public List<Video> listarFavoritos(int usuarioId) throws SQLException {
        List<Video> favoritos = new ArrayList<>();
        String sql = "SELECT v.* FROM video v INNER JOIN favorito f ON v.id = f.video_id WHERE f.usuario_id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
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

                favoritos.add(video);
            }
        }
        return favoritos;
    }
}
