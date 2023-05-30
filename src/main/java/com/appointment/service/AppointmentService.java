package com.appointment.service;

import java.util.List;
import java.util.UUID;

import com.appointment.controller.dto.AppointmentCardViewDto;
import com.appointment.controller.dto.AppointmentDashboardDto;
import com.appointment.controller.dto.AppointmentDto;
import com.appointment.controller.dto.AppointmentListViewDto;
import com.appointment.controller.dto.AppointmentPetDto;
import com.appointment.controller.dto.AppointmentVetDto;
import com.appointment.entities.Appointment;
import com.appointment.entities.AppointmentStatus;
import com.appointment.exceptions.AppointmentAlreadyExistsException;
import com.appointment.exceptions.AppointmentNotFoundException;
import com.appointment.exceptions.AppointmentStatusException;

public interface AppointmentService {
	public List<Appointment> getAllAppointments();
	public Appointment getAppointmentById(UUID appointmentId) throws AppointmentNotFoundException;
	public Appointment saveAppointment(AppointmentDto appointment) throws AppointmentAlreadyExistsException;
	public List<AppointmentCardViewDto> getAllAppointmentsByCard();
	
	
	public List<AppointmentListViewDto> getAllAppointmentsByList();
	public Appointment editAppointment(AppointmentDto appointment, UUID appointmentId) throws AppointmentNotFoundException;
	
	// There is another edit option, need to consult with the tech lead
	
	public String deleteAppointmentById(UUID id) throws AppointmentNotFoundException;
	public AppointmentStatus cancelAppointmentById(UUID id) throws AppointmentStatusException, AppointmentNotFoundException;
	
	// "Take up appointment" and "approve" appointment have the same functionality
	public AppointmentStatus approveAppointmentById(UUID id) throws AppointmentNotFoundException, AppointmentStatusException;
	public List<Appointment> viewPendingAppointments() throws AppointmentNotFoundException;
	public List<Appointment> viewUpcomingAppointments() throws AppointmentNotFoundException;
	public AppointmentStatus takeUpAppointment(UUID id) throws  AppointmentNotFoundException, AppointmentStatusException;
	

	public List<AppointmentVetDto> getAllVetDtos();
	public List<AppointmentDashboardDto> getAllDashboardDtos();
	
	public  List<Appointment> getAppointments(String vetName);
//  {
//      List<Appointments> appointments=null;
//      appointments=appointmentRepository.findByName(vetName);
//      return appointments;
//  }

	// Services for the dashboard team
	public int getTotalAppointmentCount();
	public int getApprovedAppointmentCount();
	public int getCancelledAppointCount();
	public int getPendingAppointmentCount();
	public int getCompletedAppointmentCount();
	
	// Service to pass DTO to pet details
	public List<AppointmentPetDto> getAppointmentPetDtos();
	
	
	
}
