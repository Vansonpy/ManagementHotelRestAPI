package com.example.reafult.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reafult.dto.SalesDTO;
import com.example.reafult.dto.ServicesDTO;
import com.example.reafult.entities.Customer;
import com.example.reafult.entities.Rooms;
import com.example.reafult.entities.Sales;
import com.example.reafult.entities.Services;
import com.example.reafult.entities.Users;
import com.example.reafult.repository.CustomerRepository;
import com.example.reafult.repository.RoomRepository;
import com.example.reafult.repository.SaleRepository;
import com.example.reafult.repository.SalesCustomRepository;
import com.example.reafult.repository.UserRepository;

@Service
@Transactional
public class SalesService {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private SalesCustomRepository saleCustomRepository;

	public List<SalesDTO> findByMothOfCheckinDate(Date startDate, Date endDate) {
		List<Sales> listSale = saleCustomRepository.findByMothInCheckinDate(startDate, endDate);
		List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
		for (Sales sale : listSale) {
			SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
			if (sale.getCustomer().getUserName()==null) {
				salesDTO.setUserName(sale.getCustomer().getEmail());
			} else {
				salesDTO.setUserName(sale.getCustomer().getUserName());
			}
			salesDTO.setRoomName(sale.getRoom().getRoomName());
			salesDTO.setPrice(sale.getRoom().getPrice());
			salesDTO.setType(sale.getRoom().getType());
			Set<Services> listService = sale.getServices();
			if (listService != null) {
				List<ServicesDTO> listServicesDTO = new ArrayList<ServicesDTO>();
				for (Services service : listService) {
					ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
					listServicesDTO.add(servicesDTO);
				}
				salesDTO.setServiceDTO(listServicesDTO);
			}

			listSaleDTO.add(salesDTO);
		}
		return listSaleDTO;
	}

	public List<SalesDTO> findByStatus(Integer status) {
		List<Sales> listSale = saleRepository.findByStatus(status);
		List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
		for (Sales sale : listSale) {
			SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
			if (sale.getCustomer().getUserName()==null) {
				salesDTO.setUserName(sale.getCustomer().getEmail());
			} else {
				salesDTO.setUserName(sale.getCustomer().getUserName());
			}
			salesDTO.setRoomName(sale.getRoom().getRoomName());
			salesDTO.setPrice(sale.getRoom().getPrice());
			salesDTO.setType(sale.getRoom().getType());
			Set<Services> listService = sale.getServices();
			if (listService != null) {
				List<ServicesDTO> listServicesDTO = new ArrayList<ServicesDTO>();
				for (Services service : listService) {
					ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
					listServicesDTO.add(servicesDTO);
				}
				salesDTO.setServiceDTO(listServicesDTO);
			}

			listSaleDTO.add(salesDTO);
		}
		return listSaleDTO;
	}

	public SalesDTO findById(final Integer id) {
		Sales sale = saleRepository.findById(id).get();
		SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
		if (sale.getCustomer().getUserName()==null) {
			salesDTO.setUserName(sale.getCustomer().getEmail());
		} else {
			salesDTO.setUserName(sale.getCustomer().getUserName());
		}
		salesDTO.setRoomName(sale.getRoom().getRoomName());
		salesDTO.setPrice(sale.getRoom().getPrice());
		salesDTO.setType(sale.getRoom().getType());
		Set<Services> listService = sale.getServices();
		if (listService != null) {
			List<ServicesDTO> listServicesDTO = new ArrayList<ServicesDTO>();
			for (Services service : listService) {
				ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
				listServicesDTO.add(servicesDTO);
			}
			salesDTO.setServiceDTO(listServicesDTO);
		}
		return salesDTO;
	}

	public List<SalesDTO> findByRoomName(String roomName) {
		Rooms room = roomRepository.findByRoomName(roomName);
		Set<Sales> listSale = room.getSales();
		List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
		for (Sales sale : listSale) {
			if (sale.getStatus() == 0) {
				SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
				if (sale.getCustomer().getUserName()==null) {
					salesDTO.setUserName(sale.getCustomer().getEmail());
				} else {
					salesDTO.setUserName(sale.getCustomer().getUserName());
				}
				salesDTO.setRoomName(sale.getRoom().getRoomName());
				salesDTO.setPrice(sale.getRoom().getPrice());
				salesDTO.setType(sale.getRoom().getType());
				Set<Services> listService = sale.getServices();
				if (listService != null) {
					List<ServicesDTO> listServicesDTO = new ArrayList<ServicesDTO>();
					for (Services service : listService) {
						ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
						listServicesDTO.add(servicesDTO);
					}
					salesDTO.setServiceDTO(listServicesDTO);
				}

				listSaleDTO.add(salesDTO);
			}
		}
		return listSaleDTO;
	}

//-----------------------------------------------------
	public List<SalesDTO> findByUserName(String userName) {
		Users user = userRepository.findByUserName(userName);
		Customer customer = customerRepository.findByEmail(user.getEmail());
		List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
		if (customer != null) {
			Set<Sales> listSale = customer.getSales();
			if (listSale != null) {
				for (Sales sale : listSale) {
					SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
					if (customer.getUserName()==null) {
						salesDTO.setUserName(customer.getEmail());
					} else {
						salesDTO.setUserName(customer.getUserName());
					}
					salesDTO.setRoomName(sale.getRoom().getRoomName());
					salesDTO.setPrice(sale.getRoom().getPrice());
					salesDTO.setType(sale.getRoom().getType());
					Set<Services> listService = sale.getServices();
					if (listService != null) {
						List<ServicesDTO> listServicesDTO = new ArrayList<ServicesDTO>();
						for (Services service : listService) {
							ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
							listServicesDTO.add(servicesDTO);
						}
						salesDTO.setServiceDTO(listServicesDTO);
					}
					listSaleDTO.add(salesDTO);
				}
			}
		}
		return listSaleDTO;
	}

