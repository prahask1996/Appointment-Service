package com.appointment.entities;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID petId;

    private String petName;
    private String petAge;
    private String petGender;
    private boolean petStatus;
    private String parentName;
    private String parentEmailId;
    private String parentPhoneNumber;
    
    // Address
    private String state;
    private String city;
    private String country;
    private long pinCode;
    
    private String avatar;

    @OneToMany
    private List<Appointment> appointmentsList;
}
