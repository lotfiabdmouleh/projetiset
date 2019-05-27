package tn.iset.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.iset.model.Agent;
import tn.iset.model.tirage.Matiere;
@CrossOrigin
@RestController
@RequestMapping("/historique")
public class HistoriqueController {
@Autowired
EntityManager entityManager;

@GetMapping("/all")
@ResponseBody
public List getAllHistory(){
	/*return	entityManager.createNativeQuery("select  r.modified_by,r.revtstmp , a.revtype,m.revtype x from revinfo r,agent_aud a,matiere_aud m where a.rev=r.rev or r.rev=m.rev ")
		
			.getResultList();
		}
	*/
	List revisions = new ArrayList<>() ;
	List entities=new ArrayList<Class<T>>();
	entities.add(Agent.class);
	entities.add(Matiere.class);
	for(int i=0;i<entities.size();i++){
		
	
	revisions.addAll(AuditReaderFactory.get(entityManager)
            .createQuery()
            .forRevisionsOfEntity((Class<?>) entities.get(i), false, true)
            .addProjection(AuditEntity.id())
            .addProjection( AuditEntity.revisionProperty("timestamp"))
            .addProjection(AuditEntity.revisionProperty("modifiedBy"))
            .addProjection(AuditEntity.revisionType())
            .getResultList()
		);
	}
	
	
	return revisions;
}
}

