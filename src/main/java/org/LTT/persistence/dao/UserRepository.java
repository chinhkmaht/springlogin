package org.LTT.persistence.dao;

import org.LTT.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByEnabled(boolean enabled);
    
    List<User> findByEnabledAndRoleId(boolean status,long roleid);
    
    User findByEnabledAndId(boolean status, long id);
    List<User> findByLastNameOrFirstName(String lastname,String firtname);
    List<User>findByLastNameOrFirstNameOrUniversityId(String lastname,String firtname,long univer);
    List<User>findByLastNameAndLastNameAndUniversityId(String lastname,String firtname,long univer);
    List<User> findByUniversity(long univer);
    List<User> findByCompanyCardsIsNullAndEnabled(boolean status);
    List<User> findByCompanycardIdIsNullAndEnabled(boolean status);
    List<User> findByLastNameLikeOrFirstNameLike(String lastname, String firtname);
    List<User>findByAssignToIsLessThanAndEnabledAndRoleId(long check,boolean status,long roleid);

    List<User>findByTimesheetIsNullAndEnabledAndRoleId(boolean status,long roleid);
    List<User>findByEnabledAndLocalIdIsNullAndRoleId(boolean status,long roleid);
    @Override
    void delete(User user);

}
