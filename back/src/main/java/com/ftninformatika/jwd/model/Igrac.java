package com.ftninformatika.jwd.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Igrac {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name = "ime_i_prezime")
	private String imeIprezime;
	
	@Column
	private String pozicija;
	
	@Column(name = "broj_dresa")
	private int brojDresa;
	
	@Column(nullable = false, name ="datum_rodjenja")
	private LocalDate rodjenje;
	
	@Column(name = "na_prodaju")
	private boolean prodaja;
	
	@ManyToOne
	private Klub klub;
	
	@OneToMany(mappedBy = "igrac", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Transfer> transferi;

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

	public LocalDate getRodjenje() {
		return rodjenje;
	}

	public void setRodjenje(LocalDate rodjenje) {
		this.rodjenje = rodjenje;
	}

	public boolean isProdaja() {
		return prodaja;
	}

	public void setProdaja(boolean prodaja) {
		this.prodaja = prodaja;
	}

	public Klub getKlub() {
		return klub;
	}

	public void setKlub(Klub klub) {
		this.klub = klub;
	}
		
	public List<Transfer> getTransferi() {
		return transferi;
	}

	public void setTransferi(List<Transfer> transferi) {
		this.transferi = transferi;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Igrac other = (Igrac) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Igrac [id=" + id + ", imeIprezime=" + imeIprezime + ", pozicija=" + pozicija + ", brojDresa="
				+ brojDresa + ", rodjenje=" + rodjenje + ", prodaja=" + prodaja + ", klub=" + klub + ", transferi="
				+ transferi + "]";
	}	
}