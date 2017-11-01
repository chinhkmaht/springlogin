package org.LTT.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.LTT.persistence.dao.PeriodTimesheetRepository;
import org.LTT.persistence.dao.ResgistrationPeriodReposition;
import org.LTT.persistence.dao.UserRepository;
import org.LTT.persistence.model.PeriodTimesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPeriodTimesheet implements IAdminPeriodSheet{
	@Autowired
	private ResgistrationPeriodReposition resgistrationPeriodReposition;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PeriodTimesheetRepository periodTimesheetRepository;


	@Override
	public void adminPriod(PeriodTimesheet periodTimesheet) {
		// TODO Auto-generated method stub
		
		String pattern = "yyyy:MM:dd";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		
		periodTimesheet.setCreated(new Date());
		periodTimesheet.setModifed(new Date());
		String periodTime=periodTimesheet.getFormDate() +" "+periodTimesheet.getToDate();

		periodTimesheet.setNamePeriod(periodTimesheet.getNamePeriod());
		periodTimesheet.setFormDate(periodTimesheet.getFormDate());
		periodTimesheet.setToDate(periodTimesheet.getToDate());
		periodTimesheet.setTimeperiod(periodTime);
		periodTimesheet.setNoteFriday(periodTimesheet.getNoteFriday());
		periodTimesheet.setNoteMonday(periodTimesheet.getNoteMonday());
		periodTimesheet.setNoteThursday(periodTimesheet.getNoteThursday());
		periodTimesheet.setNoteTuesday(periodTimesheet.getNoteTuesday());
		periodTimesheet.setNoteWednesday(periodTimesheet.getNoteWednesday());
		periodTimesheet.setStatus(true);
		System.out.println("periodTimesheet = "+periodTimesheet);
		periodTimesheetRepository.save(periodTimesheet);
		
	}

	@Override
	public PeriodTimesheet findbyid(long id) {
		// TODO Auto-generated method stub
		return periodTimesheetRepository.findOne(id);
	
	}

	@Override
	public void editPeriod(PeriodTimesheet periodTimesheet) {
		// TODO Auto-generated method stub
		periodTimesheet.setCreated(new Date());
		periodTimesheet.setModifed(new Date());
		String periodTime=periodTimesheet.getFormDate() +" "+periodTimesheet.getToDate();
		periodTimesheet.setNamePeriod(periodTimesheet.getNamePeriod());
		periodTimesheet.setFormDate(periodTimesheet.getFormDate());
		periodTimesheet.setToDate(periodTimesheet.getToDate());
		periodTimesheet.setTimeperiod(periodTime);
		periodTimesheet.setNoteFriday(periodTimesheet.getNoteFriday());
		periodTimesheet.setNoteMonday(periodTimesheet.getNoteMonday());
		periodTimesheet.setNoteThursday(periodTimesheet.getNoteThursday());
		periodTimesheet.setNoteTuesday(periodTimesheet.getNoteTuesday());
		periodTimesheet.setNoteWednesday(periodTimesheet.getNoteWednesday());
		periodTimesheet.setStatus(true);
		System.out.println("periodTimesheet = "+periodTimesheet);
		periodTimesheetRepository.save(periodTimesheet);
	}
	
}
