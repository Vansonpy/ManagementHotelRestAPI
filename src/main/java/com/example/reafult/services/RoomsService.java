package com.example.reafult.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.reafult.dto.RoomsDTO;
import com.example.reafult.dto.SalesDTO;
import com.example.reafult.dto.ServicesDTO;
import com.example.reafult.entities.Customer;
import com.example.reafult.entities.FileDB;
import com.example.reafult.entities.Rooms;
import com.example.reafult.entities.Sales;
import com.example.reafult.entities.Services;
import com.example.reafult.repository.CustomerRepository;
import com.example.reafult.repository.FileDBRepository;
import com.example.reafult.repository.RoomRepository;
import com.example.reafult.repository.SalesCustomRepository;

@Service
@Transactional
public class RoomsService {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private SalesCustomRepository saleCustomRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private FileDBRepository fileDBRepository;

	public RoomsDTO findByRoomName(String roomName) {
		Rooms room = roomRepository.findByRoomName(roomName);
		RoomsDTO roomsDTO = mapper.map(room, RoomsDTO.class);
		Set<Sales> listSale = room.getSales();
		if (listSale != null) {
			List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
			for (Sales sale : listSale) {
				SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
				if (sale.getCustomer().getUserName() == null) {
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
				roomsDTO.setSalesDTO(listSaleDTO);
			}
		}

		Set<FileDB> listFileDB = room.getFilesDB();
		if (listFileDB != null) {
			for (FileDB fileDB : listFileDB) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
						.path(fileDB.getId().toString()).toUriString();
				if(roomsDTO.getUrlImage()==null) {
					List<String> listUrl = new ArrayList<String>();
					listUrl.add(fileDownloadUri);
					roomsDTO.setUrlImage(listUrl);
				}else {
					roomsDTO.getUrlImage().add(fileDownloadUri);
				}
			}
		}
		return roomsDTO;
	}

	public RoomsDTO findById(final Integer id) {
		Rooms room = roomRepository.findById(id).get();
		RoomsDTO roomsDTO = mapper.map(room, RoomsDTO.class);
		return roomsDTO;
	}

