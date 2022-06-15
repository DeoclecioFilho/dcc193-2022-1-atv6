package br.ufjf.dcc193.tomato;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private Integer tomatos;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getTomatos() {
        return tomatos;
    }
    public void setTomatos(Integer tomatos) {
        this.tomatos = tomatos;
    }
    public Atividade(Long id, String titulo, Integer tomatos) {
        this.id = id;
        this.titulo = titulo;
        this.tomatos = tomatos;
    }
    @Override
    public String toString() {
        return "Atividade [id=" + id + ", titulo=" + titulo + ", tomatos=" + tomatos + "]";
    }
    
    
}
