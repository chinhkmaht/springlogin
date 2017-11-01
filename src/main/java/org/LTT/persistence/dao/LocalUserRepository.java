package org.LTT.persistence.dao;

import org.LTT.persistence.model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalUserRepository extends JpaRepository<LocalUser,Long>{
    List<LocalUser>findByStatus(boolean status);
}
