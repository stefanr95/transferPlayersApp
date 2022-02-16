package com.ftninformatika.jwd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.model.Igrac;
import com.ftninformatika.jwd.service.IgracService;
import com.ftninformatika.jwd.support.IgracDtoToIgrac;
import com.ftninformatika.jwd.support.IgracToIgracDto;
import com.ftninformatika.jwd.web.dto.IgracDTO;

@RestController
@RequestMapping(value = "/api/igraci", produces = MediaType.APPLICATION_JSON_VALUE)
public class IgracController {

	@Autowired
	private IgracService igracService;

	@Autowired
	private IgracDtoToIgrac toIgrac;

	@Autowired
	private IgracToIgracDto toIgracDto;

	//preuzimanje svih igraca PAGINIRANO
	@GetMapping
	public ResponseEntity<List<IgracDTO>> getAll(
			@RequestParam (required = false) String pozicija,
			@RequestParam (required = false) Long klubId,
			@RequestParam (value = "pageNo", defaultValue = "0") int pageNo) {


		Page<Igrac> page = igracService.findAll(pozicija, klubId, pageNo);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

		return new ResponseEntity<>(toIgracDto.convert(page.getContent()), headers, HttpStatus.OK);

	}

	//preuzimanje igraca po zadatom ID
	@GetMapping("/{id}")
	public ResponseEntity<IgracDTO> getOne(@PathVariable Long id) {
		Igrac igrac = igracService.findOne(id);

		if(igrac != null) {
			return new ResponseEntity<>(toIgracDto.convert(igrac), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
	}

	//dodavanje novog igraca
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IgracDTO> create(@Valid @RequestBody IgracDTO igracDTO){
		Igrac igrac = toIgrac.convert(igracDTO);

		if(igrac.getKlub() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Igrac sacuvanIgrac= igracService.save(igrac);

		return new ResponseEntity<>(toIgracDto.convert(sacuvanIgrac), HttpStatus.CREATED);
	}

}