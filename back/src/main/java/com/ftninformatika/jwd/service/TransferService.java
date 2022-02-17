package com.ftninformatika.jwd.service;

import java.util.List;

import com.ftninformatika.jwd.model.Transfer;

public interface TransferService {

	Transfer findOne(Long id);

	Transfer save(Transfer transfer);

	List<Transfer> findAll();

}
