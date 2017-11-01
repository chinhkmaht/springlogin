package org.LTT.persistence.dao;

import org.LTT.persistence.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalRepository extends JpaRepository<Local,Long>{

}
