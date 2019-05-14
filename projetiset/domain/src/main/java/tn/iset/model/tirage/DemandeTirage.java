package tn.iset.model.tirage;

import java.sql.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import tn.iset.model.Auditable;

/**
 * @author bahri
 */
@Entity
public class DemandeTirage extends Auditable<String>{

    @Id
    @GeneratedValue
    private Long id;

  

    @OneToMany
    private List<Enseignement> enseignements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  

	public List<Enseignement> getEnseignements() {
		return enseignements;
	}

	public void setEnseignements(List<Enseignement> enseignements) {
		this.enseignements = enseignements;
	}

   

}