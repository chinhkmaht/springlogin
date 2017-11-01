package org.LTT.persistence.dao;

import org.LTT.persistence.model.PeriodTimesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeriodTimesheetRepository extends JpaRepository<PeriodTimesheet,Long> {
    List<PeriodTimesheet>findByStatus(boolean status);
    PeriodTimesheet findByStatusAndId(boolean status,long id);
}
