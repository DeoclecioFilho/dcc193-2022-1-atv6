package br.ufjf.dcc193.tomato;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @NotBlank(message = "É preciso um título!")
    private String titulo;
    @PositiveOrZero(message = "Tomatos tem que ser 0 ou maior !")
    private Integer tomatos;

    public Tarefa() {

    }

    public Tarefa(Long id, String titulo, Integer tomatos) {
        this.id = id;
        this.titulo = titulo;
        this.tomatos = tomatos;
    }

    public Tarefa(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Tarefa(Long id) {
        this(id, null, 0);
    }

    public Tarefa(String titulo) {
        this(null, titulo, 0);
    }

    public Tarefa(Integer tomatos) {
        this(null, null, tomatos);
    }

    public Integer getTomatos() {
        return tomatos;
    }

    public void setTomatos(Integer tomatos) {
        this.tomatos = tomatos;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Atividade [id=" + id + ", titulo=" + titulo + ", Tomatos=" + tomatos + "]";
    }
}
