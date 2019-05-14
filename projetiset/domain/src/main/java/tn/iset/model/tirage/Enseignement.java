package tn.iset.model.tirage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

}