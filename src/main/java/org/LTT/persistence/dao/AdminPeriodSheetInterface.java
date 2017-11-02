package org.LTT.persistence.dao;

import org.LTT.persistence.model.PeriodTimesheet;

public interface AdminPeriodSheetInterface {
	void adminPriod(PeriodTimesheet periodTimesheet);
	//public RegistrationPeriod findbyid(long id); void editRegistrationPeriod(RegistrationPeriod registrationPeriod);
	public PeriodTimesheet findbyid(long id);
	
	void editPeriod(PeriodTimesheet periodTimesheet);
}
