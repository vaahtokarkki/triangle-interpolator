# Määrittelydokumentti

## Mitä algoritmeja ja tietorakenteita toteutat työssäsi 

Tarkoituksenani on toteuttaa lineaarinen interpolointialgoritmi, erityisesti spatiaalisen analyysin tarpeisiin. Haasteen tässä luo se, että interpoloinnissa kyettävät tunnetut arvot eivät ole siististi ruudukossa, kuten esimerkiksi kuvankäsittelyn tapauksissa usein on, vaan pisteet voivat sijaita mielivaltaisesti suhteessa toisiinsa. Lopputuloksena olisi tarkoitus syntyä kuva, jossa annettujen pisteiden rajaama alue on väritetty interpoloiduilla arvoilla.

Lähden ratkomaan ongelmaa ensin luomalla pisteistä [Delaunay triangulation](https://en.wikipedia.org/wiki/Delaunay_triangulation) menetelmää noudattaen verkon kolmioita. Jokaisen kolmion sisään jääville pikseleille lasken arvon [Barysentrisiä koordinaatteja](https://en.wikipedia.org/wiki/Barycentric_coordinate_system) hyödyntäen. Suunnittelemani toteutus vaatii myös jonkin nearest neighbor algoritmin toteutuksen, jotta jokaiselle pikselille voidaan löytää kolme lähintä tiedettyä mittausta.

### Delaunay triangulation

En erikseen lähde tekemään pisteistä kolmioverkkoa, vaan kun kuvaa muodostetaan jokaisen pikselin kohdalla katsotaan löytyykö listasta kolmiota, jonka sisään kyseinen piste jää. Jos tällöistä ei löydy etsitään pikselistä kolme lähintä tunnettua pistettä ja tarkistetaanko muodostavatko pisteet validin Delaunay kolmion. Jos pisteet muodostavat validin kolmio, lisätään se listaan ja lasketana pikselille arvo. Jos kolmio ei ollut validi etsitään lisää lähimpiä tunnettuja pisteitä ja jatketaan kolmioiden muodostamista, kunnes löydetään validi kolmio.

Jos pikseleiden arvo lasketaan vain kolmen lähimmän pisteen perusteella, vaihtuisi näin muodostetut kolmiot riippuen mitä pikseliä ollaan laskemassa. Jos pidetään huoli, että muodostettu kolmio on validi Delaunay kolmio, pysyy lasketut arvot tasaisena, eikä niihin tule yllättäviä hyppäyksiä. En tiedä kuinka suuri merkitys tällä on lopputulokseen ja saatakin luopua tästä, jos toteutus on aivan liian vaativa tai hidas. 

### Validi Delaunay kolmio

Alla kuva, jossa oikealla validi Delaunay kolmio ja vasemmalla virheellinen. Jos kolmion keskipisteesseen (triangle circumcenter) tehdyn ympyrän sisälle jää tunnettuja pisteitä, ei kolmio ole validi Delaunay kolmio. Tätä varten täytyy myös miettiä, kuinka kolmion keskipiste lasketaan ja jääkö sen ympärille muodostettuun ympyrään pisteitä.

Käytettävät tietorakenteet päivittyvät vielä projektin edetessä, mutta pyrin käyttämään taulukoita mahdollisimman pitkälle, jo toteutuksen alkuvaiheesta alkaen. 

![Delaunay triangle](images/triangle.png)

[Kuvan lähde](https://www.duo.uio.no/bitstream/handle/10852/43535/delaunay_alg_performance.pdf?sequence=1)

Menetelmän oikeellisuudesta minulla ei ole mitään varmuutta, mutta teoriassa uskoisin pääsevän melko lähelle todellisia interpolointituloksia. Tarkoituksenani olisi myös vertailla valmista lopputulosta esimerkiksi ArcGIS:llä tehtyihin interpolointeihin samoista aineistoista. GIS-järjestelmissä on myös käytössä melko paljon kehittyneemmät interpolointimenetelmät (kuten [Kriging](https://gisgeography.com/kriging-interpolation-prediction/)), joten voi olla myös mielenkiintoista vertailla näiden eroja. 

Alla havannollistettu algoritmin toimintaa, muodostettujen kolmioiden sisään jäävät pikselit täytetään interpoloiduilla arvoilla, kun kolmioiden kulmien arvot tiedetään:

![Illustration](images/algorithm_illustration.png)

## Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet

Tarkoituksena on interpoloida annetuista, satunnaisesti sijoitetuista mittauksista (scattered data) niiden rajaamalle alueelle arvoja. Paikkatieto kiinnostaa ja tämä olisi oiva mahjdollisuus yhistää paikkatietoja ja ohjelmointia. Lineaarinen interpolointi ja valitsemani menetelmät vaikuttivat myös matemaattisesti sopivan haastavilta, mutta ei kuitenkaan liian vaikeilta.

## Mitä syötteitä ohjelma saa ja miten näitä käytetään

Aineisto annetaan csv-tiedostossa, jossa mittaukset ja niiden koordinaatit.  Koordinaatit ainakin aluksi vain ETRS-TM35FIN muodossa, mutta mahdollisesti myöhemmässä vaiheessa tuki myös wgs84 desimaalimuodossa oleville koordinaateille. Periaatteessa koordinaatit voisi antaa mielivaltaisessa muodossa, mutta käytännön kannalta totetutan tuen, jollekin käytössä olevalle standardille. Lisäksi ohjelmalle annetaan parametrina resoluutio, jolla määritetään luotavan kuvatiedoston koko.

Alla muutama rivi esimerkkisyötteestä. Ohjelmalle täytyy antaa myös parametrina, mitä saraketta csv-tiedostossa käytetään x- ja y-koordinaatteihin, sekä minkä sarakkeen arvoa interpoloidaan. 

| xkoord | ykoord  | vaesto |
|--------|---------|--------|
| 395000 | 6689000 | 1492   |
| 394000 | 6689000 | 1369   |
| 393000 | 6689000 | 3136   |
| 392000 | 6689000 | 2655   |

Yllä oleva syöte on tehty tilastokeskuksen avoimesta väestöruutuaineistosta ja kuvaa kuinka monta asukasta asuu 1km x 1km ruudun alueella, kun ruudun keskipiste on koordinaattien osoittamassa kohdassa. Muita mahdollisia interpoloitavia kohteita voisi olla esimerkiksi säähavainnot (lämpötila, sademäärä, ilmanlaatu jne) tai korkeustiedot korkeusmallin luomiseksi.

Lopputuloksena olisi siis kuva, jossa on annettujen mittausten rajaamalle alueelle jokaiselle pikselille interpoloitu arvo. Lopputulos voi olla esimerkiksi mustavalkoinen tai arvoille voidaan myös antaa värit. Taustakartta ei luultavasti kuvaan saa lisättyä, koska ei ole välttämättä aivan triviaalia kohdistaa mittauspisteitä ja taustakarttaa oikein, jotta annetut mittaukset osuvat kartassa oikealle kohdalle. 

## Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)

Aikavaatimuksena olisi tavoitteena päästä O(n)-aikaan, joskin luultavasti algoritmin suoritus vaatii useamman O(n) aikasen silmukan. Vaihtoehtoisesti myös O(n^2) aika- ja tilavaatimuksella olevaan algoritmiin olisin tyytyväinen. Vaatimuksia on vielä tässä vaiheessa vaikea arvioida.   

## Lähteet

* https://codeplea.com/triangular-interpolation 
* https://www.scratchapixel.com/lessons/3d-basic-rendering/ray-tracing-rendering-a-triangle/barycentric-coordinates
* https://pdfs.semanticscholar.org/d7be/b4eabd3ea7108ff41135d87984a34c29ce7c.pdf
* https://team.inria.fr/titane/files/2017/03/barycentric.pdf
* http://www.cs.uu.nl/geobook/interpolation.pdf
* https://stackoverflow.com/questions/2902213/interpolation-of-scattered-data-what-could-i-do
* https://www.duo.uio.no/bitstream/handle/10852/43535/delaunay_alg_performance.pdf?sequence=1