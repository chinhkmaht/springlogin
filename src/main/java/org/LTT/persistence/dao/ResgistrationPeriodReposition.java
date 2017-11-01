package org.LTT.persistence.dao;

import org.LTT.persistence.model.RegistrationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResgistrationPeriodReposition extends JpaRepository<RegistrationPeriod,Long> {
    List<RegistrationPeriod>findByStatus(boolean status);
    RegistrationPeriod findByStatusAndId(boolean status,long id);
     
}
