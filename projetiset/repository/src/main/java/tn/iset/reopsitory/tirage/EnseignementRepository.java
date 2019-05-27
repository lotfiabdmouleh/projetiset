package tn.iset.reopsitory.tirage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.iset.model.tirage.Enseignement;

public interface EnseignementRepository extends JpaRepository<Enseignement, Long> {
	@Query(value=" SELECT e FROM Enseignement e WHERE e.enseignant.username= :username")
	List getEns(
	  @Param("username") String username)
	;
}
