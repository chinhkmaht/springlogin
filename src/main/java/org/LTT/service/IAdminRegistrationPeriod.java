package org.LTT.service;

import org.LTT.persistence.model.RegistrationPeriod;
import org.LTT.persistence.model.Timesheet;

public interface IAdminRegistrationPeriod {
	void adminRegistration(RegistrationPeriod registrationPeriod);
	void editRegistrationPeriod(RegistrationPeriod registrationPeriod);
	public RegistrationPeriod findbyid(long id);

}

