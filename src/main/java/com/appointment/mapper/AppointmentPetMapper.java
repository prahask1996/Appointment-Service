package com.appointment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.appointment.controller.dto.AppointmentPetDto;
import com.appointment.entities.Appointment;

@Mapper
public interface AppointmentPetMapper {
	@Mapping(source = "appointment.appointmentId", target = "appointmentId")
	@Mapping(source = "appointment.date", target = "appointmentDate")
	@Mapping(source = "appointment.time", target = "appointmentTime")
	@Mapping(source = "appointment.vetDetails.vetName", target = "doctorName")
	@Mapping(source = "appointment.vetDetails.deptName", target = "deptName")
	@Mapping(source = "appointment.appointmentStatus", target = "appointmentStatus")
	public AppointmentPetDto convertToPetDto(Appointment appointment);
}
