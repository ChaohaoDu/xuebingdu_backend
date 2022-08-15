package com.xuebingdu.frontend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@Setter

public class Product {
	@Id
//	@SequenceGenerator(name = "productIdGenerator", sequenceName = "productSequence", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdGenerator")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String imageUrl;

// mappedBy productSize中所创建的对象名
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
//	private Set<ProductSize> productSize;
}
