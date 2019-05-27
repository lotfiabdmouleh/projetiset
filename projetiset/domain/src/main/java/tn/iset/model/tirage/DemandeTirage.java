package tn.iset.model.tirage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tn.iset.model.Auditable;

/**
 * @author bahri
 */
@Entity
public class DemandeTirage extends Auditable<String>{

    @Id
    @GeneratedValue
    private Long id;

  private String file;

	@JsonIgnoreProperties("demandeTirages")
	@ManyToOne
	private Enseignement enseignement;
	
	private int nb_copie;
	@Size(max = 500)
	private String etat;
	
	 

   

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  


	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	

	public Enseignement getEnseignement() {
		return enseignement;
	}

	public void setEnseignement(Enseignement enseignement) {
		this.enseignement = enseignement;
	}

	public int getNb_copie() {
		return nb_copie;
	}

	public void setNb_copie(int nb_copie) {
		this.nb_copie = nb_copie;
	}

   

}