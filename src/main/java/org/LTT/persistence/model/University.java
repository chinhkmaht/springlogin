package org.LTT.persistence.model;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameUniver;


    private boolean status;

    public Long getId() {
        return id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNameUniver() {
        return nameUniver;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "university")
    private User users;

    public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public void setNameUniver(String nameUniver) {
        System.out.println("nameUniver   "+nameUniver);
        this.nameUniver = nameUniver;
    }

    public void setModifileDate(Date modifileDate) {
        this.modifileDate = modifileDate;
    }

    public Date getCreateDate() {

        return createDate;
    }

    public Date getModifileDate() {
        return modifileDate;
    }

    private Date createDate;

    private Date modifileDate;


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("University [nameUniver=").append(nameUniver).append("]").append("[id=").append(id).append("]").append("[status=").append(status).append("]");
        return builder.toString();
    }

}
