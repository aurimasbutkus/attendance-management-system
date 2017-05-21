package com.ems.messaging;

import com.ems.teaminfo.TeamService;
import com.ems.userinfo.UserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Aurimas on 2017-05-17.
 */
@Entity
@Table(name = "team_message")
public class TeamMessage {

    @Transient
    public String formattedText;

    @Transient
    private String senderUsername;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Your message can't be empty")
    private String text;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;

    private Integer fkAccountSender;

    private Integer fkTeamReceiver;

    public TeamMessage() {

    }

    public TeamMessage(String text, Integer fkTeamReceiver) {
        this.text = text;
        this.fkTeamReceiver = fkTeamReceiver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFkTeamReceiver() {
        return fkTeamReceiver;
    }

    public void setFkTeamReceiver(Integer fkTeamReceiver) {
        this.fkTeamReceiver = fkTeamReceiver;
    }

    public Integer getFkAccountSender() {
        return fkAccountSender;
    }

    public void setFkAccountSender(Integer fkAccountSender) {
        this.fkAccountSender = fkAccountSender;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }
    public void getFormattedText(UserService userService, TeamService teamService){
        formattedText = date.toString() + " | " + userService.getUsernameById(fkAccountSender) + " -> " + teamService.getTeamById(fkTeamReceiver).getName() + " | " + text;
    }
}
