package com.ftninformatika.jwd.service;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.model.Igrac;

public interface IgracService {

	Page<Igrac> findAll(String pozicija, Long klubId, int pageNo);

}
