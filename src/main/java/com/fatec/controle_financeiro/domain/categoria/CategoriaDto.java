package com.fatec.controle_financeiro.domain.categoria;

public class CategoriaDto {
    private Long id;

    private String descricao;

    private Boolean ativo;

    public CategoriaDto(){

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Boolean getAtivo(){
        return ativo;
    }

    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }
}
