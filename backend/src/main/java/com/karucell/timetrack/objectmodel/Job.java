package com.karucell.timetrack.objectmodel;

import java.time.*;
import java.util.Date;

/**
 * Tämä luokka kuvaa yksittäistä tuntikirjausta. Yksittäisen Työn omistaa User ja se kuuluu Projektiin.
 * Tässä versiossa aikamalli on yksinkertainen: kirjataan aloitus- ja lopetusaika, lasketaan siitä duration.
 *
 */
public class Job {

    User doer;
    String description;
    Date start;
    Date end;

    // tärkeimpien metodien luurangot

    // uuden objektin luominen kellonkorttityylillä: ajastin alkaa laskea perustamishetkestä...
    public Job(String description){
    }

    // uuden objektin luominen jälkikäteen: annetaan start- ja loppuaika
    public Job(Date start, Date end, String description){
    }
}
