package com.appointment;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.appointment.mapper.AppointmentCardMapper;



import com.appointment.mapper.AppointmentCardMapperImpl;
import com.appointment.mapper.AppointmentDashboardMapper;
import com.appointment.mapper.AppointmentDashboardMapperImpl;
import com.appointment.mapper.AppointmentListMapper;
import com.appointment.mapper.AppointmentListMapperImpl;
import com.appointment.mapper.AppointmentPetMapper;
import com.appointment.mapper.AppointmentPetMapperImpl;
import com.appointment.mapper.AppointmentVetMapper;
import com.appointment.mapper.AppointmentVetMapperImpl;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class PetzeyJavaReApplication {
  
	@Value(value = "${swagger.url}")   
	public String url;
	public static void main(String[] args) {
		SpringApplication.run(PetzeyJavaReApplication.class, args);
	}
	@Bean
	public AppointmentListMapper getAllAppointmentListMapper() {
		return new AppointmentListMapperImpl();
	}

	@Bean
	public AppointmentCardMapper getAllAppointmentCardMapper() {
		return new AppointmentCardMapperImpl();
	}

	@Bean
	public AppointmentVetMapper getAppointmentVetMapper() {
		return new AppointmentVetMapperImpl();
	}

	@Bean
	public AppointmentDashboardMapper getAppointmentDashboardMapper() {
		return new AppointmentDashboardMapperImpl();
	}

	@Bean
	public AppointmentPetMapper getAllAppointmentPetMapper() {
		return new AppointmentPetMapperImpl();

	}
	@Bean 
	public OpenAPI customOpenAPI() 
	{
		Server productionserver = new Server(); 
//	Server localserver = new Server(); 
	List<Server> servers = new ArrayList<>();
	productionserver.setUrl(url);   
//	localserver.setUrl(url);
	servers.add(productionserver);
//	 servers.add(localserver);    
	OpenAPI openAPI = new OpenAPI(); 
	openAPI.setServers(servers);   
	return openAPI; 
	}
	
}
