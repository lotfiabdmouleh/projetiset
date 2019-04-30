package tn.iset.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@Table(name = "REVINFO")
@RevisionEntity(MyRevisionEntityListener.class)
public class MyRevisionEntity implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @RevisionNumber
  @Column(name = "REV", nullable = false, updatable = false)
  private Integer id;
  @RevisionTimestamp
  @Column(name = "REVTSTMP", nullable = false, updatable = false)
  private Date timestamp;
  @Column(name = "MODIFIED_BY", length = 100)
  private String modifiedBy;
public String getModifiedBy() {
	return modifiedBy; 
}
public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
}
  
  
}