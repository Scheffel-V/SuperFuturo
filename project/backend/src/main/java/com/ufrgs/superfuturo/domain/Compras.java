package com.ufrgs.superfuturo.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "COMPRAS")
public class Compras extends AbstractEntity<Long>{
    
    
    @OneToMany(mappedBy = "compras") 
    private List<Produto> produtos;

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "usuario_id_fk")
    //private Usuario usuario;

    @Column(nullable = false, columnDefinition = "DECIMAL(3,2) DEFAULT 0.00")
    private BigDecimal precoTotal;

    /**
     * @param precoTotal the precoTotal to set
     */
    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * @param usuario the usuario to set
     */
    //public void setUsuario(Usuario usuario) {
    //    this.usuario = usuario;
    //}

    /**
     * @return the precoTotal
     */
    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    /**
     * @return the produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * @return the usuario
     */
    //public Usuario getUsuario() {
    //    return usuario;
    //}


}