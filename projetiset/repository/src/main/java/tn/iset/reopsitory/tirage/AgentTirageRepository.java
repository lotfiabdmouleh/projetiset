package tn.iset.reopsitory.tirage;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.model.tirage.AgentTirage;

public interface AgentTirageRepository extends JpaRepository<AgentTirage, Long> {

}
