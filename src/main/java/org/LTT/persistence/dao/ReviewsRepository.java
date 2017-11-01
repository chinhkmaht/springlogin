package org.LTT.persistence.dao;

import org.LTT.persistence.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
	

    List<Reviews> findByUserIdReviewAndStatus(long userreview,boolean status);

    List<Reviews> findByUserIdrv(long useridrv);
}
