package tn.iset.reopsitory.tirage;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.model.tirage.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

}
