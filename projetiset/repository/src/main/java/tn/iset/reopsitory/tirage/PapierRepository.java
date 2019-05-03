package tn.iset.reopsitory.tirage;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.model.tirage.Papier;

public interface PapierRepository extends JpaRepository<Papier, Long> {

}
