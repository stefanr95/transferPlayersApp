package com.ftninformatika.jwd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Igrac;

@Repository
public interface IgracRepository extends JpaRepository<Igrac, Long> {
	
	Igrac findOneById(Long id);
	
	Page<Igrac> findByPozicijaIgnoreCaseContainsAndKlubId(String pozicija, Long id, PageRequest pageRequest);

	Page<Igrac> findByPozicijaIgnoreCaseContains(String pozicija, PageRequest pageRequest);

}
