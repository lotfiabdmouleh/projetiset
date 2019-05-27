package tn.iset.model.tirage;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import tn.iset.model.User;

/**
 * @author bahri
 */
@Entity
public class AgentTirage extends User {
	
	@OneToMany
	private List<Tirage> tirages;

    @ManyToMany
    private List<Consommable> consommables;

    public List<Consommable> getConsommables() {
        if (consommables == null) {
            consommables = new ArrayList<>();
        }
        return consommables;
    }

    public void setConsommables(List<Consommable> consommables) {
        this.consommables = consommables;
    }

    public void addConsommable(Consommable consommable) {
        getConsommables().add(consommable);
    }

    public void removeConsommable(Consommable consommable) {
        getConsommables().remove(consommable);
    }
   
    public List<Tirage> getTirages() {
        if (tirages == null) {
        	tirages = new ArrayList<>();
        }
        return tirages;
    }

    public void setTirages(List<Tirage> tirages) {
        this.tirages = tirages;
    }

    public void addTirage(Tirage tirages) {
        getTirages().add(tirages);
    }

    public void removeTirage(Tirage tirages) {
        getTirages().remove(tirages);
    }

}