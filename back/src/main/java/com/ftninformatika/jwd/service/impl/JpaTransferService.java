package com.ftninformatika.jwd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.model.Igrac;
import com.ftninformatika.jwd.model.Klub;
import com.ftninformatika.jwd.model.Transfer;
import com.ftninformatika.jwd.repository.TransferRepository;
import com.ftninformatika.jwd.service.IgracService;
import com.ftninformatika.jwd.service.KlubService;
import com.ftninformatika.jwd.service.TransferService;

@Service
public class JpaTransferService implements TransferService{

	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private KlubService klubService;

	@Autowired
	private IgracService igracService;

	@Override
	public Transfer findOne(Long id) {
		return transferRepository.findOneById(id);
	}

	@Override
	public List<Transfer> findAll() {
		return transferRepository.findAll();
	}

	@Override
	public Transfer save(Transfer transfer) {
		Igrac igrac = transfer.getIgrac();
		Klub klub = transfer.getKlub();
		igrac.setKlub(klub);
		double budzet = klub.getBudzet() - transfer.getCena();
		klub.setBudzet(budzet);
		klub.getIgraci().add(igrac);
		klub.getTransferi().add(transfer);
		igrac.getTransferi().add(transfer);

		klub = klubService.save(klub);
		igrac = igracService.save(igrac);


		return transferRepository.save(transfer);
	}

}