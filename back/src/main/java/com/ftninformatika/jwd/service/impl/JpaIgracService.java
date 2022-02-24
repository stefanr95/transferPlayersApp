package com.ftninformatika.jwd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.model.Igrac;
import com.ftninformatika.jwd.repository.IgracRepository;
import com.ftninformatika.jwd.service.IgracService;

@Service
public class JpaIgracService implements IgracService {

	@Autowired
	private IgracRepository igracRepository;


	@Override
	public Igrac findOne(Long id) {
		return igracRepository.findOneById(id);
	}


	@Override
	public Page<Igrac> findAll(Long klubId, String pozicija, int pageNo) {

		if(pozicija == null) {
			pozicija = "";
		}
		if(klubId == null) {
			return igracRepository.findByPozicijaIgnoreCaseContains(pozicija, PageRequest.of(pageNo, 3));
		}

		return igracRepository.findByPozicijaIgnoreCaseContainsAndKlubId(pozicija, klubId, PageRequest.of(pageNo, 3));
	}


	@Override
	public Igrac save(Igrac igrac) {
		return igracRepository.save(igrac);
	}


	@Override
	public Igrac delete(Long id) {
		Igrac igrac = findOne(id);
		if(igrac != null) {
			igrac.getKlub().getIgraci().remove(igrac);
			igrac.setKlub(null);
			igrac = igracRepository.save(igrac);
			igracRepository.delete(igrac);

			return igrac;
		}
		return null;
	}


	@Override
	public Igrac update(Igrac igrac) {
		return igracRepository.save(igrac);
	}

}