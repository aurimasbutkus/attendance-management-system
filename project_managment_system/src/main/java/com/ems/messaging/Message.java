package com.ems.messaging;

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

    public void setFk_account_receiver(Integer fk_account_receiver) {
        this.fk_account_receiver = fk_account_receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
