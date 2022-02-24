package com.ftninformatika.jwd.service;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.model.Igrac;

public interface IgracService {

	Igrac findOne(Long id);

	Igrac save(Igrac igrac);

	Igrac delete(Long id);

	Igrac update(Igrac igrac);

	Page<Igrac> findAll(Long klubId, String pozicija, int pageNo);

}
