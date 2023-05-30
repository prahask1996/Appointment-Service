package com.appointment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.appointment.controller.dto.AppointmentDashboardDto;
import com.appointment.controller.dto.AppointmentListViewDto;
import com.appointment.entities.Appointment;

@Mapper
public interface AppointmentDashboardMapper {
	
	
	@Mapping(source="appointment.appointmentId",target="appointmentId")
	@Mapping(source="appointment.date",target="date")
	@Mapping(source="appointment.time",target="time")
	@Mapping(source="appointment.status",target="status")
	@Mapping(source ="appointment.petDetails.petName", target="petName")
	@Mapping(source="appointment.vetDetails.vetName", target="vetName")
	@Mapping(source="appointment.petDetails.state", target="state")
	@Mapping(source="appointment.petDetails.country", target="country")
	@Mapping(source="appointment.petDetails.city", target="city")
	@Mapping(source="appointment.petDetails.pinCode", target="pinCode")
	@Mapping(source="appointment.petDetails.avatar", target="avatar")
	
	public AppointmentDashboardDto convertToDto(Appointment appointment);
}
