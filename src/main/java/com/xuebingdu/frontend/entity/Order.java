package com.xuebingdu.frontend.entity;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "order_tracking_number")
	private String orderTrackingNumber;

	@Column(name = "total_quantity")
	private int totalQuantity;

	@Column(name = "product_total_price")
	private BigDecimal productTotalPrice;

	@Column(name = "shipping")
	private BigDecimal shipping;

	@Column(name = "status")
	private String status;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private Set<OrderItem> orderItems = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	public void add(OrderItem item) {
		if (item != null) {
			if (orderItems == null) {
				orderItems = new HashSet<>();
			}

			orderItems.add(item);
			item.setOrder(this);
		}
	}

	@Override
	public String toString() {
		return "Order{" + "id=" + id + ", orderTrackingNumber='" + orderTrackingNumber + '\'' + ", totalQuantity="
		   + totalQuantity + ", productTotalPrice=" + productTotalPrice + ", shipping=" + shipping + ", status='"
		   + status + '\'' + ", dateCreated=" + dateCreated + ", lastUpdated=" + lastUpdated + ", orderItems="
		   + orderItems + ", customer=" + customer + ", address=" + address + '}';
	}
}





