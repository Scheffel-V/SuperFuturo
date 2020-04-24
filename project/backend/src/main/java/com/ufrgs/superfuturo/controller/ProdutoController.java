package com.ufrgs.superfuturo.controller;

import com.ufrgs.superfuturo.domain.Produto;
import com.ufrgs.superfuturo.service.ProdutoService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Produto produto){
        return "/produto/cadastro";
    }

    @GetMapping("/listar")
    public String listar(){
        return "/produto/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Produto produto){
        produtoService.salvar(produto);
        return "redirect:/produtos/cadastrar";
    }

}