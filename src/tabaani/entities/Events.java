/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.entities;

import java.time.LocalDate;

/**
 *
 * @author DELL
 */
public class Events {
    private int id;
    private int nbrmaxpart;
    private String imageevent;
    private String eventname;
    private String description;
    private LocalDate eventdate;
    private String eventaddress;
    private Themes eventtheme_id;
    private User org_id;
    private int nbr_going;

    public Events() {
    }
    

    public Events(int id, int nbrmaxpart, String imageevent, String eventname, String description, LocalDate eventdate, String eventaddress, Themes eventtheme_id, User org_id, int nbr_going) {
        this.id = id;
        this.nbrmaxpart = nbrmaxpart;
        this.imageevent = imageevent;
        this.eventname = eventname;
        this.description = description;
        this.eventdate = eventdate;
        this.eventaddress = eventaddress;
        this.eventtheme_id = eventtheme_id;
        this.org_id = org_id;
        this.nbr_going = nbr_going;
    }

    public Events(int nbrmaxpart, String imageevent, String eventname, String description, LocalDate eventdate, String eventaddress, Themes eventtheme_id, User org_id, int nbr_going) {
        this.nbrmaxpart = nbrmaxpart;
        this.imageevent = imageevent;
        this.eventname = eventname;
        this.description = description;
        this.eventdate = eventdate;
        this.eventaddress = eventaddress;
        this.eventtheme_id = eventtheme_id;
        this.org_id = org_id;
        this.nbr_going = nbr_going;
    }

    public Events(int nbrmaxpart, String eventname, String description, LocalDate eventdate, String eventaddress, Themes eventtheme_id, User org_id, int nbr_going) {
        this.nbrmaxpart = nbrmaxpart;
        this.eventname = eventname;
        this.description = description;
        this.eventdate = eventdate;
        this.eventaddress = eventaddress;
        this.eventtheme_id = eventtheme_id;
        this.org_id = org_id;
        this.nbr_going = nbr_going;
    }

    public Events(int id, int nbrmaxpart, String imageevent, String eventname, String description, String eventaddress, Themes eventtheme_id, User org_id, int nbr_going) {
        this.id = id;
        this.nbrmaxpart = nbrmaxpart;
        this.imageevent = imageevent;
        this.eventname = eventname;
        this.description = description;
        this.eventaddress = eventaddress;
        this.eventtheme_id = eventtheme_id;
        this.org_id = org_id;
        this.nbr_going = nbr_going;
    }

    public Events(int aInt, int aInt0, String string, String string0, String string1, String string2, int aInt1, int aInt2, int aInt3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrmaxpart() {
        return nbrmaxpart;
    }

    public void setNbrmaxpart(int nbrmaxpart) {
        this.nbrmaxpart = nbrmaxpart;
    }

    public String getImageevent() {
        return imageevent;
    }

    public void setImageevent(String imageevent) {
        this.imageevent = imageevent;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventdate() {
        return eventdate;
    }

    public void setEventdate(LocalDate eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventaddress() {
        return eventaddress;
    }

    public void setEventaddress(String eventaddress) {
        this.eventaddress = eventaddress;
    }

    public Themes getEventtheme_id() {
        return eventtheme_id;
    }

    public void setEventtheme_id(Themes eventtheme_id) {
        this.eventtheme_id = eventtheme_id;
    }

    public User getOrg_id() {
        return org_id;
    }

    public void setOrg_id(User org_id) {
        this.org_id = org_id;
    }

    public int getNbr_going() {
        return nbr_going;
    }

    public void setNbr_going(int nbr_going) {
        this.nbr_going = nbr_going;
    }

    @Override
    public String toString() {
        return "Events{" + "id=" + id + ", nbrmaxpart=" + nbrmaxpart + ", imageevent=" + imageevent + ", eventname=" + eventname + ", description=" + description + ", eventdate=" + eventdate + ", eventaddress=" + eventaddress + ", eventtheme_id=" + eventtheme_id + ", org_id=" + org_id + ", nbr_going=" + nbr_going + '}';
    }
    
      
    
}
