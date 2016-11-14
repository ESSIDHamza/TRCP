package application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import application.modeles.InformationsExternes;

public interface GestionInformationsExternesRepository extends JpaRepository<InformationsExternes, Integer> {
}