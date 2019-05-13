package tn.iset.model.tirage;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author bahri
 */
@Entity
public class Ancre extends Consommable {
    @OneToMany
    private List<Recharge> recharges;

    public List<Recharge> getRecharges() {
        if (recharges == null) {
            recharges = new ArrayList<>();
        }
        return recharges;
    }

    public void setRecharges(List<Recharge> recharges) {
        this.recharges = recharges;
    }

    public void addRecharge(Recharge recharge) {
        getRecharges().add(recharge);
    }

    public void removeRecharge(Recharge recharge) {
        getRecharges().remove(recharge);
    }

}