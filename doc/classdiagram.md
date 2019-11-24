### Class diagram file
This file is used to render class diagram image using plantuml


### Setup (for intelliJ Idea)
- install PlantUML plugin
- restart idea


### How to update diagram
- Update definitions and relations in this file
- Export as file into same directory which this file is located
- use name: classdiagram.png 
=> README.md in root of project should update automatically


### Actual diagram
- Help with symbols: http://plantuml.com/class-diagram

@startuml

class Organisaatio {
    List<Projekti> projektit
    List<Kayttaja> kayttajat
}
class Projekti {
    String name
    List<Kayttaja> omistajat
    List<Kayttaja> tiimi
    List<Tyo> tuntikirjaukset
    boolean active
}
class Kayttaja {
    String name
    boolean admin
    List<Projekti> projektit
    List<Tyo> kirjaukset

}
class Kellokortti {
    Projekti kaynnissaOlevaProjekti
}
class Tyo {
    Kayttaja tekija
    String kuvaus
    Date alku
    Date loppu
    Period kesto
}

Organisaatio --> Projekti
Organisaatio --> Kayttaja
Projekti --> Tyo
Projekti --> Kayttaja
Projekti --> Kayttaja
Kayttaja --> Projekti
Kayttaja --> Tyo
Kellokortti --> Projekti

@enduml