	public List<SalesDTO> findByUserNameAndStatus(String userName, Integer status) {
		Users user = userRepository.findByUserName(userName);
		Customer customer = customerRepository.findByEmail(user.getEmail());
		List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
		if (customer != null) {
			Set<Sales> listSale = customer.getSales();
			if (listSale != null) {
				for (Sales sale : listSale) {
					if (sale.getStatus() == status) {
						SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
						if (customer.getUserName()==null) {
							salesDTO.setUserName(customer.getEmail());
						} else {
							salesDTO.setUserName(customer.getUserName());
						}
						salesDTO.setRoomName(sale.getRoom().getRoomName());
						salesDTO.setPrice(sale.getRoom().getPrice());
						salesDTO.setType(sale.getRoom().getType());
						Set<Services> listService = sale.getServices();
						if (listService != null) {
							List<ServicesDTO> listServicesDTO = new ArrayList<ServicesDTO>();
							for (Services service : listService) {
								ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
								listServicesDTO.add(servicesDTO);
							}
							salesDTO.setServiceDTO(listServicesDTO);
						}
						listSaleDTO.add(salesDTO);

					}

				}
			}
		}

		return listSaleDTO;
	}

	public List<SalesDTO> findAll() {
		List<Sales> listSale = saleRepository.findAll();
		List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
		for (Sales sale : listSale) {
			SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
			if (sale.getCustomer().getUserName()==null) {
				salesDTO.setUserName(sale.getCustomer().getEmail());
			} else {
				salesDTO.setUserName(sale.getCustomer().getUserName());
			}
			salesDTO.setRoomName(sale.getRoom().getRoomName());
			salesDTO.setPrice(sale.getRoom().getPrice());
			salesDTO.setType(sale.getRoom().getType());
			Set<Services> listService = sale.getServices();
			if (listService != null) {
				List<ServicesDTO> listServicesDTO = new ArrayList<ServicesDTO>();
				for (Services service : listService) {
					ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
					listServicesDTO.add(servicesDTO);
				}
				salesDTO.setServiceDTO(listServicesDTO);
			}

			listSaleDTO.add(salesDTO);
		}
		return listSaleDTO;
	}

	public Long countAll() {
		long countAll = saleRepository.count();
		return countAll;
	}

	public SalesDTO save(Sales sale) {
		Sales saleInput = saleRepository.findById(sale.getId()).get();
		saleInput.setStatus(sale.getStatus());
		Sales saleSave = saleRepository.save(saleInput);
		SalesDTO salesDTO = mapper.map(saleSave, SalesDTO.class);
		salesDTO.setRoomName(saleSave.getRoom().getRoomName());
		salesDTO.setPrice(saleSave.getRoom().getPrice());
		salesDTO.setType(saleSave.getRoom().getType());
		if (saleSave.getCustomer().getUserName()==null) {
			salesDTO.setUserName(saleSave.getCustomer().getEmail());
		} else {
			salesDTO.setUserName(saleSave.getCustomer().getUserName());
		}
		return salesDTO;
	}

	public void delete(final Integer id) {
		Sales sale = saleRepository.findById(id).get();
		if (sale != null) {
			saleRepository.delete(sale);
		}
	}

	public List<SalesDTO> findByFormCheckTypeDateStatus(Date checkInDate, Date checkOutDate, String type,
			Integer price) {
		List<Sales> listSale = saleCustomRepository.findByFormCheckTypeDateStatus(checkInDate, checkOutDate, type,
				price);
		List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
		for (Sales sale : listSale) {
			SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
			if (sale.getCustomer().getUserName()==null) {
				salesDTO.setUserName(sale.getCustomer().getEmail());
			} else {
				salesDTO.setUserName(sale.getCustomer().getUserName());
			}
			salesDTO.setRoomName(sale.getRoom().getRoomName());
			salesDTO.setPrice(sale.getRoom().getPrice());
			salesDTO.setType(sale.getRoom().getType());
			Set<Services> listService = sale.getServices();
			if (listService != null) {
				List<ServicesDTO> listServicesDTO = new ArrayList<ServicesDTO>();
				for (Services service : listService) {
					ServicesDTO servicesDTO = mapper.map(service, ServicesDTO.class);
					listServicesDTO.add(servicesDTO);
				}
				salesDTO.setServiceDTO(listServicesDTO);
			}

			listSaleDTO.add(salesDTO);
		}
		return listSaleDTO;
	}
}
