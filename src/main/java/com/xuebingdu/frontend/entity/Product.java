package com.xuebingdu.frontend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String imageUrl;


// mappedBy productSize中所创建的对象名
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
//	private Set<ProductSize> productSize;
}
