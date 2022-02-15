package com.ftninformatika.jwd.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Klub {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String naziv;
	
	@Column(nullable = false)
	private double budzet;
	
	@OneToMany(mappedBy = "klub", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Igrac> igraci;
	
	@OneToMany(mappedBy = "klub", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Transfer> transferi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getBudzet() {
		return budzet;
	}

	public void setBudzet(double budzet) {
		this.budzet = budzet;
	}
	
	public List<Igrac> getIgraci() {
		return igraci;
	}

	public void setIgraci(List<Igrac> igraci) {
		this.igraci = igraci;
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
		Klub other = (Klub) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Klub [id=" + id + ", naziv=" + naziv + ", budzet=" + budzet + ", igraci=" + igraci + ", transferi="
				+ transferi + ", getId()=" + getId() + ", getNaziv()=" + getNaziv() + ", getBudzet()=" + getBudzet()
				+ ", getIgraci()=" + getIgraci() + ", getTransferi()=" + getTransferi() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}	
}