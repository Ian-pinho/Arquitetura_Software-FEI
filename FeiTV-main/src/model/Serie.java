package model;
 
public class Serie extends Video {
    private int temporadas;
 
    public Serie(int idVideo, String titulo, String descricao, int anoLancamento, int temporadas) {
        super(idVideo, titulo, descricao, "serie", anoLancamento);
        this.temporadas = temporadas;
    }
 
    public int getTemporadas() { return temporadas; }
    public void setTemporadas(int temporadas) { this.temporadas = temporadas; }
 
    @Override
    public void exibirInfo() {
        System.out.println("Série: " + getTitulo() + " (" + getAnoLancamento() + ") - Temporadas: " + temporadas);
    }
 
    public String getSituacao() {
        return temporadas + " temporada(s)";
    }
}

