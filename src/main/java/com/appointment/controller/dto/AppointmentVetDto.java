package com.appointment.controller.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentVetDto {
	
	private UUID appointmentId;
    private String petName;
    private String vetName;
    private String date;
    private String time;
    private boolean status;
}
