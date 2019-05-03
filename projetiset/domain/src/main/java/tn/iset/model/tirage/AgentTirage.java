package tn.iset.model.tirage;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import tn.iset.model.User;

/**
 * @author bahri
 */
@Entity
public class AgentTirage extends User {

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

}