package com.appointment.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appointment.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID>{
	
	@Query("select a from Appointment a where a.appointmentStatus='PENDING'")
	public List<Appointment> getPendingAppointmentsForDashboard();
	
	@Query(value = "select a from Appointment a where a.vetDetails.vetName=:vetName")
	public List<Appointment> findByName(@Param (value="vetName") String vetName);
	
	@Query("select a from Appointment a where a.appointmentId = :appointmentId")
	public Appointment getAppointmentById(@Param(value = "appointmentId")UUID appointmentId);

	@Query("select count(a) from Appointment a")
	public int getTotalAppointmentCount();
	
	@Query("select count(a) from Appointment a where a.appointmentStatus = 'PENDING'")
	public int getPendingAppointmentCount();
	
	@Query("select count(a) from Appointment a where a.appointmentStatus = 'APPROVED'")
	public int getApprovedAppointmentCount();
	
	@Query("select count(a) from Appointment a where a.appointmentStatus = 'CANCELLED'")
	public int getCancelledAppointmentCount();
	
	@Query("select count(a) from Appointment a where a.appointmentStatus = 'COMPLETED'")
	public int getCompletedAppointmentCount();
	
	@Query(value = "select * from appointment",nativeQuery = true)
	List<Appointment> getAll();
	
	@Query(value = "select * from appointment where appointment_id = :appointmentId",nativeQuery = true)
	Appointment getAppointById(@Param(value = "appointmentId")UUID appointmentId);
	
	

}
