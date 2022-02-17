package com.ftninformatika.jwd.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.model.Transfer;
import com.ftninformatika.jwd.service.IgracService;
import com.ftninformatika.jwd.service.KlubService;
import com.ftninformatika.jwd.service.TransferService;
import com.ftninformatika.jwd.web.dto.TransferDTO;

@Component
public class TransferDtoToTransfer implements Converter<TransferDTO, Transfer>{

	@Autowired
	private TransferService transferService;

	@Autowired
	private IgracService igracService;

	@Autowired
	private KlubService klubService;

	@Override
	public Transfer convert(TransferDTO dto) {
		Transfer transfer;

		if(dto.getId() == null) {
			transfer = new Transfer();
		} else {
			transfer = transferService.findOne(dto.getId());
		}

		if(transfer != null) {
			transfer.setIgrac(igracService.findOne(dto.getIgracDTO().getId()));
			transfer.setKlub(klubService.findOne(dto.getKlubDTO().getId()));
			transfer.setCena(dto.getCena());
		}

		return transfer;
	}
}
