package br.ufjf.dcc193.tomato;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @NotBlank(message = "Digite um título!")
    private String titulo;
    @PositiveOrZero(message = "Digite 0 ou mais tomatos!")
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

    public Atividade(Long id, @NotBlank(message = "Digite um título!") String titulo,
            @PositiveOrZero(message = "Digite 0 ou mais tomatos!") Integer tomatos) {
        this.id = id;
        this.titulo = titulo;
        this.tomatos = tomatos;
    }

    public Atividade(String titulo, Integer tomato) {
        this(null, titulo, tomato);
    }

    public Atividade() {
        this(null, null, null);
    }

    @Override
    public String toString() {
        return "Atividade [id=" + id + ", titulo=" + titulo + ", tomatos=" + tomatos + "]";
    }

}
