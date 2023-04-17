package com.example.reafult.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.reafult.services.FileDBService;
import com.example.reafult.dto.FileDBDTO;
import com.example.reafult.entities.FileDB;
import com.example.reafult.repository.FileDBRepository;
@RestController
@RequestMapping("/api/img")

public class FileDBController {
	@Autowired
	private FileDBService fileDBService;
	@Autowired
	private FileDBRepository fileDBRepository;
	@PostMapping(value="/upload",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<String>> uploadFile(@RequestPart("files") List<MultipartFile> files) {        
        try {
        	List<String> listFileID = fileDBService.saveFile(files);
            return new ResponseEntity<List<String>>(listFileID, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileDBDTO>> getListFiles() {
    	try {
	    	List<FileDBDTO> listFileDBDTO = fileDBService.getAllFiles();
		    if(listFileDBDTO.isEmpty()){
		        return new ResponseEntity<List<FileDBDTO>>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<List<FileDBDTO>>(listFileDBDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<FileDBDTO>>(HttpStatus.BAD_REQUEST);
		}
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
    	Optional<FileDB> optionalFileDB = fileDBRepository.findById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + optionalFileDB.get().getName() + "\"")
                .body(optionalFileDB.get().getData());
    }
}
