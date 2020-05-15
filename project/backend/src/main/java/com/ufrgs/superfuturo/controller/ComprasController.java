package com.ufrgs.superfuturo.controller;

import com.ufrgs.superfuturo.service.ComprasService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/compras")
public class ComprasController {

    private ComprasService comprasService;

    @GetMapping("/cadastrar")
    public String cadastrar(){
        return "/compras/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        //nome da variável que espero na pg 
        //lista que será enviada para a pg
        //método do service q se comunica com o DAO
        model.addAttribute("compras",comprasService.buscarTodos());
        return "/compras/lista";
    }

}