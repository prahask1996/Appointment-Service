package com.appointment.controller.dto;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.appointment.entities.AppointmentStatus;
import com.appointment.entities.Pet;
import com.appointment.entities.Vet;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDto {
	
private UUID appointmentId;
	
	private String petName;
	
	private String vetName;
	
	private String date;
	private String time;
	private String city;
	private String country;
	private String state;
	private String moreDetails;
	private String documents;
	private boolean status;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus appointmentStatus;
	
	@ElementCollection
	private List<String> petIssues;

}
