package com.appointment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.appointment.controller.dto.AppointmentVetDto;
import com.appointment.entities.Appointment;

@Mapper
public interface AppointmentVetMapper {
	
	
	
	@Mapping(source="appointment.appointmentId",target="appointmentId")
	@Mapping(source ="appointment.petDetails.petName", target="petName")
	@Mapping(source="appointment.vetDetails.vetName", target="vetName")
	@Mapping(source="appointment.date",target="date")
	@Mapping(source="appointment.time",target="time")
	@Mapping(source="appointment.status",target="status")
	
	public AppointmentVetDto convertToDto(Appointment appointment);

}
