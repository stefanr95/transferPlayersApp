package com.ftninformatika.jwd.web.dto;

public class IgracDTO {

	private Long id;

	private String imeIprezime;
	
	private String pozicija;
	
	private int brojDresa;
	
	private String rodjenje;
	
	private boolean prodaja;
	
	private KlubDTO klubDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImeIprezime() {
		return imeIprezime;
	}

	public void setImeIprezime(String imeIprezime) {
		this.imeIprezime = imeIprezime;
	}

	public String getPozicija() {
		return pozicija;
	}

	public void setPozicija(String pozicija) {
		this.pozicija = pozicija;
	}

	public int getBrojDresa() {
		return brojDresa;
	}

	public void setBrojDresa(int brojDresa) {
		this.brojDresa = brojDresa;
	}

	public String getRodjenje() {
		return rodjenje;
	}

	public void setRodjenje(String rodjenje) {
		this.rodjenje = rodjenje;
	}

	public boolean isProdaja() {
		return prodaja;	
	}

	public void setProdaja(boolean prodaja) {
		this.prodaja = prodaja;
	}

	public KlubDTO getKlubDTO() {
		return klubDTO;
	}

	public void setKlubDTO(KlubDTO klubDTO) {
		this.klubDTO = klubDTO;
	}
}