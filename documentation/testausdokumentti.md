# Testausdokumentti

## Mitä on testattu, miten tämä tehtiin

Pyrin mahdollisimman kattavaan ykiskkötestaukseen Junit-testeillä.

TODO: Testasin myös ohjelman tuottamia tuloksia ArcGIS-ohjelmalla tehtyihin interpolointeihin samasta aineistosta ja vertailin näiden eroja. 

## Minkälaisilla syötteillä testaus tehtiin (vertailupainotteisissa töissä tärkeää)

## Miten testit voidaan toistaa

## Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa

Alla ohjelman tuloksia vertailtu ArcGIS-ohjelmistolla tehtyihin interpolointeihin. Aineistona on käytetty koko suomen kattavaa lämpötilamittauksia (ilmatieteenlaitos) ja ilmanlaatumittauksia Helsingissä (lähde).

### Pisteet suuressa mittakaavassa

![Pisteet suomi](images/comparison_points2.png)

Yllä olevasta kuvassa on vasemmalla ArcGIS-ohjelmalla lämpötila-aineiston aineiston pisteet kartalle ja oikealla algoritmini tuottama kuva, jossa pelkät pisteet. Kuvasta voi huomata, että pisteiden sijainnit vääristyvät hieman, kun ne luetaan tiedostosta ja asetetaan kuvaan. Tyydyn kuitenkin tähän tarkkuuteen, koska vääristyminen johtuu koordinaattien projektioista eikä sen korjaaminen vaikuta merkittävästi ohjelman toimintaa, lähinnä sen tarkkuuteen. 

### Pisteet pienessä mittakaavassa

![Pisteet Helsinki](images/comparison_points.png)

Yllä olevassa kuvassa on päällekkäin ohjelmani tekemä kuva, jossa pisteet sekä taustakartta jossa on aineiston pisteet. Mustat pisteet ovat ohjelmani tekemiä pisteitä ja neliönmuotoiset pisteet ovat pisteiden todelliset sijainnit kartalla. Osan pisteiden sijainnit ovat hieman sivussa projektiovirheiden vuoksi, mutta tässä mittakaavassa vääristymä on huomattavasti pienempi kuin aiemmassa vertailussa.

### IDW-vertaiulu

![IDW Suomi](images/comparison_idw.png)

Yllä olevassa kuvassa vasemmalla ohjelmani tekemä IDW-interpolointi ja oikealla ArcGIS-ohjelmalla tehty vastaava interpolointi Helsingin ilmanlaatumittauksista. Oikean puoleisessa kuvassa hieman eri parametrit ja hakukriteerit, mutta lopputulokset ovat kuitenkin melko samat.

## Testauksessa käytetty aineisto 

### Esimerkki koko Suomen lämpötilat kattavasta aineistosta

**n**|**e**|**temp**|**name**
-----|-----|-----|-----
60.30373|25.54916|4.6|Porvoo Kilpilahti satama
60.12467|19.90362|3.2|Jomala Maarianhamina lentoasema
59.77909|21.37479|3.8|Parainen Utö
59.95911|19.95374|3.6|Lemland Nyhamn
60.17824|19.98686|2.9|Jomala Jomalaby
60.30098|19.13142|1.7|Hammarland Märket
59.50454|20.34748|3.1|Kökar Bogskär

### Esimerkki Helsingin ilmanlaatuaineistosta

 | | | |**n**|**e**| |**value**
-----|-----|-----|-----|-----|-----|-----
Hämeentie 7| Helsinki| Finland|60.181574|24.955299|success|33
Mannerheimintie 57-59| Helsinki| Finland|60.1903|24.915794|success|39
Kehä I| Helsinki| Finland|60.243998|24.945843|success|33
Hämeentie 84-90| Helsinki| Finland|60.195236|24.961124|success|20
