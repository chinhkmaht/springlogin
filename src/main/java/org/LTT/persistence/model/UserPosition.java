package org.LTT.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userposition")
public class UserPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long userid;
    private boolean status;
    private String nameposition;
    private Date modifileDate;
    private Date createDate;

    public Long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setNameposition(String nameposition) {
        this.nameposition = nameposition;
    }

    public void setModifileDate(Date modifileDate) {
        this.modifileDate = modifileDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNameposition() {

        return nameposition;
    }

    public Date getModifileDate() {
        return modifileDate;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
