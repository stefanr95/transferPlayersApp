package com.ftninformatika.jwd.service;

import java.util.List;

import com.ftninformatika.jwd.model.Klub;

public interface KlubService {

	List<Klub> findAll();

	Klub findOne(Long id);

	Klub findOne(String nazivKluba);

	Klub save(Klub klub);

}
