package tn.iset.reopsitory.tirage;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.model.tirage.Tirage;

public interface TirageRepository extends JpaRepository<Tirage, Long> {

}
