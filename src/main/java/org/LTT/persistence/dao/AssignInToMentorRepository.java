package org.LTT.persistence.dao;

import org.LTT.persistence.model.AssignInToM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignInToMentorRepository extends JpaRepository<AssignInToM,Long>{

    List<AssignInToM> findByUserInterIsNull();

    List<AssignInToM>findByStatus(boolean status);
}
