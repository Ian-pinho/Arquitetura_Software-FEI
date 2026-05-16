package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Video;
import model.Filme;
import model.Serie;

public class VideoDAO {
    private Conexao conexao;

    public VideoDAO() {
        this.conexao = new Conexao();
    }

    public void cadastrarVideo(Video video) throws SQLException {
        String sql = "INSERT INTO video (titulo, descricao, anoLancamento, tipo, duracao, temporadas) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, video.getTitulo());
            stmt.setString(2, video.getDescricao());
            stmt.setInt(3, video.getAnoLancamento());
            stmt.setString(4, video.getTipo());
            if (video instanceof Filme) {
                stmt.setInt(5, ((Filme) video).getDuracao());
                stmt.setNull(6, Types.INTEGER);
            } else if (video instanceof Serie) {
                stmt.setNull(5, Types.INTEGER);
                stmt.setInt(6, ((Serie) video).getTemporadas());
            } else {
                stmt.setNull(5, Types.INTEGER);
                stmt.setNull(6, Types.INTEGER);
            }
            stmt.executeUpdate();
        }
    }

    public void atualizarVideo(Video video) throws SQLException {
        String sql = "UPDATE video SET titulo = ?, descricao = ?, anoLancamento = ?, tipo = ?, duracao = ?, temporadas = ? WHERE id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, video.getTitulo());
            stmt.setString(2, video.getDescricao());
            stmt.setInt(3, video.getAnoLancamento());
            stmt.setString(4, video.getTipo());
            if (video instanceof Filme) {
                stmt.setInt(5, ((Filme) video).getDuracao());
                stmt.setNull(6, Types.INTEGER);
            } else if (video instanceof Serie) {
                stmt.setNull(5, Types.INTEGER);
                stmt.setInt(6, ((Serie) video).getTemporadas());
            } else {
                stmt.setNull(5, Types.INTEGER);
                stmt.setNull(6, Types.INTEGER);
            }
            stmt.setInt(7, video.getIdVideo());
            stmt.executeUpdate();
        }
    }

    public void removerVideo(int id) throws SQLException {
        String sql = "DELETE FROM video WHERE id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Video buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM video WHERE id = ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String tipo = rs.getString("tipo");
                if (tipo.equalsIgnoreCase("filme")) {
                    return new Filme(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("anoLancamento"),
                        rs.getInt("duracao")
                    );
                } else if (tipo.equalsIgnoreCase("serie")) {
                    return new Serie(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("anoLancamento"),
                        rs.getInt("temporadas")
                    );
                }
            }
        }
        return null;
    }

    public List<Video> listarTodos() throws SQLException {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT * FROM video";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
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

    public List<Video> buscarPorNome(String nome) throws SQLException {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT * FROM video WHERE titulo ILIKE ?";
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
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
}
