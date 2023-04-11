package com.example.reafult.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reafult.entities.Rooms;

@Repository(value = "roomRepository")
public interface RoomRepository extends JpaRepository<Rooms, Integer> {
	Rooms findByRoomName(String roomName);
	List<Rooms> findByTypeAndPriceLessThan(String type,Integer price);
}
