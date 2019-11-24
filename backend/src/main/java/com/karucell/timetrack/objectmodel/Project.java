package com.karucell.timetrack.objectmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Tämä luokka kuvaa yksittäisen projektin. Projekteja voi olla Organisaatiossa useita.
 * Luokan keskeinen toiminnallisuus on koota samaan projektiin liittyvät Job-oliot yhteen.
 */
public class Project {

    // projektin name
    String name;

    // yksi tai useampi käyttäjä, jolla hallintaoikeudet projektiin
    List<User> owners;

    // käyttäjät, jolla oikeus lisätä kirjauksia projektiin ja muokata omia kirjauksiaan
    List<User> team;

    // luokan ydinosa, eli jobEntries
    List<Job> jobEntries;

    // onko projekti active, ts. voiko lisätä uusia kirjauksia
    boolean active;

    // TODO: lisäksi tarvittavissa määrin statistiikkaa: työtunnit yhteensä, työtunnit tekijöittäin,
    // projektin aloitus- ja lopetusajat jne.

    // tärkeimpien metodien luurangot:

    // uuden tuntikirjauksen lisääminen Projektiin
    public void addJob(Job newJob) {
    }

    // TODO: avoin kysymys: onko Työn tekijällä mahdollisuus muokata Töitä vielä jälkikäteen? Tämä vaikuttaa
    // tietysti tarvittaviin metodeihin.

    // TODO: lisäksi luokkakonstruktorit, tarvittavat getterit ja setterit jne.
}
