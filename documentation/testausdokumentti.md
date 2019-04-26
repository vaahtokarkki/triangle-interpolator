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

