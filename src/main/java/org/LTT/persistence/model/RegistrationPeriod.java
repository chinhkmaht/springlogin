package org.LTT.persistence.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "registrationperiod")
public class RegistrationPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   
    private Long userId;
    
    private Long periodId;
    private String periodName;
    private String timePeriod;
    private String userName;
    private String timeMonday;
    
    private String toDateMonday;
    private String fromDateMonday;
    private String toDateTuesday;
    private String fromDateTuesday;
    private String toDateWednesday;
    private String fromDateWednesday;
   
    private String toDateThursday;
    private String fromDateThursday;
    private String toDateFriday;
    private String fromDateFriday;
    
    public String getToDateMonday() {
		return toDateMonday;
	}

	public void setToDateMonday(String toDateMonday) {
		this.toDateMonday = toDateMonday;
	}

	public String getFromDateMonday() {
		return fromDateMonday;
	}

	public void setFromDateMonday(String fromDateMonday) {
		this.fromDateMonday = fromDateMonday;
	}

	public String getToDateTuesday() {
		return toDateTuesday;
	}

	public void setToDateTuesday(String toDateTuesday) {
		this.toDateTuesday = toDateTuesday;
	}

	public String getFromDateTuesday() {
		return fromDateTuesday;
	}

	public void setFromDateTuesday(String fromDateTuesday) {
		this.fromDateTuesday = fromDateTuesday;
	}

	public String getToDateWednesday() {
		return toDateWednesday;
	}

	public void setToDateWednesday(String toDateWednesday) {
		this.toDateWednesday = toDateWednesday;
	}

	public String getFromDateWednesday() {
		return fromDateWednesday;
	}

	public void setFromDateWednesday(String fromDateWednesday) {
		this.fromDateWednesday = fromDateWednesday;
	}

	public String getToDateThursday() {
		return toDateThursday;
	}

	public void setToDateThursday(String toDateThursday) {
		this.toDateThursday = toDateThursday;
	}

	public String getFromDateThursday() {
		return fromDateThursday;
	}

	public void setFromDateThursday(String fromDateThursday) {
		this.fromDateThursday = fromDateThursday;
	}

	public String getToDateFriday() {
		return toDateFriday;
	}

	public void setToDateFriday(String toDateFriday) {
		this.toDateFriday = toDateFriday;
	}

	public String getFromDateFriday() {
		return fromDateFriday;
	}

	public void setFromDateFriday(String fromDateFriday) {
		this.fromDateFriday = fromDateFriday;
	}

	private String timeTuesday;
    private String timeWednesday;
    private String timeThursday;
    private String timeFriday;
   
    private boolean status;

    
   

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getTimeMonday() {
        return timeMonday;
    }

    public void setTimeMonday(String timeMonday) {
        this.timeMonday = timeMonday;
    }

    public String getTimeTuesday() {
        return timeTuesday;
    }

    public void setTimeTuesday(String timeTuesday) {
        this.timeTuesday = timeTuesday;
    }

    public String getTimeWednesday() {
        return timeWednesday;
    }

    public void setTimeWednesday(String timeWednesday) {
        this.timeWednesday = timeWednesday;
    }

    public String getTimeThursday() {
        return timeThursday;
    }

    public void setTimeThursday(String timeThursday) {
        this.timeThursday = timeThursday;
    }

    public String getTimeFriday() {
        return timeFriday;
    }

    public void setTimeFriday(String timeFriday) {
        this.timeFriday = timeFriday;
    }

    
}
