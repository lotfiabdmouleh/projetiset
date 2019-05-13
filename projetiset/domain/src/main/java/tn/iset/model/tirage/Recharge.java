package tn.iset.model.tirage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tn.iset.model.Auditable;

/**
 * @author bahri
 */


@Entity
public class Recharge extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;
    @JsonIgnoreProperties("recharges")
    @ManyToOne
    private Photocopieur photocopieur;
    @JsonIgnoreProperties("recharges")
    @ManyToOne
    private Ancre ancre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

	public Photocopieur getPhotocopieur() {
		return photocopieur;
	}

	public void setPhotocopieur(Photocopieur photocopieur) {
		this.photocopieur = photocopieur;
	}

	public Ancre getAncre() {
		return ancre;
	}

	public void setAncre(Ancre ancre) {
		this.ancre = ancre;
	}

	

}