package com.example.reafult.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reafult.dto.ServicesDTO;
import com.example.reafult.entities.Sales;
import com.example.reafult.entities.Services;
import com.example.reafult.repository.SaleRepository;
import com.example.reafult.repository.ServiceRepository;

@Service
@Transactional
public class ServiceService {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private SaleRepository saleRepository;
	
	public ServicesDTO findById(final Integer id) {
		Services service = serviceRepository.findById(id).get();
		ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
		servicesDTO.setSaleId(service.getSale().getId());
		return servicesDTO;
	}
	
	public List<ServicesDTO> findAll() {
		List<Services> listServices = serviceRepository.findAll();
		List<ServicesDTO> listServicesDTO = new ArrayList<ServicesDTO>();
		for (Services service : listServices) {
			ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
			servicesDTO.setSaleId(service.getSale().getId());
		}
		return listServicesDTO;
	}
	
	public List<ServicesDTO> findBySpecies(String species) {
		List<Services> listServices = serviceRepository.findBySpecies(species);
		List<ServicesDTO> listServicesDTO = new ArrayList<ServicesDTO>();
		for (Services service : listServices) {
			ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
			servicesDTO.setSaleId(service.getSale().getId());
		}
		return listServicesDTO;
	}
	
	public ServicesDTO findByName(String name) {
		Services service = serviceRepository.findByName(name);
		ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
		servicesDTO.setSaleId(service.getSale().getId());
		return servicesDTO;
	}
	
	public ServicesDTO save(Services services) {
		Services service = serviceRepository.save(services);
		Sales sale = saleRepository.findById(service.getSale().getId()).get();
		sale.getServices().add(service);
		service.setSale(sale);
		ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
		servicesDTO.setSaleId(service.getSale().getId());
		return servicesDTO;
	}
	
	public void delete(final Integer id) {
		Services service = serviceRepository.findById(id).get();
		if (service != null) {
			serviceRepository.delete(service);
		}
	}
}
