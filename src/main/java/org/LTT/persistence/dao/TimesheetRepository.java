package org.LTT.persistence.dao;

import org.LTT.persistence.model.Timesheet;
import org.LTT.web.viewmodel.AddTimeSheetRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet,Long> {
    List<Timesheet>findByStatus(boolean status);


}
