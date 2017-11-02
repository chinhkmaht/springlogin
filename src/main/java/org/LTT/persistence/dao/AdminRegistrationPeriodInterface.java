package org.LTT.persistence.dao;

import org.LTT.persistence.model.RegistrationPeriod;

public interface AdminRegistrationPeriodInterface {
	void adminRegistration(RegistrationPeriod registrationPeriod);
	void editRegistrationPeriod(RegistrationPeriod registrationPeriod);
	public RegistrationPeriod findbyid(long id);

}

