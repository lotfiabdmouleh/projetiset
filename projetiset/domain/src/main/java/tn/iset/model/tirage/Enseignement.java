package tn.iset.model.tirage;

import java.util.ArrayList;
import java.util.List;

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
public class Enseignement extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Matiere matiere;

    @OneToOne
    private Groupe groupe;

    @OneToOne
    private Departement departement;

    @OneToOne
    private Enseignant enseignant;
    
    @OneToOne
    private Annee annee;

    @OneToOne
    private Semestre semestre;
    @OneToMany
    private List<DemandeTirage> demandetirages;
 
 public List<DemandeTirage> getDemandeTirages() {
		if (demandetirages == null) {
			demandetirages = new ArrayList<>();
        }
		return demandetirages;
	}

	public void setDemandeTirages(List<DemandeTirage> demandetirages) {
		this.demandetirages = demandetirages;
	}

		
	 public void addDemandeTirages(DemandeTirage demandetirages) {
		 getDemandeTirages().add(demandetirages);
	    }

	    public void removeDemandeTirages(DemandeTirage demandetirages) {
	    	getDemandeTirages().remove(demandetirages);
	    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

	public Annee getAnnee() {
		return annee;
	}

	public void setAnnee(Annee annee) {
		this.annee = annee;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
    
    

}