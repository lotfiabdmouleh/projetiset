package tn.iset.reopsitory.tirage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.iset.model.tirage.DemandeTirage;

public interface DemandeTirageRepository extends JpaRepository<DemandeTirage, Long> {

	@Query(value=" SELECT e FROM DemandeTirage e WHERE e.enseignement.enseignant.username= :username")
	List getEns(
	  @Param("username") String username)
	;

}
