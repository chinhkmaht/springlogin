package org.LTT.persistence.dao;

import org.LTT.persistence.model.CompanyCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyCardRepository  extends JpaRepository<CompanyCard,Long>{
        List<CompanyCard> findByStatus(boolean status);

        CompanyCard findByUserId(long userId);
}
