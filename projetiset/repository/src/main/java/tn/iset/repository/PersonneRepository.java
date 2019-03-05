package tn.iset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.iset.model.Persone;

@Repository
public interface PersonneRepository extends JpaRepository<Persone, Long> {

}
