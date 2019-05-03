package tn.iset.model.tirage;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author bahri
 */
@Entity
@DiscriminatorColumn(length = 31)
public class Consommable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String des;

    @Basic
    private int qte_stck;

    @Basic
    private String seuil_max;

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

    public int getQte_stck() {
        return qte_stck;
    }

    public void setQte_stck(int qte_stck) {
        this.qte_stck = qte_stck;
    }

    public String getSeuil_max() {
        return seuil_max;
    }

    public void setSeuil_max(String seuil_max) {
        this.seuil_max = seuil_max;
    }

}