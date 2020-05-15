package com.ufrgs.superfuturo.domain;

import java.util.List;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "ESTOQUES")
public class Estoque extends AbstractEntity<Long>{
    
    
    @OneToMany(mappedBy = "estoque") //muitos produtos em 1 departamento
    private List<Produto> produtos;

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    /**
     * @return the produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }
   
    
}