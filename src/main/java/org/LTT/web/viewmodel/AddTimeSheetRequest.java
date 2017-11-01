package org.LTT.web.viewmodel;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddTimeSheetRequest {
    private Long userId;

    @Size(min = 0, max = 10)
    private String note;

    @NotEmpty(message = "*Please provide your fromTime")
    private String fromTime;

    @NotEmpty(message = "*Please provide your toTime")
    private String toTime;
    private  Date createDate;
    private String username;
    
    private boolean status;
    public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }
}
