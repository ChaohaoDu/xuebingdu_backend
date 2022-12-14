package com.xuebingdu.frontend.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "product_size")

public class ProductSize {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long productId;

	private String size;

	private BigDecimal unitPrice;

	private Boolean active;

	private Integer unitsInStock;

	private Date dateCreated;
	private Date lastUpdated;
}
