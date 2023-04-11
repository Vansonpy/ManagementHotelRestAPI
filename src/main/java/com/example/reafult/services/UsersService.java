package com.example.reafult.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reafult.dto.SalesDTO;
import com.example.reafult.dto.ServicesDTO;
import com.example.reafult.dto.UsersDTO;
import com.example.reafult.entities.Customer;
import com.example.reafult.entities.Sales;
import com.example.reafult.entities.Services;
import com.example.reafult.entities.Users;
import com.example.reafult.repository.CustomerRepository;
import com.example.reafult.repository.UserRepository;

@Service
@Transactional
public class UsersService {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UsersDTO findUserLogin(String userName, String password) {
		Users user = userRepository.findByUserNameAndPassword(userName, password);
		UsersDTO userDTO = mapper.map(user, UsersDTO.class);
		Customer customer = customerRepository.findByEmail(user.getEmail());
		List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
		if(customer!=null) {
			Set<Sales> listSale = customer.getSales();
			if(listSale!=null) {
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
		userDTO.setSalesDTO(listSaleDTO);
		return userDTO;
	}

	public UsersDTO findUserName(String userName) {
		Users user = userRepository.findByUserName(userName);
		UsersDTO userDTO = mapper.map(user, UsersDTO.class);
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
		userDTO.setSalesDTO(listSaleDTO);
		return userDTO;
	}

	public UsersDTO findUserByEmail(String email) {
		Users user = userRepository.findByEmail(email);
		UsersDTO userDTO = mapper.map(user, UsersDTO.class);
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
		userDTO.setSalesDTO(listSaleDTO);
		return userDTO;
	}

	public UsersDTO findUserNameAndEmail(String userName, String email) {
		Users user = userRepository.findByUserNameAndEmail(userName, email);
		UsersDTO userDTO = mapper.map(user, UsersDTO.class);
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
		userDTO.setSalesDTO(listSaleDTO);
		return userDTO;
	}

	public UsersDTO findById(final Integer id) {
		Users user = userRepository.findById(id).get();
		UsersDTO userDTO = mapper.map(user, UsersDTO.class);
		return userDTO;
	}

	public List<UsersDTO> findAll() {
		List<Users> listUser = userRepository.findAll();
		List<UsersDTO> listUserDTO = new ArrayList<UsersDTO>();
		if (listUser != null) {
			for (Users user : listUser) {
				UsersDTO userDTO = mapper.map(user, UsersDTO.class);
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
				userDTO.setSalesDTO(listSaleDTO);
				listUserDTO.add(userDTO);
			}
		}
		return listUserDTO;
	}

	public UsersDTO save(Users user) {
		user.setUserName(user.getUserName());
		user.setEnabled(true);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Users userSave = userRepository.save(user);
		UsersDTO userDTO = mapper.map(userSave, UsersDTO.class);
		Customer customer = customerRepository.findByEmail(user.getEmail());
		if(customer!=null && customer.getUserName()==null) {
			customer.setUserName(user.getUserName());
		}		
		return userDTO;
	}

	public void delete(Integer id) {
		Users user = userRepository.findById(id).get();
		if (user != null) {
			Customer customer = customerRepository.findByEmail(user.getEmail());
			if (customer != null) {
				List<Sales> listCheck = new ArrayList<Sales>();
				Set<Sales> listSale = customer.getSales();
				if (listSale != null) {
					for (Sales sales : listSale) {
						if (sales.getStatus() == 1) {
							listCheck.add(sales);
						}
					}
				}
				if (listCheck.size() == 0 && user.getRole().equals("Role_User") == true) {
					userRepository.delete(user);
				}
			}
		}
	}
}
