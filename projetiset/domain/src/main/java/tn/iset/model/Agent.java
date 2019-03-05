package tn.iset.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Agent implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nom;
	private String prenom;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date DateModification;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date DateAjout;
	public Agent(String nom, String prenom, Date dateModification, Date dateAjout) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		DateModification = dateModification;
		DateAjout = dateAjout;
	}
	
	public Agent() {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateModification() {
		return DateModification;
	}
	public void setDateModification(Date dateModification) {
		DateModification = dateModification;
	}
	public Date getDateAjout() {
		return DateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		DateAjout = dateAjout;
	}
	

}
