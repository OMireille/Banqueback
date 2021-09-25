package bf.lonab.banqueback.dao;

import javax.management.openmbean.OpenMBeanOperationInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banqueback.entites.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
