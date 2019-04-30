package tn.iset.model;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

class MyRevisionEntityListener implements RevisionListener {
	  @Override
	  public void newRevision(Object revisionEntity) {
	     ((MyRevisionEntity)  revisionEntity).setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());
	  }
	}