package com.ftninformatika.jwd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.model.Klub;
import com.ftninformatika.jwd.repository.KlubRepository;
import com.ftninformatika.jwd.service.KlubService;

@Service
public class JpaKlubService implements KlubService{

	@Autowired
	private KlubRepository klubRepository;
	
	
	@Override
	public Klub findOne(Long id) {
		return klubRepository.findOneById(id);
	}


	@Override
	public List<Klub> findAll() {
		return klubRepository.findAll();
	}


	@Override
	public Klub findOne(String nazivKluba) {
		return klubRepository.findByNazivIgnoreCaseContains(nazivKluba);
	}


	@Override
	public Klub save(Klub klub) {
		return klubRepository.save(klub);
	}
	
}