package tn.iset.model.tirage;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author bahri
 */
@Entity
public class Recharge {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private Date date_recharge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_recharge() {
        return date_recharge;
    }

    public void setDate_recharge(Date date_recharge) {
        this.date_recharge = date_recharge;
    }

}