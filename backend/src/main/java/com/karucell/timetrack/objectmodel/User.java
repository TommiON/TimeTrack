package com.karucell.timetrack.objectmodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Tämä luokka kuvaa yksittäistä käyttäjää. Pitää varmaankin tutkia/päättää, miten identiteetinhallinta
 * ylipäätään toteutetaan, tuskin yksi String ihan riittää?
 *
 * Tietomallissa User on tarkoitettu aktiiviseksi ja toimivaksi luokaksi, eli User luo aina uuden
 * Työn ja lisää sen Kellokorttiin tai suoraan Projektiin jos merkitään vasta jälkikäteen. Kuulostaako
 * järkevältä?
 *
 */
public class User {

    // käytännössä varmaan tarvitaan merkkojonon sijaan jokin vähän sofistikoitumeepi Henkilö-luokka tms,
    // joka linkittyy sovelluksen identiteetinhallintaan
    String name;

    // määrää onko käyttäjällä supervoimia perustaa/muokata projekteja ja käyttäjiä
    boolean admin;

    // Käyttäjän omat Projektit ja Työt
    List<Project> projects;
    List<Job> jobEntries;

    // TODO: tarvittavat metodit...

}
