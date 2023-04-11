package com.example.reafult.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.reafult.dto.FileDBDTO;
import com.example.reafult.entities.FileDB;
import com.example.reafult.repository.FileDBRepository;

@Service
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileDBService {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private FileDBRepository fileDBRepository;

	public String saveFile(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB image = new FileDB(fileName, file.getContentType(), file.getBytes());
		FileDB fileUpload = fileDBRepository.save(image);
		String fileId = fileUpload.getId();
		return fileId ;
	}

	public List<FileDBDTO> getAllFiles() {
		List<FileDB> listFiles = fileDBRepository.findAll();
		List<FileDBDTO> listFileDBDTO = new ArrayList<FileDBDTO>();
		for (FileDB fileDB : listFiles) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
					.path(fileDB.getId().toString()).toUriString();
			FileDBDTO filesDTODB = mapper.map(fileDB, FileDBDTO.class);
			filesDTODB.setUrl(fileDownloadUri);
			filesDTODB.setSize(fileDB.getData().length);
			listFileDBDTO.add(filesDTODB);
		}
		return listFileDBDTO;
	}

	public FileDBDTO findById(String id) {
		FileDB fileDB = fileDBRepository.findById(id).get();
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
				.path(fileDB.getId().toString()).toUriString();
		FileDBDTO filesDTODB = mapper.map(fileDB, FileDBDTO.class);
		filesDTODB.setUrl(fileDownloadUri);
		filesDTODB.setSize(fileDB.getData().length);
		return filesDTODB;
	}
	
	/*
	 * public List<FileDBDTO> getFilesByPageId(Integer pageId) { List<FileDB>
	 * listFiles = fileDBRepository.findByPage(pageId); List<FileDBDTO>
	 * listFileDBDTO = new ArrayList<FileDBDTO>(); for (FileDB fileDB : listFiles) {
	 * String fileDownloadUri =
	 * ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
	 * .path(fileDB.getId().toString()).toUriString(); FileDBDTO filesDTODB =
	 * mapper.map(fileDB, FileDBDTO.class); filesDTODB.setUrl(fileDownloadUri);
	 * filesDTODB.setSize(fileDB.getData().length); listFileDBDTO.add(filesDTODB); }
	 * return listFileDBDTO; }
	 */
}
