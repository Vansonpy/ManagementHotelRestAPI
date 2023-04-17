package com.example.reafult.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
			Set<FileDB> listFileDB =page.getFilesDB();
			if (listFileDB != null) {
				for (FileDB fileDB : listFileDB) {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
							.path(fileDB.getId().toString()).toUriString();
					if(pagesDTO.getListUrlImage()==null) {
						List<String> listUrl = new ArrayList<String>();
						listUrl.add(fileDownloadUri);
						pagesDTO.setListUrlImage(listUrl);
					}else {
						pagesDTO.getListUrlImage().add(fileDownloadUri);
					}
				}
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
			Set<FileDB> listFileDB =page.getFilesDB();
			if (listFileDB != null) {
				for (FileDB fileDB : listFileDB) {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
							.path(fileDB.getId().toString()).toUriString();
					if(pagesDTO.getListUrlImage()==null) {
						List<String> listUrl = new ArrayList<String>();
						listUrl.add(fileDownloadUri);
						pagesDTO.setListUrlImage(listUrl);
					}else {
						pagesDTO.getListUrlImage().add(fileDownloadUri);
					}
				}
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
			Set<FileDB> listFileDB =page.getFilesDB();
			if (listFileDB != null) {
				for (FileDB fileDB : listFileDB) {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
							.path(fileDB.getId().toString()).toUriString();
					if(pagesDTO.getListUrlImage()==null) {
						List<String> listUrl = new ArrayList<String>();
						listUrl.add(fileDownloadUri);
						pagesDTO.setListUrlImage(listUrl);
					}else {
						pagesDTO.getListUrlImage().add(fileDownloadUri);
					}
				}
			}
			listPageDTO.add(pagesDTO);
		}
		return listPageDTO;
	}

	public PagesDTO findById(Integer id) {
		Pages page = pagesRepository.findById(id).get();
		PagesDTO pagesDTO = mapper.map(page, PagesDTO.class);
		Set<FileDB> listFileDB =page.getFilesDB();
		if (listFileDB != null) {
			for (FileDB fileDB : listFileDB) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
						.path(fileDB.getId().toString()).toUriString();
				if(pagesDTO.getListUrlImage()==null) {
					List<String> listUrl = new ArrayList<String>();
					listUrl.add(fileDownloadUri);
					pagesDTO.setListUrlImage(listUrl);
				}else {
					pagesDTO.getListUrlImage().add(fileDownloadUri);
				}
			}
		}
		return pagesDTO;
	}

	public List<PagesDTO> findAll() {
		List<Pages> listPages = pagesRepository.findAll();
		List<PagesDTO> listPageDTO = new ArrayList<PagesDTO>();
		for (Pages page : listPages) {
			PagesDTO pagesDTO = mapper.map(page, PagesDTO.class);	
			Set<FileDB> listFileDB =page.getFilesDB();
			if (listFileDB != null) {
				for (FileDB fileDB : listFileDB) {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
							.path(fileDB.getId().toString()).toUriString();
					if(pagesDTO.getListUrlImage()==null) {
						List<String> listUrl = new ArrayList<String>();
						listUrl.add(fileDownloadUri);
						pagesDTO.setListUrlImage(listUrl);
					}else {
						pagesDTO.getListUrlImage().add(fileDownloadUri);
					}
				}
			}
			listPageDTO.add(pagesDTO);
		}
		return listPageDTO;
	}

	public Long countAll() {
		long countAll = pagesRepository.count();
		return countAll;
	}

	public PagesDTO save(Pages page, List<String> listFileID) throws IOException {
		
		Pages pageSave = pagesRepository.save(page);
		Set<FileDB> setFilesDB = new HashSet<FileDB>();
		for (String fileID : listFileID) {
			FileDB fileUpload = fileDBRepository.findById(fileID).get();
			fileUpload.setPage(pageSave);
			setFilesDB.add(fileUpload);
		}		
		pageSave.setFilesDB(setFilesDB);
	
		PagesDTO pageDTO = mapper.map(pageSave, PagesDTO.class);
		Set<FileDB> listFileDB =pageSave.getFilesDB();
		if (listFileDB != null) {
			for (FileDB fileDB : listFileDB) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/files/")
						.path(fileDB.getId().toString()).toUriString();
				if(pageDTO.getListUrlImage()==null) {
					List<String> listUrl = new ArrayList<String>();
					listUrl.add(fileDownloadUri);
					pageDTO.setListUrlImage(listUrl);
				}else {
					pageDTO.getListUrlImage().add(fileDownloadUri);
				}
			}
		}
		return pageDTO;
	}

	public void delete(final Integer id) {
		Pages page = pagesRepository.findById(id).get();
		if (page != null) {
			pagesRepository.delete(page);
		}
	}
}
