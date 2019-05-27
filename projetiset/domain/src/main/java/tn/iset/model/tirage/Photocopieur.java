package tn.iset.model.tirage;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    @JsonIgnoreProperties("photocopieur")
    @OneToMany
    private List<Intervention> interventions;

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

	public List<Intervention> getInterventions() {
		  if (interventions == null) {
	            interventions = new ArrayList<>();
	        }
		return interventions;
	}

	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}
	
	 public void addIntervention(Intervention intervention) {
	        getInterventions().add(intervention);
	    }

	    public void removeIntervention(Intervention intervention) {
	        getInterventions().remove(intervention);
	    }

}