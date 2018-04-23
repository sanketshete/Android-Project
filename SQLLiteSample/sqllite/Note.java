package com.example.sanket.sqllite;

/**
 * Created by sanket on 10/23/2017.
 */

public class Note {
    private Long _id;
    private String subject,text;

    public Note(String subject, String text) {
        this.subject = subject;
        this.text = text;
    }
    public Note(){

    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Note{" +
                "_id=" + _id +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
