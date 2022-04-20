/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.tests;

import tabaani.entities.Events;
import tabaani.entities.Themes;
import tabaani.services.EventsCRUD;
import tabaani.services.ThemesCRUD;
import tabaani.utils.MyConnection;

/**
 *
 * @author DELL
 */
public class MainClass {
    public static void main(String[] args) {
        /*MyConnection mc = MyConnection.getInstance();
        MyConnection mc2 = MyConnection.getInstance();
        System.out.println(mc.hashCode()+" - "+mc2.hashCode());*/
        /*ThemesCRUD tcd = new ThemesCRUD();
        Themes th = new Themes("Theme dynamique java", "Image java 2");
        tcd.ajouterTheme2(th);*/
        //System.out.println(tcd.supprimerTheme());
        
        EventsCRUD ecd = new EventsCRUD();
        ecd.ajouterEvent();
    }
    
}
