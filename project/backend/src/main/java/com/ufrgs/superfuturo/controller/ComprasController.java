package com.ufrgs.superfuturo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/compras")
public class ComprasController {


    @GetMapping("/cadastrar")
    public String cadastrar(){
        return "/compras/cadastro";
    }

    @GetMapping("/listar")
    public String listar(){
        return "/compras/lista";
    }

}