package com.ftninformatika.jwd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	public Page<Igrac> findAll(String pozicija, Long klubId, int pageNo) {
		
		return null;
	}

}
