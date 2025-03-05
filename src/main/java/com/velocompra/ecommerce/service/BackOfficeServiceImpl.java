package com.velocompra.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackOfficeServiceImpl implements BackOfficeService{

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void listarProdutos() {
        produtoService.listarTodosProdutos();
    }

    @Override
    public void listarUsuarios() {
        usuarioService.listarTodosUsuarios();
    }
}
