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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "orders", uniqueConstraints = @UniqueConstraint(columnNames = {
		"order_id" }), indexes = @Index(columnList = "product_name"))
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@XmlElement(name = "orderId")
	@Column(name = "order_id", nullable = false, unique = true)
	private Long orderId;

	@XmlElement(name = "customerName")
	@Column(name = "customer_name", nullable = false, length = 100)
	private String customerName;

	@XmlElement(name = "productName")
	@Column(name = "product_name", nullable = false, length = 100)
	private String productName;

	@XmlElement(name = "quantity")
	@Min(value = 1, message = "Quantity must be greater than 0")
	@Column(nullable = false)
	private int quantity;

	@XmlElement(name = "price")
	@Min(value = 1, message = "Price must be greater than 0")
	@Column(nullable = false)
	private double price;
}
