package com.velocompra.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O código do produto é obrigatório")
    @Size(max = 50, message = "O código do produto deve ter no máximo 50 caracteres")
    @Column(unique = true, nullable = false, length = 50)
    private String codigo;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(max = 255, message = "O nome do produto deve ter no máximo 255 caracteres")
    @Column(nullable = false, length = 255)
    private String nome;

    @NotNull(message = "O preço do produto é obrigatório")
    @Column(nullable = false)
    private Double preco;

    @Size(max = 2000, message = "A descrição deve ter no máximo 2000 caracteres")
    @Column(nullable = false, length = 2000) // Agora é NOT NULL
    private String descricao;

    @NotNull(message = "A quantidade do produto é obrigatória")
    @Column(nullable = false)
    private Integer quantidade;

    @NotBlank(message = "O status do produto é obrigatório")
    @Size(max = 50, message = "O status do produto deve ter no máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String status;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}