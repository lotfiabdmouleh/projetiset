package tn.iset.reopsitory.tirage;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.model.tirage.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {

}
