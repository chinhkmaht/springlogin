package org.LTT.service;

import org.LTT.persistence.dao.PeriodTimesheetRepository;
import org.LTT.persistence.dao.ResgistrationPeriodReposition;
import org.LTT.persistence.dao.UserRepository;
import org.LTT.persistence.model.PeriodTimesheet;
import org.LTT.persistence.model.RegistrationPeriod;
import org.LTT.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminRegistrationService implements IAdminRegistrationPeriod {
	@Autowired
	private ResgistrationPeriodReposition resgistrationPeriodReposition;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PeriodTimesheetRepository periodTimesheetRepository;

	@Override
	public void adminRegistration(RegistrationPeriod registrationPeriod) {
		// TODO Auto-generated method stub
		registrationPeriod.setFromDateFriday(registrationPeriod.getFromDateFriday());
		registrationPeriod.setFromDateMonday(registrationPeriod.getFromDateMonday());
		registrationPeriod.setFromDateThursday(registrationPeriod.getFromDateThursday());
		registrationPeriod.setFromDateTuesday(registrationPeriod.getFromDateTuesday());
		registrationPeriod.setFromDateWednesday(registrationPeriod.getFromDateWednesday());

		System.out.println("registrationPeriod.getFromDateTuesday()  = " + registrationPeriod.getFromDateTuesday());
		System.out.println("registrationPeriod.getFromDateWednesday()  = " + registrationPeriod.getFromDateWednesday());
		registrationPeriod.setToDateFriday(registrationPeriod.getToDateFriday());
		registrationPeriod.setToDateMonday(registrationPeriod.getToDateMonday());
		registrationPeriod.setToDateThursday(registrationPeriod.getToDateThursday());
		registrationPeriod.setToDateTuesday(registrationPeriod.getToDateTuesday());
		registrationPeriod.setToDateWednesday(registrationPeriod.getToDateWednesday());
		System.out.println("registrationPeriod.getUserId()  = " + registrationPeriod.getUserId());
		registrationPeriod.setUserId(registrationPeriod.getUserId());
		User user = userRepository.findOne(registrationPeriod.getUserId());
		String username = user.getFirstName() + " " + user.getLastName();
		registrationPeriod.setUserName(username);
		registrationPeriod.setPeriodId(registrationPeriod.getPeriodId());
		PeriodTimesheet periodTimesheet = periodTimesheetRepository.findOne(registrationPeriod.getPeriodId());
		String periodTimesheetName = periodTimesheet.getNamePeriod();
		registrationPeriod.setPeriodName(periodTimesheetName);
		registrationPeriod.setStatus(true);
		String timeFriday = registrationPeriod.getFromDateFriday() + " " + registrationPeriod.getToDateFriday();
		registrationPeriod.setTimeFriday(timeFriday);
		String timeMonday = registrationPeriod.getFromDateMonday() + " " + registrationPeriod.getToDateMonday();
		registrationPeriod.setTimeMonday(timeMonday);
		String timeThursday = registrationPeriod.getFromDateThursday() + " " + registrationPeriod.getToDateThursday();
		registrationPeriod.setTimeThursday(timeThursday);
		String timeTuesday = registrationPeriod.getFromDateTuesday() + " " + registrationPeriod.getToDateTuesday();
		registrationPeriod.setTimeTuesday(timeTuesday);
		String timeWednesday = registrationPeriod.getFromDateWednesday() + " "
				+ registrationPeriod.getToDateWednesday();
		registrationPeriod.setTimeWednesday(timeWednesday);

		resgistrationPeriodReposition.save(registrationPeriod);

	}

	public RegistrationPeriod findbyid(long id) {
		return resgistrationPeriodReposition.findOne(id);
	}

	@Override
	public void editRegistrationPeriod(RegistrationPeriod registrationPeriod) {
		// TODO Auto-generated method stub

		registrationPeriod.setFromDateFriday(registrationPeriod.getFromDateFriday());
		registrationPeriod.setFromDateMonday(registrationPeriod.getFromDateMonday());
		registrationPeriod.setFromDateThursday(registrationPeriod.getFromDateThursday());
		registrationPeriod.setFromDateTuesday(registrationPeriod.getFromDateTuesday());
		registrationPeriod.setFromDateWednesday(registrationPeriod.getFromDateWednesday());

		registrationPeriod.setToDateFriday(registrationPeriod.getToDateFriday());
		registrationPeriod.setToDateMonday(registrationPeriod.getToDateMonday());
		registrationPeriod.setToDateThursday(registrationPeriod.getToDateThursday());
		registrationPeriod.setToDateTuesday(registrationPeriod.getToDateTuesday());
		registrationPeriod.setToDateWednesday(registrationPeriod.getToDateWednesday());
		System.out.println("registrationPeriod.getUserId()  = " + registrationPeriod.getUserId());
		registrationPeriod.setUserId(registrationPeriod.getUserId());
		User user = userRepository.findOne(registrationPeriod.getUserId());
		String username = user.getFirstName() + " " + user.getLastName();
		registrationPeriod.setUserName(username);
		registrationPeriod.setPeriodId(registrationPeriod.getPeriodId());
		PeriodTimesheet periodTimesheet = periodTimesheetRepository.findOne(registrationPeriod.getPeriodId());
		String periodTimesheetName = periodTimesheet.getNamePeriod();
		registrationPeriod.setPeriodName(periodTimesheetName);
		registrationPeriod.setStatus(true);
		String timeFriday = registrationPeriod.getFromDateFriday() + " " + registrationPeriod.getToDateFriday();
		registrationPeriod.setTimeFriday(timeFriday);
		String timeMonday = registrationPeriod.getFromDateMonday() + " " + registrationPeriod.getToDateMonday();
		registrationPeriod.setTimeMonday(timeMonday);
		String timeThursday = registrationPeriod.getFromDateThursday() + " " + registrationPeriod.getToDateThursday();
		registrationPeriod.setTimeThursday(timeThursday);
		String timeTuesday = registrationPeriod.getFromDateTuesday() + " " + registrationPeriod.getToDateTuesday();
		registrationPeriod.setTimeTuesday(timeTuesday);
		String timeWednesday = registrationPeriod.getFromDateWednesday() + " "+ registrationPeriod.getToDateWednesday();
		registrationPeriod.setTimeWednesday(timeWednesday);

		resgistrationPeriodReposition.save(registrationPeriod);

	}

}
