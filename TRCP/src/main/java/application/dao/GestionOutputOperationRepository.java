package application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.modeles.OutputOperation;

public interface GestionOutputOperationRepository extends JpaRepository<OutputOperation, Integer> {
	public List<OutputOperation> getOutputOperationByReferenceDemande(String referenceDemande);

	public List<OutputOperation> getOutputOperationByNum(String num);
}