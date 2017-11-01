package org.LTT.service;

import java.util.Arrays;
import java.util.Date;

import org.LTT.persistence.dao.TimesheetRepository;
import org.LTT.persistence.model.Timesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AddTimesheettestservice implements ITimesheetService {

	@Autowired
	private TimesheetRepository timesheetRepository;

	@Override
	public void addtimesheet(Timesheet timesheet) {
		timesheet.setCreateDate(new Date());
		timesheet.setFormtime(timesheet.getFormtime());
		timesheet.setTotime(timesheet.getTotime());
		timesheet.setNote(timesheet.getNote());
		timesheet.setStatus(true);
		timesheetRepository.save(timesheet);
	}

}
