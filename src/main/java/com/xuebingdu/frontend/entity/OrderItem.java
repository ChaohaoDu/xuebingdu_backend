package com.xuebingdu.frontend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import lombok.ToString;

@Entity
@Table(name="order_item")
@Getter
@Setter
@ToString

public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="image_url")
	private String imageUrl;

	@Column(name="unit_price")
	private BigDecimal unitPrice;

	@Column(name="quantity")
	private int quantity;

	@Column(name="name")
	private String name;

	@Column(name = "size")
	private String size;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
}








