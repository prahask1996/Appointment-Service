package com.appointment.controller;

import java.util.UUID;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.controller.dto.AppointmentDto;
import com.appointment.entities.Appointment;
import com.appointment.exceptions.AppointmentAlreadyExistsException;
import com.appointment.exceptions.AppointmentCannotTakeUpException;
import com.appointment.exceptions.AppointmentDoesNotExistsById;
import com.appointment.exceptions.AppointmentNotFoundException;
import com.appointment.exceptions.AppointmentStatusException;
import com.appointment.service.AppointmentService;

@RestController
//@CrossOrigin("*")
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService service;

	// Get all the appointments
	@GetMapping("/appointment")
	public ResponseEntity<?> getAllAppointments() {
		ResponseEntity<?> response = null;
		response = new ResponseEntity<>(service.getAllAppointments(), HttpStatus.OK);
		return response;
	}

	// Get the appointment by ID
	@GetMapping("/appointment/{appointmentId}")
	public ResponseEntity<?> getAppointmentById(@PathVariable(value = "appointmentId") UUID appointmentId) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.getAppointmentById(appointmentId), HttpStatus.OK);
		} catch (AppointmentNotFoundException ex) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	// Add a new appointment
	@PostMapping("/appointment")
	public ResponseEntity<?> addAppointment(@RequestBody AppointmentDto appointment) throws AppointmentAlreadyExistsException {

		ResponseEntity<?> response = null;
		response = new ResponseEntity<>(service.saveAppointment(appointment), HttpStatus.OK);
		return response;
	}

	// Maps the Appointment as per the card view requirements
	@GetMapping("/view/card")
	public ResponseEntity<?> getAllAppointmentsByCard() {
		ResponseEntity<?> response = null;
		response = new ResponseEntity<>(service.getAllAppointmentsByCard(), HttpStatus.OK);
		return response;
	}

	// Maps the Appointment class as per the List view requirements
	@GetMapping("/view/list")
	public ResponseEntity<?> getAllAppointmentsByList() {
		ResponseEntity<?> response = null;
		response = new ResponseEntity<>(service.getAllAppointmentsByList(), HttpStatus.OK);
		return response;
	}

	// Edits the appointment, does not check for criterion
	@PutMapping("/appointment/{appointmentId}")
	public ResponseEntity<?> editAppointment(@RequestBody AppointmentDto appointment, @PathVariable UUID appointmentId) throws AppointmentNotFoundException {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.editAppointment(appointment, appointmentId), HttpStatus.CREATED);
		} catch (AppointmentNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	// Deletes the particular appointment by appointment ID
	@DeleteMapping("/appointment/{appointmentId}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "appointmentId") UUID appointmentId) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.deleteAppointmentById(appointmentId), HttpStatus.ACCEPTED);
		} catch (AppointmentNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	// Changes the appointmentStatus field in the appointment object to "Approved"
	@PutMapping("/appointment/approve/{id}")
	public ResponseEntity<?> approveAppointmentById(@PathVariable(value = "id") UUID id)
			throws AppointmentStatusException {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.approveAppointmentById(id), HttpStatus.ACCEPTED);
		} catch (AppointmentNotFoundException | AppointmentStatusException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;

	}

	//
	@GetMapping("/view/pending")
	public ResponseEntity<?> viewPendingAppointments() {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.viewPendingAppointments(), HttpStatus.OK);
		} catch (AppointmentNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping("/view/upcoming")
	public ResponseEntity<?> viewUpcomingAppointments() throws AppointmentNotFoundException {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.viewUpcomingAppointments(), HttpStatus.OK);
		} catch (AppointmentNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}
	
	// API to takeup appointment
	@PutMapping("/appointment/takeup/{id}")
	public void takeUpAppointment(@PathVariable(value = "id") UUID id)
			throws AppointmentNotFoundException, AppointmentStatusException, AppointmentDoesNotExistsById {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.takeUpAppointment(id), HttpStatus.OK);
		} catch (AppointmentNotFoundException | AppointmentStatusException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// APIs required for Dashboard team
	@GetMapping("/get/count/total")
	public ResponseEntity<?> getTotalCountOfAppointments() {
		ResponseEntity<?> response = new ResponseEntity<>(service.getTotalAppointmentCount(), HttpStatus.ACCEPTED);
		return response;
	}

	@GetMapping("/get/count/cancelled")
	public ResponseEntity<?> getCancelledCountOfAppointments() {
		ResponseEntity<?> response = new ResponseEntity<>(service.getCancelledAppointCount(), HttpStatus.ACCEPTED);
		return response;
	}

	@GetMapping("/get/count/approved")
	public ResponseEntity<?> getApprovedCountOfAppointments() {
		ResponseEntity<?> response = new ResponseEntity<>(service.getApprovedAppointmentCount(), HttpStatus.ACCEPTED);
		return response;
	}

	@GetMapping("/get/count/completed")
	public ResponseEntity<?> getCompletedCountOfAppointments() {
		ResponseEntity<?> response = new ResponseEntity<>(service.getCompletedAppointmentCount(), HttpStatus.ACCEPTED);
		return response;
	}

	@GetMapping("/get/count/pending")
	public ResponseEntity<?> getPendingCountOfAppointments() {
		ResponseEntity<?> response = new ResponseEntity<>(service.getPendingAppointmentCount(), HttpStatus.ACCEPTED);
		return response;
	}

	@GetMapping("/pet/dto")
	public ResponseEntity<?> getAppointmentDtoForPet() {
		ResponseEntity<?> response = new ResponseEntity<>(service.getAppointmentPetDtos(), HttpStatus.ACCEPTED);
		return response;
	}

	@PutMapping("/appointment/cancel/{id}")
	public ResponseEntity<?> cancelAppointmentById(@PathVariable(value = "id") UUID id)
			throws AppointmentStatusException {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.cancelAppointmentById(id), HttpStatus.ACCEPTED);
		} catch (AppointmentStatusException | AppointmentNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping("/vet/dto")
	public ResponseEntity<?> getAllVetDtos() {
		ResponseEntity<?> response = null;
		response = new ResponseEntity<>(service.getAllVetDtos(), HttpStatus.OK);
		return response;
	}

	@GetMapping("/dashboard/dto")
	public ResponseEntity<?> getAllDashboardDtos() {
		ResponseEntity<?> response = null;
		response = new ResponseEntity<>(service.getAllDashboardDtos(), HttpStatus.OK);
		return response;
	}

	@GetMapping("/view/appointments/{vetName}")
	public ResponseEntity<?> getData(@PathVariable(value = "vetName") String vetName) {
		ResponseEntity<?> responseEntity = null;
		responseEntity = new ResponseEntity<>(service.getAppointments(vetName), HttpStatus.OK);
		return responseEntity;
	}

}