package com.example.Ruokavalio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ravinto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String ravinto;
	private double maara;
	private double kalorit;
	private double hiilihydraatit;
	private double proteiinit;
	
	// ID:n generointi automaattisesti
	
	public Ravinto() {
		super();
	}

	public Ravinto(String ravinto, double maara, double kalorit, double hiilihydraatit, double proteiinit) {
		super();
		this.ravinto = ravinto;
		this.maara = maara;
		this.kalorit = kalorit;
		this.hiilihydraatit = hiilihydraatit;
		this.proteiinit = proteiinit;
	}
	
	public long getId() {
		return id;
	}
	
	public String getRavinto() {
		return ravinto;
	}

	public double getMaara() {
		return maara;
	}
	
	public double getKalorit() {
		return kalorit;
	}

	public double getHiilihydraatit() {
		return hiilihydraatit;
	}

	public double getProteiinit() {
		return proteiinit;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRavinto(String ravinto) {
		this.ravinto = ravinto;
	}
	
	public void setMaara(double maara) {
		this.maara = maara;
	}
	
	public void setKalorit(double kalorit) {
		this.kalorit = kalorit;
	}

	public void setHiilihydraatit(double hiilihydraatit) {
		this.hiilihydraatit = hiilihydraatit;
	}

	public void setProteiinit(double proteiinit) {
		this.proteiinit = proteiinit;
	}

	@Override
	public String toString() {
		return "Ravinto [ravinto=" + ravinto + ", maara=" + maara + ", kalorit=" + kalorit + ", hiilihydraatit=" + hiilihydraatit + ", proteiinit=" + proteiinit
				+ "]";
	}
	
}
