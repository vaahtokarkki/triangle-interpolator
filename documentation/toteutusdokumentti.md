# Toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelma interpoloi kuvan käyttäjän antamista pisteistä kahdella eri menetelmällä. Pisteet luetaan csv-tiedostosta ja interpoloi niistä matriisin, jonka kirjoittaa tiedostoon. Interpoloidut arvot luokitellaan [equal interval](http://wiki.gis.com/wiki/index.php/Equal_Interval_classification) menetelmällä ja näille luokille määritellään väriarvot käyttäjän haluamalla väriteemalla. Luokittelemalla interpoloidusta kuvasta saa hieman pelkistetymmän ja erityisesti idw-interpoloinnissa miellyttävämmän näköisen.

### Luetut pisteet ja koordinaatit

Luettujen pisteiden koordinaatit täytyy olla WGS84 datumissa ja desimaalimuodossa. Tämän päätin vaihtaa määrittelydokumentaation ETRS-TM35FIN koordinaatistosta, koska löysin kuitenkin enemmän WGS84-koordinaatistossa olevaa testausmateriaalia. Tähän teen vielä mahdollisesti apuvälineen, jolla muuttaa suomen koordinaatiston koordinaatteja WGS84 koordinaateiksi. 

Luetut koordinaatit täytyy muuttaa koordinaateiksi matriisissa, jonka korkeyden ja leveyden käyttäjä on määritellyt. Tämän toteutin [Haversinen kaavalla](https://en.wikipedia.org/wiki/Haversine_formula), jolla saavtetaan noin 0.5% tarkkuus. Koska luetut pisteet ovat maantieteellisiä koordinaatteja ei niiden välisiä etäisyyksiä voi laskea, kuten tasokoordinaatistossa käyttäen Pythagoraan lausetta.

Kun koordinaatit ovat luettu, täytyy ne siirtää matriisiin oikeille paikoille, siten että kun pisteiden ympärille piirtää suorakulmion ([minimum bounding box](https://en.wikipedia.org/wiki/Minimum_bounding_rectangle)) on sen vasen yläkulma matriisin ensimmäisessä elementissä, eli kohdassa (0,0). Tämän jälkeen koordinaatteja skaalataan, siten että ne täyttävät pituus tai leveyssuunnassa koko matriisin. Tässä on tärkeä huolehtia, että pisteiden väliset suhteet pysyvät (aspect ratio) samana.

### Interpolointi

Delaunay-kolmiointi on naiivi toteutus ja se vain käy pisteitä läpi, kunnes löytyy validi Delaunay-kolmio. Toisin kuin määrittelydokumentissa, ohjelma laskee ensin pisteistä kolmiot ja tallentaa ne HashSet-toteutukseen. Valitsin hajautustaulun kolmioiden tallentamiseen, koska siinä on ArrayListiä nopeampi contains-totetus ja tätä tarvitaan, jottei listaan tule samoja kolmioita useaan kertaan. 

Delaunay-kolmion oikeellisuus tarkistetaan laskemalla kolmion keskipiste (circumcenter) ja tarkistamalla jääkö ympyrän sisään pisteitä. Kolmion keskipiste lasketaan ottamalla kaksi kolmion sivua ja laskemalla näille suorille normaalit, jotka kulkevan sivujen keskikohdan läpi. Kolmion keskipiste on kohta, jossa nämä kaksi suoraa leikkaavat ja pisteen ympärille tehtävän ympyrän säde on etäisyys kolmion kärkeen.Kolmioinnin aikana pisteet käydään melko moneen kertaan läpi, mutta en myöskään tähän hätään keksi miten asian voisi hoitaa tehokkaammin.

Kolmioinnin jälkeen jokaiselle kuvan pisteelle lasketaan arvo etsimällä kolmio, jonka sisällä piste on ja kolmion kärkien arvojen avulla pisteelle lasketaan arvo käyttäen barsysentrisiä koordinaatteja.

IDW-interpolointi huomattavasti simppelempi. Tässä menetelmässä lasketaan jokaisen kuvan pikselin kohdalla määritetyn hakuetäisyyden sisään jäävien pisteiden käänteisiä etäisyyksiä seuraavan kaavan mukaan:

![IDW formula](images/IDW_formula.png)

jossa *n* on hakualueen sisään jäävien pisteiden määrä, *w* on tunnetun pisteen arvo ja *d* etäisyys nykyisestä pikselistä tunnettuun pisteeseen. 


## Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)

Delaunay kolmoiden muodostaminen on tällä hetkellä O(n^3) aikainen, mutta tämä suoritetaan vain kerran interpoloinnin aikana. Algoritmiin on nopeampia ratkaisuja olemassa, mutta jätän niiden mahdollisen toteuttamisen myöhemmäksi jos jää aikaa, koska ne ei ole aivan triviaaleja toteuttaa.

Interpoloinnin vaatima aika on vahvasti sidoksissa kuvan resoluutioon, koska jokaiselle pikselille täytyy laskea arvo. Tällöin luettujen pisteiden määrällä ei ole suurta vaikutusta, ainakin jos pisteitä on alle 100. Tarkempi suorituskykyvertailu tehdään myöhemmin.

## Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)

## Työn mahdolliset puutteet ja parannusehdotukset

## Lähteet