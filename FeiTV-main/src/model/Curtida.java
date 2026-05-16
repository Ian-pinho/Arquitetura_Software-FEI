package model;

public class Curtida {
    private Usuario usuario;
    private Video video;

    public Curtida(Usuario usuario, Video video) {
        this.usuario = usuario;
        this.video = video;
    }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Video getVideo() { return video; }
    public void setVideo(Video video) { this.video = video; }
}

