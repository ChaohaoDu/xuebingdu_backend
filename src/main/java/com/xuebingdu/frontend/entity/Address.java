package com.xuebingdu.frontend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="address")
@Getter
@Setter
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="street1")
	private String street1;

	@Column(name="street2")
	private String street2;

	@Column(name="city")
	private String city;

	@Column(name="state")
	private String state;

	@Column(name="country")
	private String country;

	@Column(name="zip_code")
	private String zipCode;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Order order;
}



