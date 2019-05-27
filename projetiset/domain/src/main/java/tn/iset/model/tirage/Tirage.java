package tn.iset.model.tirage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import tn.iset.model.Auditable;

/**
 * @author bahri
 */
@Entity
public class Tirage extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;


    @OneToOne
    private Photocopieur photocopieur;


    @ManyToOne
    private Papier papiers;

    @OneToMany
    private List<DemandeTirage> demandeTirages;

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

   

    public Papier getPapiers() {
		return papiers;
	}

	public void setPapiers(Papier papiers) {
		this.papiers = papiers;
	}

	public List<DemandeTirage> getDemandeTirages() {
        if (demandeTirages == null) {
            demandeTirages = new ArrayList<>();
        }
        return demandeTirages;
    }

    public void setDemandeTirages(List<DemandeTirage> demandeTirages) {
        this.demandeTirages = demandeTirages;
    }

    public void addDemandeTirage(DemandeTirage demandeTirage) {
        getDemandeTirages().add(demandeTirage);
    }

    public void removeDemandeTirage(DemandeTirage demandeTirage) {
        getDemandeTirages().remove(demandeTirage);
    }

}