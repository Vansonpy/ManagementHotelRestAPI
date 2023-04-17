package com.example.reafult.controller;

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

import com.example.reafult.dto.PagesDTO;
import com.example.reafult.entities.Pages;
import com.example.reafult.services.PagesService;

@RestController
@RequestMapping("/api/pages")
public class PagesController {

	@Autowired
	private PagesService pagesService;

	@GetMapping(value = "/viewAllPage")
	public ResponseEntity<List<PagesDTO>> viewAllRoom() throws Exception {
		try {
			List<PagesDTO> listPagesDTO = pagesService.findAll();
			if (listPagesDTO.isEmpty()) {
				return new ResponseEntity<List<PagesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<PagesDTO>>(listPagesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PagesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/viewByPagesName/{pagesName}")
	public ResponseEntity<List<PagesDTO>> viewPagesByName(@PathVariable("pagesName") String pagesName)
			throws Exception {
		try {
			List<PagesDTO> listPagesDTO = pagesService.findByPageName(pagesName);
			if (listPagesDTO.isEmpty()) {
				return new ResponseEntity<List<PagesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<PagesDTO>>(listPagesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PagesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/viewByPagesNameAndLocation")
	public ResponseEntity<List<PagesDTO>> viewByPagesNameAndLocation(@RequestParam String pageName,
			@RequestParam String location) throws Exception {
		try {
			List<PagesDTO> listPagesDTO = pagesService.findByPageNameAndLocation(pageName, location);
			if (listPagesDTO.isEmpty()) {
				return new ResponseEntity<List<PagesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<PagesDTO>>(listPagesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PagesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/viewByTitle/{title}")
	public ResponseEntity<List<PagesDTO>> viewPagesByTitle(@PathVariable("title") String title) throws Exception {
		try {
			List<PagesDTO> listPagesDTO = pagesService.findByTitle(title);
			if (listPagesDTO.isEmpty()) {
				return new ResponseEntity<List<PagesDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<PagesDTO>>(listPagesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PagesDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<PagesDTO> findById(@PathVariable("id") Integer id) throws Exception {
		try {
			PagesDTO pagesDTO = pagesService.findById(id);
			if (pagesDTO == null) {
				return new ResponseEntity<PagesDTO>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<PagesDTO>(pagesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<PagesDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/addPage")

	public ResponseEntity<PagesDTO> addPage(@RequestBody PagesDTO pageRequest) throws Exception {
		try {
			Pages pages = new Pages();
			pages.setPageName(pageRequest.getPageName());
			pages.setSubContent(pageRequest.getSubContent());
			pages.setTitle(pageRequest.getTitle());
			pages.setLocation(pageRequest.getLocation());			
			pages.setListContent(pageRequest.getListContent());
			List<String> listFileID = pageRequest.getListUrlImage();
			PagesDTO currentPage = pagesService.save(pages, listFileID);
			return new ResponseEntity<PagesDTO>(currentPage, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<PagesDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/updatePage/{id}")
	public ResponseEntity<PagesDTO> doUpdatePage(@PathVariable("id") Integer id,@RequestBody PagesDTO pageRequest) throws Exception {
		try {
			PagesDTO currentPage = pagesService.findById(id);
			if (currentPage == null || pageRequest.getId() != id) {
				return new ResponseEntity<PagesDTO>(HttpStatus.NO_CONTENT);
			}
			Pages pages = new Pages();
			pages.setPageName(pageRequest.getPageName());
			pages.setSubContent(pageRequest.getSubContent());
			pages.setTitle(pageRequest.getTitle());
			pages.setLocation(pageRequest.getLocation());			
			pages.setListContent(pageRequest.getListContent());
			List<String> listFileID = pageRequest.getListUrlImage();
			currentPage = pagesService.save(pages, listFileID);
			return new ResponseEntity<PagesDTO>(currentPage, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<PagesDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/deletePage/{id}")
	public ResponseEntity<PagesDTO> deletePage(@PathVariable("id") Integer id) throws Exception {
		try {
			PagesDTO pageDTO = pagesService.findById(id);
			if (pageDTO == null) {
				return new ResponseEntity<PagesDTO>(HttpStatus.NO_CONTENT);
			}
			pagesService.delete(id);
			return new ResponseEntity<PagesDTO>(pageDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<PagesDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
