package com.appointment.controller.dto;


import java.util.UUID;

import com.appointment.entities.Pet;
import com.appointment.entities.Vet;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentListViewDto {
	
	
	private UUID appointmentId;
	private String petName;
	private String petAge;
	private String deptName;
	private String vetName;
	private String date;
	private String time;
	private boolean status;


}
