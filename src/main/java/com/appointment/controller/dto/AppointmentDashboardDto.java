package com.appointment.controller.dto;

import com.appointment.entities.AppointmentStatus;

import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDashboardDto {
	
	private UUID appointmentId;
	private String date;
	private String time;
	private boolean status;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus appointmentStatus;
	
	private String vetName;
	private String petId;

    private String petName;
    private String state;
    private String city;
    private String country;
    private long pinCode;
    
    private String avatar;
	
	
	
	
	
}
