package com.appointment.controller.dto;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentCardViewDto {
	
	private UUID petId;
	private UUID appointmentId;
    private String petName;
    private String petAge;
    private String state;
    private String city;
    private String country;
    private long pinCode;
    
    private String avatar;

}
