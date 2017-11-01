package org.LTT.persistence.dao;

import org.LTT.persistence.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University,Long> {

    List<University> findByStatus(boolean status);
   

}
