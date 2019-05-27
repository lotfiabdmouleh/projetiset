package tn.iset.reopsitory.tirage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.iset.model.tirage.Tirage;

public interface TirageRepository extends JpaRepository<Tirage, Long> {

	@Query(value=" SELECT e FROM DemandeTirage e WHERE e.etat like %:type%")
	List getdem(
			@Param("type")String type);
}
