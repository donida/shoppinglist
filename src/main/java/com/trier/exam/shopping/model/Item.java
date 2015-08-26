package com.trier.exam.shopping.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "item")
public class Item implements Serializable {

	private static final long serialVersionUID = -1864128263363630146L;

	@Id
	@SequenceGenerator(name = "itemidseq", sequenceName = "itemidseq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemidseq")
	private Long id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", nullable = false, updatable = true)
	private Product product;
	private Double price;
	private Boolean purchased;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getPurchased() {
		return purchased;
	}

	public void setPurchased(Boolean purchased) {
		this.purchased = purchased;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((purchased == null) ? 0 : purchased.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (purchased == null) {
			if (other.purchased != null)
				return false;
		} else if (!purchased.equals(other.purchased))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", product=" + product + ", price=" + price + ", purchased=" + purchased + "]";
	}

}
