package com.backend.happyhome.entities;

import java.time.LocalDateTime;

import com.backend.happyhome.entities.enums.Payment;
import com.backend.happyhome.entities.enums.TransactionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="admin_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AdminTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_transaction_id")
	private Long adminTransactionId;
	
	@OneToOne
	@JoinColumn(name="order_id" , nullable = false)
	private Order orderId;
	
	@Column(name = "amount", nullable = false)
	private Double amount;
	
	@Column(name = "payment_method", nullable = false)
	private Payment paymentMethod;
	
	@Column(name = "status", nullable = false)
	private TransactionStatus status;
	
	@Column(name = "timestamp")
	private LocalDateTime timestamp;
}
