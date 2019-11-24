package com.karucell.timetrack.objectmodel;

/**
 * Tämä luokka huolehtii kellokorttimoodista, eli sisältää korkeintaan yhden Job-objektin, jota ei ole vielä
 * suljettu eli ei vielä kuulu mihinkään Projektiin (tallennetaan Projektiin kun Kellokortti suljetaan).
 * Tarvittaessa luokkaan voidaan rakentaa jotain automaatiota esim. huomauttamaan, jos Kellokortti on ollut
 * yhtäjaksoisesti päällä epilyttävän pitkään jne.
 */
public class Timecard {
    // jokaiseen kellokorttiin liittyy yksi projekti, joka on tämä:
    Project attachedProject;
    // TODO: pitää miettiä miten kellokortti tallentaa ajan,
    //  kokonaisaika voinee muodostua useast erillisestä aikajaksosta, ks. metodit alla

    // laittaa kellokortin päälle, tallentaa ajan sillä hetkellä
    public void startCounting() {}

    // laittaa kellokortin tauolle, tallentaa ajan sillä hetkellä
    public void stopCounting() {}

    // kun homma tulee päätökseeen, tallennetaan kokonaisaika ja vasta tässä vaiheessa luodaan tästä Job
    // olisikohan tällainen mallinnus järkevä?
    public void finalise() {}
}
