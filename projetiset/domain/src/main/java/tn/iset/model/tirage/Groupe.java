package tn.iset.model.tirage;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author bahri
 */
@Entity
public class Groupe {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String nom_grp;

    @Basic
    private int nb_etd;

    @Basic
    private String filiere;

    @Basic
    private int niveau;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_grp() {
        return nom_grp;
    }

    public void setNom_grp(String nom_grp) {
        this.nom_grp = nom_grp;
    }

    public int getNb_etd() {
        return nb_etd;
    }

    public void setNb_etd(int nb_etd) {
        this.nb_etd = nb_etd;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

}