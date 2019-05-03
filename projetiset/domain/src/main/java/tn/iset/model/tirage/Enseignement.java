package tn.iset.model.tirage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author bahri
 */
@Entity
public class Enseignement {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private DemandeTirage demandeTirage;

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

    public DemandeTirage getDemandeTirage() {
        return demandeTirage;
    }

    public void setDemandeTirage(DemandeTirage demandeTirage) {
        this.demandeTirage = demandeTirage;
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