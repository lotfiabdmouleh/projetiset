package tn.iset.reopsitory.tirage;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.model.tirage.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {

}
