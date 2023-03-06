package com.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	
//	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "productPrice")
	private String productPrice;

	public Product() {
//	super();
}

	

	public Product(String productName, String productPrice) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
	}



	public String getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}



	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + "]";
	}
	
	

}
