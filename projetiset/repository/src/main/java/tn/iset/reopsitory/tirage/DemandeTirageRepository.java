package tn.iset.reopsitory.tirage;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.model.tirage.DemandeTirage;

public interface DemandeTirageRepository extends JpaRepository<DemandeTirage, Long> {

}
