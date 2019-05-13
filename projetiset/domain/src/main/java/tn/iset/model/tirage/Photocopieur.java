package tn.iset.model.tirage;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author bahri
 */
@Entity
public class Photocopieur {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String des;

    @Basic
    private String reference;
	
    @OneToMany
    private List<Recharge> recharges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

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