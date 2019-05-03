package tn.iset.reopsitory.tirage;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.model.tirage.Enseignement;

public interface EnseignementRepository extends JpaRepository<Enseignement, Long> {

}
