package tn.iset.model.tirage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author bahri
 */
@Entity
public class Tirage {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private Date date_tirage;

    @OneToOne
    private Photocopieur photocopieur;

    @OneToOne
    private AgentTirage agentTirage;

    @OneToMany
    private List<Papier> papiers;

    @OneToMany
    private List<DemandeTirage> demandeTirages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_tirage() {
        return date_tirage;
    }

    public void setDate_tirage(Date date_tirage) {
        this.date_tirage = date_tirage;
    }

    public Photocopieur getPhotocopieur() {
        return photocopieur;
    }

    public void setPhotocopieur(Photocopieur photocopieur) {
        this.photocopieur = photocopieur;
    }

    public AgentTirage getAgentTirage() {
        return agentTirage;
    }

    public void setAgentTirage(AgentTirage agentTirage) {
        this.agentTirage = agentTirage;
    }

    public List<Papier> getPapiers() {
        if (papiers == null) {
            papiers = new ArrayList<>();
        }
        return papiers;
    }

    public void setPapiers(List<Papier> papiers) {
        this.papiers = papiers;
    }

    public void addPapier(Papier papier) {
        getPapiers().add(papier);
    }

    public void removePapier(Papier papier) {
        getPapiers().remove(papier);
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