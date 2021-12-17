package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author agus.wijanarko
 *
 */
@Entity
@Table(name = ProductEntity.TABLE_NAME, uniqueConstraints = { @UniqueConstraint(columnNames = "product_code") })

public class ProductEntity implements Serializable {

	public static final String TABLE_NAME = "product";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(required = true, example = "1", description = "id data")
	protected Long id;

	@Schema(required = true, example = "product-1", description = "nama product")
	@Column(name = "product_name", nullable = false)
	private String productName;

	@Schema(required = true, example = "prod-1", description = "kode product")
	@Column(name = "product_code", nullable = false)
	private String productCode;

	@Schema(required = true, example = "cate-1", description = "kategori product")
	@Column(name = "category", nullable = false)
	private String category;

	@Schema(required = true, example = "brand-1", description = "brand product")
	@Column(name = "brand", nullable = false)
	private String brand;

	@Schema(required = true, example = "30.000", description = "harga product")
	@Column(name = "price", nullable = false)
	private Long price;

	@Schema(required = false, example = "active", description = "status")
	@Column(name = "status", nullable = false)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
