package com.ems.messaging;

import com.ems.userinfo.UserService;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Aurimas on 2017-05-07.
 */
@Entity
@Table(name = "private_message")
public class Message {

    @Transient
    public String formattedText;

    @Transient
    private String receiverUsername;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Your message can't be empty")
    private String text;
    @NotNull
    private Date date;

    private Integer fkAccountSender;

    private Integer fkAccountReceiver;

    public Message(){

    }

    public Message(String text, Integer fkAccountSender, Integer fkAccountReceiver){
        this.text = text;
        this.fkAccountSender = fkAccountSender;
        this.fkAccountReceiver = fkAccountReceiver;
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

    public Integer getFkAccountSender() {
        return fkAccountSender;
    }

    public void setFkAccountSender(Integer fkAccountSender) {
        this.fkAccountSender = fkAccountSender;
    }

    public Integer getFkAccountReceiver() {
        return fkAccountReceiver;
    }

    public String getSenderUsername(UserService userService){
        return userService.getUsernameById(fkAccountSender);
    }

    public String getReceiverUsername(UserService userService) { return userService.getUsernameById(fkAccountReceiver); }

    public void getFormattedText(UserService userService){
        formattedText = date.toString() + " | " + getSenderUsername(userService) + " -> " + getReceiverUsername(userService) + " | " + text;
    }

    public void setFkAccountReceiver(Integer fkAccountReceiver) {
        this.fkAccountReceiver = fkAccountReceiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", fkAccountSender=" + fkAccountSender +
                ", fkAccountReceiver=" + fkAccountReceiver +
                '}';
    }
}
