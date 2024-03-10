package com.codegym.maccenter.model;

import lombok.Data;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;

	private long price;

	public String getPriceFormatted() {
		DecimalFormat decimalFormat = new DecimalFormat("#,###"); // Mẫu định dạng với dấu phân cách hàng đơn vị
		return decimalFormat.format(price);
	}

	private int weight;

	private String description;

	private String imageName;


}//create table mapping trong db
