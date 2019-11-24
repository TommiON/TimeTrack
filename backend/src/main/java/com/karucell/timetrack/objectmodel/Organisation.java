package com.karucell.timetrack.objectmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Tämä  on organisaation kattoluokka. Sisältää kaikki Projektit ja Käyttäjät.
 * Tarjoaa admin-käyttäjille metodit Projektien ja Käyttäjien luomiseen ja muokkaamiseen.
 *
 * TODO: Täysin avonainen kysymys tietomallissa: miten admin-oikeudet luodaan? Tämänhetkisessä mallissa
 * adminit ovat Käyttäjiä joilla on yksi boolean-muuttuja true, mutta kuka/mistä säätää tämän
 * oikeuden päälle alunperin? Onko lisäksi niin, että sovelluksessa on tasan yksi Organisaatio, jolloin
 * tästä kai tulee jonkinainen globaali singleton-objekti. Paljon mietittävää vielä tässä...
 */
public class Organisation {
    List<Project> projects;
    List<User> users;

}
