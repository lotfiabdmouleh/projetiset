package tn.iset.model.tirage;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author bahri
 */
@Entity
public class DemandeTirage {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private Date date_demande;

    @OneToOne(mappedBy = "demandeTirage")
    private Enseignement enseignement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_demande() {
        return date_demande;
    }

    public void setDate_demande(Date date_demande) {
        this.date_demande = date_demande;
    }

    public Enseignement getEnseignement() {
        return enseignement;
    }

    public void setEnseignement(Enseignement enseignement) {
        this.enseignement = enseignement;
    }

}