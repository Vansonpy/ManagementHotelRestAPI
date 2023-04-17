package com.example.reafult.controller;

import java.util.Date;
import java.util.List;

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

import com.example.reafult.dto.RoomsDTO;
import com.example.reafult.dto.SalesDTO;
import com.example.reafult.entities.Rooms;
import com.example.reafult.services.RoomsService;

@RestController
@RequestMapping("/api/rooms")
public class RoomsController {
	@Autowired
	private RoomsService roomsService;
	
	@GetMapping(value="/viewAllRoom")
	public ResponseEntity<List<RoomsDTO>> viewAllRoom() throws Exception {	
	    try {
	    	List<RoomsDTO> listRoomsDTO = roomsService.findAll();
		    if(listRoomsDTO.isEmpty()){
		        return new ResponseEntity<List<RoomsDTO>>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<List<RoomsDTO>>(listRoomsDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<RoomsDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/viewByRoomName")
	public ResponseEntity<RoomsDTO> viewRoomByName(@RequestParam String roomName) throws Exception {
		try {
	    	RoomsDTO roomsDTO = roomsService.findByRoomName(roomName);
		    if(roomsDTO==null){
		        return new ResponseEntity<RoomsDTO>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<RoomsDTO>(roomsDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			 return new ResponseEntity<RoomsDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value="/deleteRoom/{id}")
	public ResponseEntity<RoomsDTO> delete(@PathVariable(value = "id") Integer id) throws Exception {		
	    try {
	    	RoomsDTO roomsDTO = roomsService.findById(id);
		    if(roomsDTO==null){
		        return new ResponseEntity<RoomsDTO>(HttpStatus.NO_CONTENT);
		    }
		    roomsService.delete(id);
		    return new ResponseEntity<RoomsDTO>(roomsDTO,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			 return new ResponseEntity<RoomsDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value="/updateRoom")
	public ResponseEntity<RoomsDTO> doUpdateRoom(@RequestParam Integer id,@RequestBody RoomsDTO roomRequest) throws Exception {
		RoomsDTO currentRoom= roomsService.findById(id);
		try {
	    	if (currentRoom==null||roomRequest.getId()!=id) {
		        System.out.println("Room with id " + id + " not found");
		        return new ResponseEntity<RoomsDTO>(HttpStatus.NO_CONTENT);
		    }  
	    	List<String> listFileID = roomRequest.getUrlImage();
	    	Rooms room = new Rooms();
	    	room.setId(currentRoom.getId());
	    	room.setPrice(roomRequest.getPrice());
	    	room.setRoomName(roomRequest.getRoomName());
	    	room.setType(roomRequest.getType());
		    currentRoom = roomsService.save(room,listFileID);
		    return new ResponseEntity<RoomsDTO>(currentRoom, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			 return new ResponseEntity<RoomsDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value="/addRoom")
	public ResponseEntity<RoomsDTO> addRoom(@RequestBody RoomsDTO roomRequest) throws Exception{		
	    try {
	    	Rooms room = new Rooms();
	    	room.setPrice(roomRequest.getPrice());
	    	room.setRoomName(roomRequest.getRoomName());
	    	room.setType(roomRequest.getType());
	    	List<String> listFileID = roomRequest.getUrlImage();
	    	RoomsDTO currentRoom = roomsService.save(room,listFileID);
		    return new ResponseEntity<RoomsDTO>(currentRoom, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			 return new ResponseEntity<RoomsDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/FormCheckRoom")
	public  ResponseEntity<List<RoomsDTO>> findByFormCheckRoom(@RequestBody SalesDTO saleRequest) throws Exception {
		if (isInFuture(saleRequest.getCheckinDate(), saleRequest.getCheckoutDate())) {
			try {
				List<RoomsDTO> listRoomsDTO = roomsService.findByFormCheckTypeAndDateAndPirce(saleRequest.getCheckinDate(), saleRequest.getCheckoutDate(),saleRequest.getType(),saleRequest.getPrice());
				if (listRoomsDTO.isEmpty()) {
					return new ResponseEntity<List<RoomsDTO>>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<List<RoomsDTO>>(listRoomsDTO, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<RoomsDTO>>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<List<RoomsDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/FormBookRoom")
	public  ResponseEntity<RoomsDTO> findByFormBookRoom(@RequestBody SalesDTO saleRequest) throws Exception {
		if (isInFuture(saleRequest.getCheckinDate(), saleRequest.getCheckoutDate())) {
			try {
				RoomsDTO roomDTO = roomsService.findByFormBookRoom(saleRequest.getCheckinDate(), saleRequest.getCheckoutDate(),saleRequest.getType(),saleRequest.getPrice(),saleRequest.getUserName());
				if (roomDTO==null) {
					return new ResponseEntity<RoomsDTO>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<RoomsDTO>(roomDTO, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<RoomsDTO>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<RoomsDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	private boolean isInFuture(Date checkinDate, Date checkoutDate) {
		if (checkoutDate.compareTo(checkinDate) >= 0) {
			return true;
		} else {
			return false;
		}
	}

}
