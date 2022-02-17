package com.ftninformatika.jwd.web.dto;

import javax.validation.constraints.Positive;

public class TransferDTO {

	private Long id;

	private IgracDTO igracDTO;

	private KlubDTO klubDTO;

	@Positive
	private double cena;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IgracDTO getIgracDTO() {
		return igracDTO;
	}

	public void setIgracDTO(IgracDTO igracDTO) {
		this.igracDTO = igracDTO;
	}

	public KlubDTO getKlubDTO() {
		return klubDTO;
	}

	public void setKlubDTO(KlubDTO klubDTO) {
		this.klubDTO = klubDTO;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
}