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
     * @param produtos the produtos to set
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }


    /**
     * @return the precoTotal
     */
    public BigDecimal getPrecoTotal() {
        
    	BigDecimal precoTotal = BigDecimal.ZERO;
    	for(int i = 0; i < this.produtos.size(); i++)
    		precoTotal = precoTotal.add(this.produtos.get(i).getPreco());
    	
    	return precoTotal;
    }

    /**
     * @return the produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * @return Success of inclusion
     */
    public boolean addProduto(Produto produto) {
        try
        {
        	produtos.add(produto);
        	return true;
        }
    	catch(Exception ex)
    	{
    		return false;
    	}
    	
    }
    
    /**
     * @return Success of removal
     */
    public boolean removeProduto(Produto produto) {
        try
        {
        	produtos.remove(produto);
        	return true;
        }
    	catch(Exception ex)
    	{
    		return false;
    	}
    	
    }
    
    /**
     * @return Success of removal
     */
    public boolean removeProdutoUsingCod(String strCodProduto) {
        try
        {
        	if(this.produtos == null)
        		return false;
        	if(this.produtos.size() == 0)
        		return false;
        	for(int i = 0; i < this.produtos.size();i++)
        	{
        		if(this.produtos.get(i).getCodProduto().equals(strCodProduto))
        		{
        			this.produtos.remove(produtos.get(i));
        			return true;
        		}
        	}
        	return false;
        }
    	catch(Exception ex)
    	{
    		return false;
    	}
    	
    }
    
    /**
     * @return the usuario
     */
    //public Usuario getUsuario() {
    //    return usuario;
    //}

    /**
     * @param usuario the usuario to set
     */
    //public void setUsuario(Usuario usuario) {
    //    this.usuario = usuario;
    //}

    /**
     * @param precoTotal the precoTotal to set
     */
    //public void setPrecoTotal(BigDecimal precoTotal) {
      //  this.precoTotal = precoTotal;
    //}
    
}