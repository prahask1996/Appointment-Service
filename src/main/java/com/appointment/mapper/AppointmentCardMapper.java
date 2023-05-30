package com.appointment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.appointment.controller.dto.AppointmentCardViewDto;

import com.appointment.entities.Appointment;

@Mapper
public interface AppointmentCardMapper {
	
	@Mapping(source="appointment.petDetails.petName", target="petName")
	@Mapping(source="appointment.petDetails.petAge", target="petAge")
	@Mapping(source="appointment.petDetails.state", target="state")
	@Mapping(source="appointment.petDetails.country", target="country")
	@Mapping(source="appointment.petDetails.city", target="city")
	@Mapping(source="appointment.petDetails.pinCode", target="pinCode")
	@Mapping(source="appointment.petDetails.avatar", target="avatar")
	@Mapping(source="appointment.appointmentId", target = "appointmentId")
	public AppointmentCardViewDto convertToDto(Appointment appointment);
	
}
