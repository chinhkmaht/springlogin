package org.LTT.service;

import java.util.Date;

import org.LTT.persistence.dao.CompanyCardRepository;
import org.LTT.persistence.dao.UserRepository;
import org.LTT.persistence.model.CompanyCard;
import org.LTT.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCardService implements IAdminCardService {

	@Autowired
	CompanyCardRepository companyCardRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void addNewCard(CompanyCard companyCard) {
		// TODO Auto-generated method stub
		long idcard = 1;
		companyCard.setCreateDate(new Date());
		companyCard.setModifileDate(new Date());
		companyCard.setName(companyCard.getName());
		companyCard.setStatus(true);
		companyCard.setUserId(companyCard.getUserId());
		companyCardRepository.save(companyCard);
		User user = userRepository.findOne(companyCard.getUserId());
		user.setCompanycardId(idcard);
		userRepository.save(user);
	}

}
