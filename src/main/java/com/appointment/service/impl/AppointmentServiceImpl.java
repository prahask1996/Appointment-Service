package com.appointment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;

import com.appointment.controller.dto.AppointmentCardViewDto;
import com.appointment.controller.dto.AppointmentDashboardDto;
import com.appointment.controller.dto.AppointmentDto;
import com.appointment.controller.dto.AppointmentListViewDto;
import com.appointment.controller.dto.AppointmentPetDto;
import com.appointment.controller.dto.AppointmentVetDto;
import com.appointment.entities.Appointment;
import com.appointment.entities.AppointmentStatus;
import com.appointment.entities.Pet;
import com.appointment.entities.Vet;
import com.appointment.exceptions.AppointmentAlreadyExistsException;
import com.appointment.exceptions.AppointmentCannotTakeUpException;
import com.appointment.exceptions.AppointmentDoesNotExistsById;
import com.appointment.exceptions.AppointmentNotFoundException;
import com.appointment.exceptions.AppointmentStatusException;
import com.appointment.mapper.AppointmentCardMapper;
import com.appointment.mapper.AppointmentDashboardMapper;
import com.appointment.mapper.AppointmentListMapper;
import com.appointment.mapper.AppointmentPetMapper;
import com.appointment.mapper.AppointmentVetMapper;
import com.appointment.repository.AppointmentRepository;
import com.appointment.service.AppointmentService;

