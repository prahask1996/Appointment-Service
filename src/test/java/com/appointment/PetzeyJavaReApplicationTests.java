//package com.appointment;
//import static org.hamcrest.CoreMatchers.nullValue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
// 
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
// 
//
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
// 
//
//import com.appointment.controller.dto.AppointmentCardViewDto;
//import com.appointment.controller.dto.AppointmentListViewDto;
//import com.appointment.entities.Appointment;
//import com.appointment.entities.AppointmentStatus;
//import com.appointment.exceptions.AppointmentAlreadyExistsException;
//import com.appointment.exceptions.AppointmentNotFoundException;
//import com.appointment.exceptions.AppointmentStatusException;
//import com.appointment.mapper.AppointmentListMapper;
//import com.appointment.repository.AppointmentRepository;
//import com.appointment.service.impl.AppointmentServiceImpl;
//
// 
//
//
//@SpringBootTest
//public class PetzeyJavaReApplicationTests {
//
// 
//
//    
//    @Mock
//    private AppointmentRepository appointmentRepository;
//
//    @InjectMocks
//    private AppointmentServiceImpl serviceImpl;
//
//
//    @Autowired
//    private AppointmentListMapper listMapper;
//
//    @Test
//    @Order(1)
//    public void test_getAllAppointments() {
//        List<Appointment> appointments=new ArrayList<Appointment>();
//        appointments.add(new Appointment("bk", null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, null, null));
//        appointments.add(new Appointment("ak", null, null, "15-01-2023", "14:00 PM", "Hi", "C://Document", true, null, null));
//
// 
//
//    
//    when(appointmentRepository.findAll()).thenReturn(appointments);
//    assertEquals(2,serviceImpl.getAllAppointments().size());
//    }
//
//    @Test
//    @Order(2)
//    public void test_getAppointmentById() throws AppointmentNotFoundException{
//
//        Appointment app=new Appointment("1L", null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, null, null);
//        String id="1L";
//        when(appointmentRepository.getById(id)).thenReturn(app);
//        assertEquals(app, serviceImpl.getAppointmentById(id));
//    }
//
//    @Test
//    @Order(3)
//    public void test_saveAppointment() throws AppointmentAlreadyExistsException {
//    Appointment app=new Appointment("bk", null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, null, null);
//    when(appointmentRepository.save(app)).thenReturn(app);
//    assertEquals(app, serviceImpl.saveAppointment(app));
//    }
//
// 
//
//    @Test
//    @Order(4)
//    public void test_editAppointment() throws AppointmentAlreadyExistsException, AppointmentNotFoundException {
//    Appointment app=new Appointment("1L", null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, null, null);
//    app.setStatus(false);
//    when(appointmentRepository.getById("1L")).thenReturn(app);
//    assertEquals(false, serviceImpl.editAppointment(app).isStatus());
//
//
//
//    }
//
//    @Test
//    @Order(9)
//    public void test_viewPendingAppointments() throws AppointmentNotFoundException {
//        List<Appointment> appointments=new ArrayList<Appointment>();
//        appointments.add(new Appointment("bk", null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, AppointmentStatus.PENDING, null));
//        appointments.add(new Appointment("ak", null, null, "15-01-2023", "14:00 PM", "Hi", "C://Document", true, AppointmentStatus.APPROVED, null));
//        //Appointment app=new Appointment("bk", null, null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, null, null);
//       when(appointmentRepository.findAll()).thenReturn(appointments);
//       assertEquals(1, serviceImpl.viewPendingAppointments().size());
//
// 
//
//    }
//
//    @Test
//    @Order(10)
//    public void test_viewUpcomingAppointments() throws AppointmentNotFoundException {
//        List<Appointment> appointments=new ArrayList<Appointment>();
//        appointments.add(new Appointment("bk", null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, AppointmentStatus.PENDING, null));
//        appointments.add(new Appointment("ak", null, null, "15-01-2023", "14:00 PM", "Hi", "C://Document", true, AppointmentStatus.COMPLETED, null));
//        //Appointment app=new Appointment("bk", null, null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, null, null);
//       when(appointmentRepository.findAll()).thenReturn(appointments);
//       assertEquals(0, serviceImpl.viewUpcomingAppointments().size());
//
// 
//
//    }
//
// 
//
//    
//    @Test
//    @Order(5)
//    public void test_deleteAppointmentById() throws AppointmentNotFoundException {
//        Appointment app=new Appointment("1L", null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, null, null);
//    String id="1L";
//    when(appointmentRepository.getById(id)).thenReturn(app);
//    assertEquals("Deleted :", serviceImpl.deleteAppointmentById(id));
//    }
//
//
//    @Test
//    @Order(6)
//    public void test_cancelAppointmentById() throws AppointmentStatusException, AppointmentNotFoundException {
//        Appointment app=new Appointment("1L", null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, AppointmentStatus.PENDING, null);
//        when(appointmentRepository.getById("1L")).thenReturn(app);
//        assertEquals(AppointmentStatus.CANCELLED, serviceImpl.cancelAppointmentById("1L"));
//    }
//
//
//    @Test
//    @Order(7)
//    public void test_approveAppointmentById() throws AppointmentNotFoundException, AppointmentStatusException {
//        Appointment app=new Appointment("1L", null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, AppointmentStatus.PENDING, null);
//     when(appointmentRepository.getById("1L")).thenReturn(app);
//     assertEquals(AppointmentStatus.APPROVED, serviceImpl.approveAppointmentById("1L"));
//    }
//
//    @Test
//    @Order(8)
//    public void test_takeUpAppointment() throws AppointmentNotFoundException, AppointmentStatusException {
//        Appointment app=new Appointment("1L", null, null, "17-01-2023", "15:00 PM", "Hello", "C://Document", true, AppointmentStatus.PENDING, null);
//        when(appointmentRepository.getById("1L")).thenReturn(app);
//        assertEquals(AppointmentStatus.APPROVED, serviceImpl.takeUpAppointment("1L"));
//    }
//
//    }