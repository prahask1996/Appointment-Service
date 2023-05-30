package com.appointment.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID appointmentId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Pet petDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Vet vetDetails;
	
	private String date;
	private String time;
	private String moreDetails;
	private String documents;
	private boolean status;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus appointmentStatus;
	
	@ElementCollection
	private List<String> petIssues;
	
	
}
