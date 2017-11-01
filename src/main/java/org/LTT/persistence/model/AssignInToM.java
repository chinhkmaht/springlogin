package org.LTT.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "assignInterToMenter")
public class AssignInToM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userInter;
    private Long userMentor;
    private Date modifileDate;
    private Date createDate;
    private String firtnameinter;
    private String lastnameinter;
    private String firtnamementor;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	private String lastnamementor;
    private boolean status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserInter() {
		return userInter;
	}

	public String getFirtnameinter() {
		return firtnameinter;
	}

	public void setFirtnameinter(String firtnameinter) {
		this.firtnameinter = firtnameinter;
	}

	public String getLastnameinter() {
		return lastnameinter;
	}

	public void setLastnameinter(String lastnameinter) {
		this.lastnameinter = lastnameinter;
	}

	public String getFirtnamementor() {
		return firtnamementor;
	}

	public void setFirtnamementor(String firtnamementor) {
		this.firtnamementor = firtnamementor;
	}

	public String getLastnamementor() {
		return lastnamementor;
	}

	public void setLastnamementor(String lastnamementor) {
		this.lastnamementor = lastnamementor;
	}

	public void setUserInter(Long userInter) {
		this.userInter = userInter;
	}
	public Long getUserMentor() {
		return userMentor;
	}
	public void setUserMentor(Long userMentor) {
		this.userMentor = userMentor;
	}
	public Date getModifileDate() {
		return modifileDate;
	}
	public void setModifileDate(Date modifileDate) {
		this.modifileDate = modifileDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
