package com.zavis.fileprocessor.file_processor.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "orders", 
		uniqueConstraints = @UniqueConstraint(columnNames = {"order_id" }), 
		indexes = @Index(columnList = "product_name"))
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_id", nullable = false)
	private Long orderId;
	
	@Column(name="customer_name", nullable=false, length = 100)
	private String customerName;

	@Column(name = "product_name", nullable = false, length = 100)
	private String productName;

	@Min(value = 1, message = "Quantity must be greated than 0")
	@Column(nullable = false)
	private int quantity;

	@Min(value = 1, message = "Price must be greated than 0")
	@Column(nullable = false)
	private double price;
}
