package com.ftninformatika.jwd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Igrac;

@Repository
public interface IgracRepository extends JpaRepository<Igrac, Long> {

}
