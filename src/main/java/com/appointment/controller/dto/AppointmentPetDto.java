package com.appointment.controller.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentPetDto {
	private UUID appointmentId;
	private String doctorName;
	private String deptName;
	private String appointmentDate;
	private String appointmentTime;
	private String appointmentStatus;
}
