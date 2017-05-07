package com.ems.messaging;

import com.ems.userinfo.UserDAO;
import com.ems.userinfo.UserJDBC;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

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
    private String receiver_username;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer message_id;
    @NotEmpty(message = "Your message can't be empty")
    private String text;
    @NotNull
    private Date date;

    private Integer fk_account_sender;

    private Integer fk_account_receiver;

    public Message(){

    }

    public Message(String text, Integer fk_account_sender, Integer fk_account_receiver){
        this.text = text;
        this.fk_account_sender = fk_account_sender;
        this.fk_account_receiver = fk_account_receiver;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getFk_account_sender() {
        return fk_account_sender;
    }

    public void setFk_account_sender(Integer fk_account_sender) {
        this.fk_account_sender = fk_account_sender;
    }

    public Integer getFk_account_receiver() {
        return fk_account_receiver;
    }

    public String getSenderUsername(UserDAO userDAO){
        return userDAO.getUsernameById(fk_account_sender);
    }

    public void getFormattedText(UserDAO userDAO){
        formattedText = date.toString() + " | " + getSenderUsername(userDAO) + " | " + text;
    }

    public void setFk_account_receiver(Integer fk_account_receiver) {
        this.fk_account_receiver = fk_account_receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReceiver_username() {
        return receiver_username;
    }

    public void setReceiver_username(String receiver_username) {
        this.receiver_username = receiver_username;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", text='" + text + '\'' +
                ", fk_account_sender=" + fk_account_sender +
                ", fk_account_receiver=" + fk_account_receiver +
                '}';
    }
}
