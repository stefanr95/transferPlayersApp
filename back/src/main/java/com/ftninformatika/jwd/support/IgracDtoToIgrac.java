package com.ftninformatika.jwd.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.model.Igrac;
import com.ftninformatika.jwd.service.IgracService;
import com.ftninformatika.jwd.service.KlubService;
import com.ftninformatika.jwd.web.dto.IgracDTO;

@Component
public class IgracDtoToIgrac implements Converter<IgracDTO, Igrac>{

	@Autowired
	private IgracService igracService;

	@Autowired
	private KlubService klubService;

	@Override
	public Igrac convert(IgracDTO dto) {
		Igrac igrac;

		if(dto.getId() == null) {
			igrac = new Igrac();
		} else {
			igrac = igracService.findOne(dto.getId());
		}

		if(igrac != null) {
			igrac.setImeIprezime(dto.getImeIprezime());
			igrac.setPozicija(dto.getPozicija());
			igrac.setBrojDresa(dto.getBrojDresa());
			igrac.setRodjenje(getLocalDate(dto.getRodjenje()));
			igrac.setProdaja(dto.isProdaja());
			igrac.setKlub(klubService.findOne(dto.getKlubDTO().getId()));	

		}

		return igrac;
	}

	private LocalDate getLocalDate(String datumTransfer) throws DateTimeParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate datum = LocalDate.parse(datumTransfer, formatter);
		return datum;
	}

}