package tn.iset.reopsitory.tirage;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.model.tirage.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {

}
