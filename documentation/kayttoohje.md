# Käyttöohje

Lataa projekti omalle koneellesi:

```bash
git clone https://github.com/vaahtokarkki/triangle-interpolator.git
cd triangle-interpolator
```

Tämän jälkeen ohjelman saa ajettua komennolla:

```bash
cd triangle-interpolator
java -jar interpolate.jar 
```

Ohjelmalle saa annettua parametrinä kansion, josta se etsii luettavia tiedostoja, esim `java -jar interpolate.jar my_folder`.

Ohjelman saa käännettyä lähdekoodista mavenilla komennolla:

```bash
mvn package
```

Tämän jälkeen ajettavan jar-tiedoston pitäisi olla kansiossa `triangle-interpolator/triangle-interpolator/target/triangle-interpolator-1.0-SNAPSHOT.jar`

## Ohjelman käyttö

* Ohjelma etsii oletusarvoisesti kansiosta `triangle-interpolator/data` csv-tiedostoja, joista käyttäjää pyydetään valitsemaan haluttu tiedosto pisteiden luku varten.
* Valitse käytettävä csv-erotin, oletusarvoisesti ;
* Valitse sarake, josta luetaan pisteen x-koordinaatti. Tämä on yleensä e-koordinaatti, esim n 60.1223 __e 25.0012__
* Valitse sarake, josta luetaan pisteen y-koordinaatti. Tämä on yleensä n-koordinaatti
* Valitse sarake, josta luetaan pisteen arvo. Tämä on interpoloinnissa käytettävä arvo.
* Valitse tehtävän kuvan koko pikseleissä
* Valitse interpoloinnissa käytettävien lukkien määrä. Tämä on kuinka monta eri arvoa lopputuloksessa on. Usein 10 on sopiva määrä, suuret arvot tekevät lopputuloksesta epäselvän eikä suurta erottelukykyä usein tarvitse.
* Valitse IDW-interpoloinnin parametrit. Oletuksena hakuetäisyys on 500 pikseliä ja p-arvo 2. Jos pisteitä on vähän, on suositeltavaa käyttää suurempaa hakuetäisyyttä. Pienemmät p-arvot tekevät huipuista jyrkempiä tunnettujen pisteiden läheisyydessä.
* Valitse käytettävä väripaletti. Vaihtoehtoina Seqential tai Diverging. Sequential sopii kun arvot ovat järjestettyjä arvoja (esim 0-100). Diverging sopii kun arvot jakautuu kahteen osaan, esim lämpötila -20C - +20C.
* Valitse piirretäänkö lopputulokseen myös luetut pisteet ja niiden arvot
* Anna kirjoitettavan kuvatiedoston nimi. Oletuksena luetun tiedoston nimi.

## Ohjelmalle annettavat syötteet

Ohjelmalle annetaan syötteenä csv-tiedosto, jossa on luettavat pisteet ja niille arvot. Pisteiden koordinaatit täytyvät olla WGS84-koordinaatteja (käytössä esim GPS) [desimaalimuodossa](https://en.wikipedia.org/wiki/Decimal_degrees). Arvot voivat olla kokonaislukuja tai liukulukuja. Jos tiedostossa on muita sarakkeita, jätetään ne huomioimatta. Alla on esimerkki ohjelmalle syötettävästä datasta.

**n**|**e**|**temperature**|**name**
-----|-----|-----|-----
60.20867|25.1959|4|Helsinki Vuosaari satama
60.3267|24.95675|4.4|Vantaa Helsinki-Vantaan lentoasema
60.17523|24.94459|4.9|Helsinki Kaisaniemi
60.10512|24.97539|3.9|Helsinki Harmaja
60.20307|24.96131|5|Helsinki Kumpula
60.25299|25.04549|3.9|Helsinki Malmi lentokenttä

Esimerkkidataa on kansiossa `triangle-interpolator/data`:

* `temperatures.csv` koko suomen lämpötilamittaukset 01.05.2019 TODO, Ilmatieteenlaitos
* `airquality_hki_2017.csv` Helsingin ilmanlaadun mittaukset vuonna 2017, HSY