package tn.iset.model.tirage;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author bahri
 */
@Entity
public class Papier {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String format;
private int nb_feuille;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

	public int getNb_feuille() {
		return nb_feuille;
	}

	public void setNb_feuille(int nb_feuille) {
		this.nb_feuille = nb_feuille;
	}

}