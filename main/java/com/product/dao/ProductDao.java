package com.product.dao;

//import java.util.List;

import org.springframework.data.jpa.repository.*;

import com.product.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
//	List<Product> findByNameContaining(String productName);
}
