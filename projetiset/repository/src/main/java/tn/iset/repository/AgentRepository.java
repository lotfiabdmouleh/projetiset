package tn.iset.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {

}
