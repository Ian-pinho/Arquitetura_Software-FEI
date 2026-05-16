package controller;

import model.Usuario;
import view.Logado;

public class ControleLogado {
    private Logado tela4;
    private Usuario usuario;
    
    public ControleLogado(Logado tela4, Usuario usuario){
        this.tela4 = tela4;
        this.usuario = usuario;
    }
    
    public Usuario chamarAlteracao(){
        return usuario;
    }
    
    public Usuario chamarExclusao(){
        return usuario;
    }
}
