package com.ufrgs.superfuturo.domain;

import java.math.BigDecimal;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "PRODUTOS")
public class Produto extends AbstractEntity<Long>{
    
    @Column(name = "nome",nullable = false,unique = true,length = 100)
    private String nome;

    @Column(nullable = false, columnDefinition = "DECIMAL(3,2) DEFAULT 0.00")
    private BigDecimal preco;

    private String codProduto; //cod de barras (?) 

    @ManyToOne //muitos produtos em 1 estoque
    @JoinColumn(name = "idEstotque_fk")
    private Estoque estoque;

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    /**
     * @return the codProduto
     */
    public String getCodProduto() {
        return codProduto;
    }

    /**
     * @return the estoque
     */
    public Estoque getEstoque() {
        return estoque;
    }

    /**
     * @return the preco
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * @param codProduto the codProduto to set
     */
    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

}