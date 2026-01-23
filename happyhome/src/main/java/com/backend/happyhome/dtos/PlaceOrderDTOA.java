package com.backend.happyhome.dtos;

import java.time.LocalDateTime;

import com.backend.happyhome.entities.AdminTransaction;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.enums.Priority;
import com.backend.happyhome.entities.enums.Status;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDTOA {

	@NotNull
	private Long consumerId;
	
	@NotNull
	private Long vendorId;
	
	@NotNull
	private Long serviceId;	
	
	@NotNull
	private LocalDateTime timeSlot;
	
	@NotNull
	private Double orderPrice;
	
	private Status status = Status.NOT_ASSIGNED;
	
	private Priority priority = Priority.NORMAL;
	
//	private Long consumerTransactionId;
//	
//	private Long adminTransactionId;
	
	
}
