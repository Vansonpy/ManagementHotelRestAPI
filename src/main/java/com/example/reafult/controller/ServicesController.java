package com.example.reafult.controller;

import java.util.List;

import javax.validation.Valid;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reafult.dto.ServicesDTO;
import com.example.reafult.entities.Services;
import com.example.reafult.services.ServiceService;

@RestController
@RequestMapping("/api/services")
public class ServicesController {
//	private Logger logger = Logger.getLogger(SalesController.class);

	@Autowired
	private ServiceService servicesService;

	@GetMapping(value = "/viewAllServices")
	public ResponseEntity<List<ServicesDTO>> viewAllServices() throws Exception {		
		try {
			List<ServicesDTO> listServicesDTO = servicesService.findAll();
			if (listServicesDTO.isEmpty()) {
				return new ResponseEntity<List<ServicesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<ServicesDTO>>(listServicesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ServicesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/viewAllServicesByName/{name}")
	public ResponseEntity<ServicesDTO> viewAllSaleByName(@PathVariable("name") String name) throws Exception {		
		try {
			ServicesDTO servicesDTO = servicesService.findByName(name);
			if (servicesDTO == null) {
				return new ResponseEntity<ServicesDTO>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<ServicesDTO>(servicesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ServicesDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/viewAllServicesBySpecies/{species}")
	public ResponseEntity<List<ServicesDTO>> viewAllSaleBySpecies(@PathVariable("species") String species)
			throws Exception {		
		try {
			List<ServicesDTO> listServicesDTO = servicesService.findBySpecies(species);
			if (listServicesDTO.isEmpty()) {
				return new ResponseEntity<List<ServicesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<ServicesDTO>>(listServicesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ServicesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/addServices")
	public ResponseEntity<ServicesDTO> addSales(@Valid @RequestBody Services servicesRequest) throws Exception {		
		try {
			ServicesDTO currentServices = servicesService.save(servicesRequest);
			return new ResponseEntity<ServicesDTO>(currentServices, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ServicesDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/updateServices/{id}")
	public ResponseEntity<ServicesDTO> doUpdatePage(@PathVariable("id") Integer id,
			@Valid @RequestBody Services servicesRequest) throws Exception {		
		try {
			ServicesDTO currentServices = servicesService.findById(id);

			if (currentServices == null || servicesRequest.getId() != id) {
				System.out.println("Service with id " + id + " not found");
				return new ResponseEntity<ServicesDTO>(HttpStatus.NOT_FOUND);
			}
			currentServices = servicesService.save(servicesRequest);
			return new ResponseEntity<ServicesDTO>(currentServices, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ServicesDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/deleteServices/{id}")
	public ResponseEntity<ServicesDTO> delete(@PathVariable(value = "id") Integer id, Services servicesRequest)
			throws Exception {	
		try {
			ServicesDTO serviceDTO = servicesService.findById(id);
			if (serviceDTO == null) {
				return new ResponseEntity<ServicesDTO>(HttpStatus.NO_CONTENT);
			}
			servicesService.delete(id);
			return new ResponseEntity<ServicesDTO>(serviceDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ServicesDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