import ch.qos.logback.classic.Logger;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository aRep;

	@Autowired
	private AppointmentCardMapper cardMapper;

	@Autowired
	private AppointmentListMapper listMapper;

	@Autowired
	private AppointmentPetMapper appointmentPetMapper;

	@Autowired
	private AppointmentVetMapper vetMapper;

	@Autowired
	private AppointmentDashboardMapper dMapper;

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImpl.class);

	@Override
	public List<Appointment> getAllAppointments() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getAllAppointments");
		List<Appointment> appointments = aRep.getAll();
		if (appointments.isEmpty()) {
			LOGGER.error("There are no appointments available");
		}
		return appointments;
	}

	@Override
	public Appointment getAppointmentById(UUID appointmentId) throws AppointmentNotFoundException {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getAppointmentById");

		Appointment appointment = aRep.getAppointById(appointmentId);
		if (appointment == null) {
			LOGGER.error("Invalid Appointment Id");
			throw new AppointmentNotFoundException("Could not fetch appointment : Appointment not found");
		} else
			return appointment;
	}

	@Override
	public Appointment saveAppointment(AppointmentDto appointmentDto) throws AppointmentAlreadyExistsException {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> saveAppointment");
		Appointment appointment = new Appointment();

		appointment.setAppointmentId(appointment.getAppointmentId());

		appointment.setDate(appointmentDto.getDate());

		appointment.setTime(appointmentDto.getTime());

		appointment.setStatus(appointmentDto.isStatus());

		appointment.setAppointmentStatus(appointmentDto.getAppointmentStatus());

		appointment.setDocuments(appointmentDto.getDocuments());

		appointment.setMoreDetails(appointmentDto.getMoreDetails());

		Pet pet = new Pet();
		pet.setPetName(appointmentDto.getPetName());
		pet.setCity(appointmentDto.getCity());
		pet.setState(appointmentDto.getState());
		pet.setCountry(appointmentDto.getCountry());
		pet.setCountry(pet.getCountry());
		appointment.setPetDetails(pet);
		Vet vet = new Vet();
		vet.setVetName(appointmentDto.getVetName());
		appointment.setVetDetails(vet);
		return aRep.save(appointment);
	}

	@Override
	public Appointment editAppointment(AppointmentDto appointmentDto, UUID appointmentId)
			throws AppointmentNotFoundException {

		if (aRep.existsById(appointmentId)) {

			Appointment appointment = aRep.getAppointmentById(appointmentId);

			appointment.setDate(appointmentDto.getDate());
			appointment.setTime(appointmentDto.getTime());
			appointment.setStatus(appointmentDto.isStatus());
			appointment.setAppointmentStatus(appointmentDto.getAppointmentStatus());
			appointment.setDocuments(appointmentDto.getDocuments());
			appointment.setMoreDetails(appointmentDto.getMoreDetails());
			return aRep.save(appointment);
		} else
			throw new AppointmentNotFoundException();

	}

	@Override
	public String deleteAppointmentById(UUID id) throws AppointmentNotFoundException {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> deleteAppointmentsById");
		if (aRep.getAppointmentById(id) == null) {
			LOGGER.error("Invalid Appointment Id");
			throw new AppointmentNotFoundException("Cannot delete, appointment does not exist");
		}
		aRep.deleteById(id);
		return "Deleted";

	}

	@Override
	public AppointmentStatus cancelAppointmentById(UUID id)
			throws AppointmentStatusException, AppointmentNotFoundException {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> cancelAppointmentById");
		Appointment appointment = aRep.getById(id);
		if (appointment == null) {
			LOGGER.error("Invalid Appointment Id");
			throw new AppointmentNotFoundException("Could not fetch appointment : Appointment not found");
		}
		// Get the status
		AppointmentStatus status = appointment.getAppointmentStatus();

		// Check the status for "Pending"
		if (status == AppointmentStatus.PENDING || status == AppointmentStatus.APPROVED) {
			appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
			aRep.save(appointment);
		} else {
			LOGGER.error("Invalid Appointment Id");
			throw new AppointmentStatusException("Appointment Status Exception");
		}
		return AppointmentStatus.CANCELLED;
	}

	@Override
	public AppointmentStatus approveAppointmentById(UUID id)
			throws AppointmentNotFoundException, AppointmentStatusException {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> approveAppointmentById");
//		if (!aRep.existsById(id)) {
//			LOGGER.error("Invalid Appointment Id");
//			throw new AppointmentNotFoundException("Appointment with this Id not found");
//		}
		Appointment appointment = aRep.getById(id);
		if (appointment == null) {
			LOGGER.error("Invalid Appointment Id");
			throw new AppointmentNotFoundException("Could not fetch appointment : Appointment not found");
		}
		// Get the status
		AppointmentStatus status = appointment.getAppointmentStatus();

		// Check the status for "Pending"
		if (status != AppointmentStatus.PENDING) {
			throw new AppointmentStatusException("Appointment is not pending to be approved");
		}
		appointment.setAppointmentStatus(AppointmentStatus.APPROVED);
		aRep.save(appointment);

		return AppointmentStatus.APPROVED;

	}

	@Override
	public AppointmentStatus takeUpAppointment(UUID id)
			throws AppointmentNotFoundException, AppointmentStatusException {
		LOGGER.error("Appointment can't be taken up");
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> takeUpAppointment");
//		if (!aRep.existsById(id)) {
//			throw new AppointmentNotFoundException("Appointment with this Id not found");
//		}
		Appointment appointment = aRep.getById(id);
		if (appointment == null) {
			LOGGER.error("Invalid Appointment Id");
			throw new AppointmentNotFoundException("Could not fetch appointment : Appointment not found");
		}

		// Get the status
		AppointmentStatus status = appointment.getAppointmentStatus();

		// Check the status for "Pending"
		if (status != AppointmentStatus.PENDING) {
			throw new AppointmentStatusException("Appointment status is not 'Pending' to be approved");
		}
		appointment.setAppointmentStatus(AppointmentStatus.APPROVED);
		aRep.save(appointment);

		return AppointmentStatus.APPROVED;

	}

	@Override
	public List<AppointmentCardViewDto> getAllAppointmentsByCard() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getAllAppointmentsByCard");
		List<AppointmentCardViewDto> appointmentCardViewDto = new ArrayList<AppointmentCardViewDto>();
		List<Appointment> appointments = new ArrayList<>();
		appointments = aRep.findAll();

		for (Appointment appointment : appointments) {
			AppointmentCardViewDto dto = cardMapper.convertToDto(appointment);
			appointmentCardViewDto.add(dto);

		}
		return appointmentCardViewDto;
	}

	@Override
	public List<AppointmentListViewDto> getAllAppointmentsByList() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getAllAppointmentsByList");
		List<AppointmentListViewDto> appointmentListViewDto = new ArrayList<AppointmentListViewDto>();
		List<Appointment> appointments = new ArrayList<>();
		appointments = aRep.findAll();

		for (Appointment appointment : appointments) {
			AppointmentListViewDto dto = listMapper.convertToDto(appointment);
			appointmentListViewDto.add(dto);

		}
		return appointmentListViewDto;

	}

	@Override
	public List<Appointment> viewPendingAppointments() throws AppointmentNotFoundException {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> viewPendingAppointments");
		List<Appointment> pendingAppointmentlist = new ArrayList<Appointment>();
		List<Appointment> appointments = aRep.findAll();
		if (appointments.isEmpty()) {
			LOGGER.error("Appointments could not be found");
			throw new AppointmentNotFoundException("No Appointments available,...");
		} else {
			for (Appointment appointment : appointments) {
				if (appointment.getAppointmentStatus() == AppointmentStatus.PENDING) {
					pendingAppointmentlist.add(appointment);
				}
			}
		}
		return pendingAppointmentlist;
	}

	@Override
	public List<Appointment> viewUpcomingAppointments() throws AppointmentNotFoundException {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> ViewUpcomingAppointments");
		List<Appointment> upcomingAppointmentlist = new ArrayList<Appointment>();
		List<Appointment> appointments = aRep.findAll();
		if (appointments.isEmpty()) {
			LOGGER.error("No upcoming appointments");
			throw new AppointmentNotFoundException("No Appointments available,...");
		} else {
			for (Appointment appointment : appointments) {
				if (appointment.getAppointmentStatus() == AppointmentStatus.APPROVED) {
					upcomingAppointmentlist.add(appointment);
				}
			}
		}
		return upcomingAppointmentlist;
	}

	@Override

	public List<AppointmentVetDto> getAllVetDtos() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getAllVetDtos");
		List<AppointmentVetDto> appointmentVetDto = new ArrayList<AppointmentVetDto>();
		List<Appointment> appointments = new ArrayList<>();
		appointments = aRep.findAll();
		for (Appointment appointment : appointments) {
			AppointmentVetDto dto = vetMapper.convertToDto(appointment);
			appointmentVetDto.add(dto);

		}
		return appointmentVetDto;

	}

	@Override
	public List<AppointmentDashboardDto> getAllDashboardDtos() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getAllDashboardDtos");
		List<AppointmentDashboardDto> appointmentDashboardDto = new ArrayList<AppointmentDashboardDto>();
		List<Appointment> appointments = new ArrayList<>();
		appointments = aRep.getPendingAppointmentsForDashboard();
		for (Appointment appointment : appointments) {
			AppointmentDashboardDto dto = dMapper.convertToDto(appointment);
			appointmentDashboardDto.add(dto);

		}
		return appointmentDashboardDto;
	}

	@Override
	public List<Appointment> getAppointments(String vetName) {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getAppointments");
		List<Appointment> appointments = null;
		appointments = aRep.findByName(vetName);
		return appointments;
	}

	public int getTotalAppointmentCount() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getTotalAppointmentCount");
		int count = aRep.getTotalAppointmentCount();
		return count;
	}

	@Override
	public int getApprovedAppointmentCount() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getApprovedAppointmentCount");
		int approvedCount = aRep.getApprovedAppointmentCount();
		return approvedCount;
	}

	@Override
	public int getCancelledAppointCount() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getCancelledAppointCount");
		int cancelledCount = aRep.getCancelledAppointmentCount();
		return cancelledCount;
	}

	@Override
	public int getPendingAppointmentCount() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getPendingAppointmentCount");
		int pendingCount = aRep.getPendingAppointmentCount();
		return pendingCount;
	}

	@Override
	public int getCompletedAppointmentCount() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getCompletedAppointmentCount");
		int completedCount = aRep.getCompletedAppointmentCount();
		return completedCount;
	}

	@Override
	public List<AppointmentPetDto> getAppointmentPetDtos() {
		LOGGER.info("Class -> AppointmentServiceImpl, Method -> getAppointmentPetDtos");
		List<AppointmentPetDto> appointmentPetView = new ArrayList<AppointmentPetDto>();
		List<Appointment> appointments = new ArrayList<>();
		appointments = aRep.findAll();
		for (Appointment appointment : appointments) {
			AppointmentPetDto dto = appointmentPetMapper.convertToPetDto(appointment);
			appointmentPetView.add(dto);
		}
		return appointmentPetView;

	}

}