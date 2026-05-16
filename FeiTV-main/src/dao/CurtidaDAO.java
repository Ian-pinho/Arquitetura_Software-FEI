package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Video;
import model.Filme;
import model.Serie;

public class CurtidaDAO {
    private Conexao conexao = new Conexao();

    public void curtirVideo(int usuarioId, int videoId) throws SQLException {
        String sql = "INSERT INTO curtida (usuario_id, video_id) VALUES (?, ?)";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, videoId);
            stmt.executeUpdate();
        }
    }

    public void descurtirVideo(int usuarioId, int videoId) throws SQLException {
        String sql = "DELETE FROM curtida WHERE usuario_id = ? AND video_id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, videoId);
            stmt.executeUpdate();
        }
    }

    // Pode usar este nome para ficar igual ao controller
    public boolean isCurtido(int usuarioId, int videoId) throws SQLException {
        String sql = "SELECT 1 FROM curtida WHERE usuario_id = ? AND video_id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, videoId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public int contarCurtidas(int videoId) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM curtida WHERE video_id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, videoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public List<Video> listarCurtidos(int usuarioId) throws SQLException {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT v.* FROM video v JOIN curtida c ON v.id = c.video_id WHERE c.usuario_id = ?";
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
                    continue; // ignora tipos desconhecidos
                }

                videos.add(video);
            }
        }
        return videos;
    }
}
