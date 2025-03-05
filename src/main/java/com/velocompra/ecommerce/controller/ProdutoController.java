package com.velocompra.ecommerce.controller;

import com.velocompra.ecommerce.model.Produto;
import com.velocompra.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos); // Retorna a lista de produtos com status 200 OK
    }

    // Buscar um produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);
        if (produto != null) {
            return ResponseEntity.ok(produto); // Retorna o produto com status 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 Not Found se o produto não existir
        }
    }

    // Adicionar um novo produto
    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.salvarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto); // Retorna o produto criado com status 201 Created
    }

    // Atualizar um produto existente
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado); // Retorna o produto atualizado com status 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 Not Found se o produto não existir
        }
    }

    // Deletar um produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        boolean deletado = produtoService.deletarProduto(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se o produto foi deletado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found se o produto não existir
        }
    }
}