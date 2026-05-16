package model;
 
public class Filme extends Video {
    private int duracao; // em minutos
 
    public Filme(int idVideo, String titulo, String descricao, int anoLancamento, int duracao) {
        super(idVideo, titulo, descricao, "filme", anoLancamento);
        this.duracao = duracao;
    }
 
    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }
 
    @Override
    public void exibirInfo() {
        System.out.println("Filme: " + getTitulo() + " (" + getAnoLancamento() + ") - Duração: " + duracao + " min");
    }
 
    public String getSituacao() {
        return duracao + " min";
    }
}

