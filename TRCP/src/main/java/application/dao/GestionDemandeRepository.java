package application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.modeles.Demande;

public interface GestionDemandeRepository extends JpaRepository<Demande, Integer> {
	public List<Demande> getDemandeByReferenceDemande(String referenceDemande);
}