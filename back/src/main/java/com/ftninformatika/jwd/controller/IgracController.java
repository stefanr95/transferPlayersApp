package com.ftninformatika.jwd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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



}
