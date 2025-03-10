package com.velocompra.ecommerce.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoDTO {

    private String codigo;
    private String nome;
    private Double preco;

    @Size(max = 2000, message = "A descrição deve ter no máximo 2000 caracteres")
    private String descricao;
    private Integer quantidade;
    private String status;

    public ProdutoDTO() {

    }

    public ProdutoDTO(String codigo, String nome, Double preco, String descricao ,Integer quantidade, String status) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.status = status;
    }

}
