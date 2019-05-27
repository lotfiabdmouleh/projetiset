package tn.iset.model.tirage;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

/**
 * @author bahri
 */
@Audited
@Entity
public class Matiere {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String nom_mat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_mat() {
        return nom_mat;
    }

    public void setNom_mat(String nom_mat) {
        this.nom_mat = nom_mat;
    }

}