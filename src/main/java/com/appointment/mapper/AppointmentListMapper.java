package com.appointment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.appointment.controller.dto.AppointmentListViewDto;
import com.appointment.entities.Appointment;

@Mapper
public interface AppointmentListMapper {
	
	

	@Mapping(source="appointment.appointmentId",target="appointmentId")
	@Mapping(source="appointment.date",target="date")
	@Mapping(source="appointment.time",target="time")
	@Mapping(source="appointment.status",target="status")
	@Mapping(source="appointment.petDetails.petName", target="petName")
	@Mapping(source="appointment.petDetails.petAge", target="petAge")
	@Mapping(source="appointment.vetDetails.deptName", target="deptName")
	@Mapping(source="appointment.vetDetails.vetName", target="vetName")
	
	public AppointmentListViewDto convertToDto(Appointment appointment);
	

}