	public List<RoomsDTO> findAll() {
		List<Rooms> listRoom = roomRepository.findAll();
		List<RoomsDTO> listRoomDTO = new ArrayList<RoomsDTO>();
		for (Rooms rooms : listRoom) {
			RoomsDTO roomsDTO = mapper.map(rooms, RoomsDTO.class);
			Set<Sales> listSale = rooms.getSales();
			if (listSale != null) {
				List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
				for (Sales sale : listSale) {
					SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
					if (sale.getCustomer().getUserName() == null) {
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
					roomsDTO.setSalesDTO(listSaleDTO);
				}
			}

			Set<FileDB> listFileDB = rooms.getFilesDB();
			if (listFileDB != null) {
				for (FileDB fileDB : listFileDB) {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
							.path(fileDB.getId().toString()).toUriString();
					if(roomsDTO.getUrlImage()==null) {
						List<String> listUrl = new ArrayList<String>();
						listUrl.add(fileDownloadUri);
						roomsDTO.setUrlImage(listUrl);
					}else {
						roomsDTO.getUrlImage().add(fileDownloadUri);
					}
				}
			}

			listRoomDTO.add(roomsDTO);
		}
		return listRoomDTO;
	}

	public RoomsDTO save(Rooms room, List<String> listFileID) throws IOException {
		Rooms roomSave = roomRepository.save(room);
		Set<FileDB> setFilesDB = new HashSet<FileDB>();
		for (String fileID : listFileID) {
			FileDB fileUpload = fileDBRepository.findById(fileID).get();
			fileUpload.setRoom(roomSave);
			setFilesDB.add(fileUpload);
		}		
		roomSave.setFilesDB(setFilesDB);
		
		RoomsDTO roomsDTO = mapper.map(roomSave, RoomsDTO.class);
		Set<Sales> listSale = room.getSales();
		if (listSale != null) {
			List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
			for (Sales sale : listSale) {
				SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
				if (sale.getCustomer().getUserName() == null) {
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
				roomsDTO.setSalesDTO(listSaleDTO);
			}
		}

		Set<FileDB> listFileDB = room.getFilesDB();
		if (listFileDB != null) {
			for (FileDB fileDB : listFileDB) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
						.path(fileDB.getId().toString()).toUriString();
				if(roomsDTO.getUrlImage()==null) {
					List<String> listUrl = new ArrayList<String>();
					listUrl.add(fileDownloadUri);
					roomsDTO.setUrlImage(listUrl);
				}else {
					roomsDTO.getUrlImage().add(fileDownloadUri);
				}
			}
		}
		return roomsDTO;
	}

	public void delete(Integer id) {
		Rooms room = roomRepository.findById(id).get();
		if (room != null) {
			Set<Sales> listSale = room.getSales();
			List<Sales> listCheck = new ArrayList<Sales>();
			for (Sales sales : listSale) {
				if (sales.getStatus() != 0) {
					listCheck.add(sales);
				}
			}
			if (listCheck.size() == 0) {
				roomRepository.delete(room);
			}
		}
	}

	public RoomsDTO findByFormBookRoom(Date checkInDate, Date checkOutDate, String type, Integer price, String email) {
		List<Rooms> listRoom = roomRepository.findByTypeAndPriceLessThan(type, price);
		if (listRoom.isEmpty()) {
			return null;
		}
		List<Sales> listSaleHired = saleCustomRepository.findByFormCheckTypeDateStatus(checkInDate, checkOutDate, type,
				price);
		List<Rooms> listRoomHired = new ArrayList<Rooms>();
		if (listSaleHired != null) {
			for (Sales sales : listSaleHired) {
				listRoomHired.add(sales.getRoom());
			}
		}
		listRoom.removeAll(listRoomHired);
		if (listRoom.isEmpty()) {
			return null;

		}
		Rooms roomBook = new Rooms();
		for (Rooms rooms : listRoom) {
			roomBook = rooms;
		}
		Sales saleBook = new Sales();
		saleBook.setStatus(0);
		saleBook.setCheckinDate(checkInDate);
		saleBook.setCheckoutDate(checkOutDate);
		if (roomBook.getSales() != null) {
			roomBook.getSales().add(saleBook);
		} else {
			Set<Sales> setSales = new HashSet<Sales>();
			setSales.add(saleBook);
			roomBook.setSales(setSales);
		}
		saleBook.setRoom(roomBook);
		Customer customer = customerRepository.findByEmail(email);
		if (customer != null) {
			customer.getSales().add(saleBook);
			saleBook.setCustomer(customer);
		} else {
			Customer customerNew = new Customer();
			customerNew.setEmail(email);
			customerRepository.save(customerNew);
			System.out.println(customerNew);
			Set<Sales> setSales = new HashSet<Sales>();
			setSales.add(saleBook);
			customerNew.setSales(setSales);

			saleBook.setCustomer(customerNew);
		}

		RoomsDTO roomsDTO = mapper.map(roomBook, RoomsDTO.class);
		Set<Sales> listSale = roomBook.getSales();
		if (listSale != null) {
			List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
			for (Sales sale : listSale) {
				SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
				if (sale.getCustomer().getUserName() == null) {
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
				roomsDTO.setSalesDTO(listSaleDTO);
			}
		}

		Set<FileDB> listFileDB = roomBook.getFilesDB();
		if (listFileDB != null) {
			for (FileDB fileDB : listFileDB) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
						.path(fileDB.getId().toString()).toUriString();
				if(roomsDTO.getUrlImage()==null) {
					List<String> listUrl = new ArrayList<String>();
					listUrl.add(fileDownloadUri);
					roomsDTO.setUrlImage(listUrl);
				}else {
					roomsDTO.getUrlImage().add(fileDownloadUri);
				}
			}
		}
		return roomsDTO;

	}

	public List<RoomsDTO> findByFormCheckTypeAndDateAndPirce(Date checkInDate, Date checkOutDate, String type,
			Integer price) {
		List<Rooms> listRoom = roomRepository.findByTypeAndPriceLessThan(type, price);
		List<RoomsDTO> listRoomDTO = new ArrayList<RoomsDTO>();
		if (listRoom.isEmpty()) {
			return null;
		}
		List<Sales> listSaleHired = saleCustomRepository.findByFormCheckTypeDateStatus(checkInDate, checkOutDate, type,
				price);
		List<Rooms> listRoomHired = new ArrayList<Rooms>();
		if (listSaleHired != null) {
			for (Sales sales : listSaleHired) {
				listRoomHired.add(sales.getRoom());
			}
		}
		listRoom.removeAll(listRoomHired);
		if (listRoom.isEmpty()) {
			return null;

		}
		for (Rooms rooms : listRoom) {
			RoomsDTO roomsDTO = mapper.map(rooms, RoomsDTO.class);
			Set<Sales> listSaleEmty = rooms.getSales();
			if (listSaleEmty != null) {
				List<SalesDTO> listSaleDTO = new ArrayList<SalesDTO>();
				for (Sales sale : listSaleEmty) {
					SalesDTO salesDTO = mapper.map(sale, SalesDTO.class);
					if (sale.getCustomer().getUserName() == null) {
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
					roomsDTO.setSalesDTO(listSaleDTO);
				}
			}

			Set<FileDB> listFileDB = rooms.getFilesDB();
			if (listFileDB != null) {
				for (FileDB fileDB : listFileDB) {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
							.path(fileDB.getId().toString()).toUriString();
					if(roomsDTO.getUrlImage()==null) {
						List<String> listUrl = new ArrayList<String>();
						listUrl.add(fileDownloadUri);
						roomsDTO.setUrlImage(listUrl);
					}else {
						roomsDTO.getUrlImage().add(fileDownloadUri);
					}
				}
			}
			listRoomDTO.add(roomsDTO);
		}
		return listRoomDTO;
	}
}
