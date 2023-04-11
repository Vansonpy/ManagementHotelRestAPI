package com.example.reafult.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.reafult.dto.FileDBDTO;
import com.example.reafult.dto.PagesDTO;
import com.example.reafult.entities.FileDB;
import com.example.reafult.entities.Pages;
import com.example.reafult.repository.FileDBRepository;
import com.example.reafult.repository.PageRepository;

@Service
@Transactional
public class PagesService {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PageRepository pagesRepository;
	@Autowired
	private FileDBRepository fileDBRepository;

	public List<PagesDTO> findByTitle(String title) {
		List<Pages> listPages = pagesRepository.findByTitle(title);
		List<PagesDTO> listPageDTO = new ArrayList<PagesDTO>();
		for (Pages page : listPages) {
			PagesDTO pagesDTO = mapper.map(page, PagesDTO.class);			
			Set<FileDB> listFile = page.getFileDB();
			if (listFile != null) {
				List<String> listUrlImage = new ArrayList<String>();
				for (FileDB fileDB : listFile) {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/img/files/").path(fileDB.getId().toString()).toUriString();
					listUrlImage.add(fileDownloadUri);
				}
				pagesDTO.setListUrlImage(listUrlImage);
			}
			listPageDTO.add(pagesDTO);
		}
		return listPageDTO;
	}

	public List<PagesDTO> findByPageNameAndLocation(String pageName, String location) {
		List<Pages> listPages = pagesRepository.findByPageNameAndLocation(pageName, location);
		List<PagesDTO> listPageDTO = new ArrayList<PagesDTO>();
		for (Pages page : listPages) {
			PagesDTO pagesDTO = mapper.map(page, PagesDTO.class);	
			Set<FileDB> listFile = page.getFileDB();
			if (listFile != null) {
				List<String> listUrlImage = new ArrayList<String>();
				for (FileDB fileDB : listFile) {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/img/files/").path(fileDB.getId().toString()).toUriString();
					listUrlImage.add(fileDownloadUri);
				}
				pagesDTO.setListUrlImage(listUrlImage);
			}
			listPageDTO.add(pagesDTO);
		}
		return listPageDTO;
	}

	public List<PagesDTO> findByPageName(String pageName) {
		List<Pages> listPages = pagesRepository.findByPageName(pageName);
		List<PagesDTO> listPageDTO = new ArrayList<PagesDTO>();
		for (Pages page : listPages) {
			PagesDTO pagesDTO = mapper.map(page, PagesDTO.class);	
			Set<FileDB> listFile = page.getFileDB();
			if (listFile != null) {
				List<String> listUrlImage = new ArrayList<String>();
				for (FileDB fileDB : listFile) {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/img/files/").path(fileDB.getId().toString()).toUriString();
					listUrlImage.add(fileDownloadUri);
				}
				pagesDTO.setListUrlImage(listUrlImage);
			}
			listPageDTO.add(pagesDTO);
		}
		return listPageDTO;
	}

	public PagesDTO findById(Integer id) {
		Pages page = pagesRepository.findById(id).get();
		PagesDTO pagesDTO = mapper.map(page, PagesDTO.class);
		Set<FileDB> listFile = page.getFileDB();
		if (listFile != null) {
			List<String> listUrlImage = new ArrayList<String>();
			for (FileDB fileDB : listFile) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
						.path(fileDB.getId().toString()).toUriString();
				listUrlImage.add(fileDownloadUri);
			}
			pagesDTO.setListUrlImage(listUrlImage);
		}
		return pagesDTO;
	}

	public List<PagesDTO> findAll() {
		List<Pages> listPages = pagesRepository.findAll();
		List<PagesDTO> listPageDTO = new ArrayList<PagesDTO>();
		for (Pages page : listPages) {
			PagesDTO pagesDTO = mapper.map(page, PagesDTO.class);	
			Set<FileDB> listFile = page.getFileDB();
			if (listFile != null) {
				List<String> listUrlImage = new ArrayList<String>();
				for (FileDB fileDB : listFile) {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/img/files/").path(fileDB.getId().toString()).toUriString();
					listUrlImage.add(fileDownloadUri);
				}
				pagesDTO.setListUrlImage(listUrlImage);
			}
			listPageDTO.add(pagesDTO);
		}
		return listPageDTO;
	}

	public Long countAll() {
		long countAll = pagesRepository.count();
		return countAll;
	}

	public PagesDTO save(Pages page, MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB image = new FileDB(fileName, file.getContentType(), file.getBytes());
		FileDB fileUpload = fileDBRepository.save(image);
		Set<FileDB> setFileDB = new HashSet<FileDB>();
		setFileDB.add(fileUpload);
		Pages pageSave = pagesRepository.save(page);
		pageSave.setFileDB(setFileDB);
		fileUpload.setPage(pageSave);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
				.path(fileUpload.getId().toString()).toUriString();
		PagesDTO pageDTO = mapper.map(pageSave, PagesDTO.class);
		List<String> listUrlImage = new ArrayList<String>();
		listUrlImage.add(fileDownloadUri);
		pageDTO.setListUrlImage(listUrlImage);
		return pageDTO;
	}

	public void delete(final Integer id) {
		Pages page = pagesRepository.findById(id).get();
		if (page != null) {
			pagesRepository.delete(page);
		}
	}
}
