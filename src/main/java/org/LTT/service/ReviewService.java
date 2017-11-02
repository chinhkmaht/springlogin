package org.LTT.service;

import java.util.Date;

import org.LTT.persistence.dao.ReviewInterface;
import org.LTT.persistence.dao.ReviewsRepository;
import org.LTT.persistence.dao.UserRepository;
import org.LTT.persistence.model.Reviews;
import org.LTT.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements ReviewInterface {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ReviewsRepository reviewsRepository;

	@Override
	public void addReviewsinternship(Reviews reviews) {
		// TODO Auto-generated method stub
		
		System.out.println("addReviewsinternship   --------------------  ");
		reviews.setCreated(new Date());
		reviews.setModified(new Date());
		reviews.setNote(reviews.getNote());
		reviews.setRank(reviews.getRank());
		reviews.setStatus(true);
		reviews.setReviewTo(reviews.getReviewTo());
		reviews.setReviewsForm(reviews.getReviewsForm());
		reviews.setUserIdReview(reviews.getUserIdReview());
		User user = userRepository.findOne(reviews.getUserIdReview());
		String usernamereview = user.getFirstName() + " " + user.getLastName();
		reviews.setUserReviewName(usernamereview);

		reviews.setUserIdrv(reviews.getUserIdrv());
		User userrv = userRepository.findOne(reviews.getUserIdrv());
		String usernamerv = userrv.getFirstName() + " " + userrv.getLastName();
		reviews.setUsername(usernamerv);
		System.out.println("addReviewsinternship   --------reviews------------  "+reviews);
		reviewsRepository.save(reviews);
	}

}
