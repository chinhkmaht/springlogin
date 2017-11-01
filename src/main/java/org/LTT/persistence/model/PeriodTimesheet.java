package org.LTT.persistence.model;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "PeriodTimesheet")

public class PeriodTimesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

 
    @Length(min = 8, message = "*Your namePeriod must have at least 8 characters")
	@NotEmpty(message = "*Please provide your namePeriod")
    private String namePeriod;
    
    private  String timeperiod;
    
    @NotNull(message=" Please provide your from date")
    private Date formDate;
    
    @NotNull(message=" Please provide your to date")
    private Date toDate;
    
    private Date created;
    private Date modifed;

    private String noteMonday;
    private String noteTuesday;
    private String noteWednesday;
    private String noteThursday;
    private String noteFriday;
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePeriod() {
        return namePeriod;
    }

    public void setNamePeriod(String namePeriod) {
        this.namePeriod = namePeriod;
    }

    public String getTimeperiod() {
        return timeperiod;
    }

    public void setTimeperiod(String timeperiod) {
        this.timeperiod = timeperiod;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getFormDate() {
        return formDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getToDate() {
        return toDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModifed() {
        return modifed;
    }

    public void setModifed(Date modifed) {
        this.modifed = modifed;
    }

    public String getNoteMonday() {
        return noteMonday;
    }

    public void setNoteMonday(String noteMonday) {
        this.noteMonday = noteMonday;
    }

    public String getNoteTuesday() {
        return noteTuesday;
    }

    public void setNoteTuesday(String noteTuesday) {
        this.noteTuesday = noteTuesday;
    }

    public String getNoteWednesday() {
        return noteWednesday;
    }

    public void setNoteWednesday(String noteWednesday) {
        this.noteWednesday = noteWednesday;
    }

    public String getNoteThursday() {
        return noteThursday;
    }

    public void setNoteThursday(String noteThursday) {
        this.noteThursday = noteThursday;
    }

    public String getNoteFriday() {
        return noteFriday;
    }

    public void setNoteFriday(String noteFriday) {
        this.noteFriday = noteFriday;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
