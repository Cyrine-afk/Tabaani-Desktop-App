/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.entities;

/**
 *
 * @author DELL
 */
public class ParticipateEvent {
    private int id;
    private int event_id;
    private int user_id;

    public ParticipateEvent() {
    }

    public ParticipateEvent(int id, int event_id, int user_id) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
    }

    public ParticipateEvent(int event_id, int user_id) {
        this.event_id = event_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ParticipateEvent{" + "id=" + id + ", event_id=" + event_id + ", user_id=" + user_id + '}';
    }
    
    
}
