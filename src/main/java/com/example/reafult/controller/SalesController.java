package com.example.reafult.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reafult.dto.SalesDTO;
import com.example.reafult.entities.Sales;
import com.example.reafult.services.SalesService;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
//	private Logger logger = Logger.getLogger(SalesController.class);

	@Autowired
	private SalesService salesService;

	@GetMapping(value = "/viewAllSale")
	public ResponseEntity<List<SalesDTO>> viewAllSale() throws Exception {	
		try {
			List<SalesDTO> listSalesDTO = salesService.findAll();
			if (listSalesDTO.isEmpty()) {
				return new ResponseEntity<List<SalesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<SalesDTO>>(listSalesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<SalesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/viewById")
	public ResponseEntity<SalesDTO> viewUser(@RequestParam Integer id) throws Exception {
		try {
			SalesDTO salesDTO = salesService.findById(id);
			if (salesDTO == null) {
				return new ResponseEntity<SalesDTO>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<SalesDTO>(salesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<SalesDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "/viewAllSaleByStatus")
	public ResponseEntity<List<SalesDTO>> viewAllSaleByStatus(@RequestParam Integer status) throws Exception {		
		try {
			List<SalesDTO> listSalesDTO = salesService.findByStatus(status);
			if (listSalesDTO.isEmpty()) {
				return new ResponseEntity<List<SalesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<SalesDTO>>(listSalesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<SalesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/viewSalesUser")
	public ResponseEntity<List<SalesDTO>> viewSalesUser(@RequestParam String userName) throws Exception {		
		try {
			List<SalesDTO> listSalesDTO = salesService.findByUserName(userName);
			if (listSalesDTO.isEmpty()) {
				return new ResponseEntity<List<SalesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<SalesDTO>>(listSalesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<SalesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/viewSalesUserAndStatus")
	public ResponseEntity<List<SalesDTO>> viewSalesUserAndStatus(@RequestParam String userName,@RequestParam Integer status) throws Exception {		
		try {
			List<SalesDTO> listSalesDTO = salesService.findByUserNameAndStatus(userName,status);
			if (listSalesDTO.isEmpty()) {
				return new ResponseEntity<List<SalesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<SalesDTO>>(listSalesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<SalesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	@SuppressWarnings("deprecation")
	@GetMapping(value = "/viewByMothOfCheckinDate")
	public ResponseEntity<List<SalesDTO>> viewByMothOfCheckinDate(@RequestBody Sales saleRequest) throws Exception {
		try {
			Date checkinDate = saleRequest.getCheckinDate();

			Calendar cStrart = Calendar.getInstance();
			Calendar cEnd = Calendar.getInstance();
			checkinDate.setDate(1);
			checkinDate.setMonth(checkinDate.getMonth());
			cStrart.setTime(checkinDate);
			switch (checkinDate.getMonth() + 1) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				checkinDate.setDate(31);
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				checkinDate.setDate(30);
				break;
			case 2:
				checkinDate.setDate(29);
				break;
			}
			cEnd.setTime(checkinDate);
			Date endDate = cEnd.getTime();
			Date strartDate = cStrart.getTime();
			List<SalesDTO> listSalesDTO = salesService.findByMothOfCheckinDate(strartDate, endDate);
			if (listSalesDTO.isEmpty()) {
				return new ResponseEntity<List<SalesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<SalesDTO>>(listSalesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<SalesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/addSales")
	public ResponseEntity<SalesDTO> addSales(@Valid @RequestBody Sales saleRequest) throws Exception {
		try {
			SalesDTO currentSale = salesService.save(saleRequest);
			return new ResponseEntity<SalesDTO>(currentSale, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<SalesDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/updateSales")
	public ResponseEntity<SalesDTO> doUpdatePage(@RequestParam Integer id, @RequestBody Sales sale)
			throws Exception {		
		try {
			SalesDTO currentSale = salesService.findById(id);
			System.out.println(id);
			if (currentSale == null) {
				return new ResponseEntity<SalesDTO>(HttpStatus.NO_CONTENT);
			}
			SalesDTO salesUpdate = salesService.save(sale);
			return new ResponseEntity<SalesDTO>(salesUpdate, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<SalesDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/FormCheckTypeDateStatus")
	public  ResponseEntity<List<SalesDTO>> findByFormCheckTypeDateStatus(@RequestBody SalesDTO saleRequest) throws Exception {
//		Date checkinDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkInDate);
//		Date checkoutDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkOutDate);
		if (isInFuture(saleRequest.getCheckinDate(), saleRequest.getCheckoutDate())) {
			try {
				List<SalesDTO> listSalesDTO = salesService.findByFormCheckTypeDateStatus(saleRequest.getCheckinDate(), saleRequest.getCheckoutDate(),saleRequest.getType(),saleRequest.getPrice());
				if (listSalesDTO.isEmpty()) {
					return new ResponseEntity<List<SalesDTO>>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<List<SalesDTO>>(listSalesDTO, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<SalesDTO>>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<List<SalesDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		
	}

	private boolean isInFuture(Date checkinDate, Date checkoutDate) {
		if (checkoutDate.compareTo(checkinDate) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	@DeleteMapping(value = "/deleteSales/{id}")
	public ResponseEntity<SalesDTO> delete(@PathVariable(value = "id") Integer id) throws Exception {	
		try {
			SalesDTO salesDTO = salesService.findById(id);
			if (salesDTO == null) {
				return new ResponseEntity<SalesDTO>(HttpStatus.NO_CONTENT);
			}
			salesService.delete(id);
			return new ResponseEntity<SalesDTO>(salesDTO,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SalesDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
