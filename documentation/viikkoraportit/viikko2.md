# Viikko 2

Käytetyt tunnit: 12

## Mitä olen tehnyt tällä viikolla? 

Jatkoin teorian parissa, erityisesti kuinka laskea luetuista pisteitä kolmiot. Annetuista pisteistä kolmioiden muodostaminen vaikuttaakin olevan yllättävän haastavaa, mutta luulen järkeilleeni (mahdollisesti hitaahkon) tavan ratkaista tämän.  Tästä kirjoitin lisää [määrittelydokumenttiin](../maarittelydokumentti.md#delaunay-triangulation)
Löydetyistä kolmioista pikselien arvojen laskeminen ei osoittautunutkaan kovin vaikeaksi ja sainkin tehtyä lyhyen kokeilun niihin liittyen.

 ## Miten ohjelma on edistynyt? 

Tein lyhyen demon, jolla interpoloida arvoja pikseleille, kun annettuna on vain yksi kolmio. Tein myös tälle Javan omilla luokilla kuvatiedostoon kirjoittamisen ja muodostettu kuva näytti hyvältä, joten uskon ainakin tämä osa menetelmästä toimii. Kuva on vielä mustavalkoinen, mutta myöhemmässä vaiheessa olisi tarkoitus myös laskea interpoloiduille arvoille värilliset arvot. Tein myös perusluokat ja muutamia niihin liittyviä metodeja (etäisyyksien laskemista yms), joilla lähteä eteenpäin interpoloinnissa. 

## Mitä opin tällä viikolla / tänään? 
Tarkemmin Delaunay kolmioista ja kuinka ne muodostetaan. Asia ei olekaan niin simppeli mitä aluksi ajattelin, ei riitä etsiä vain halutusta pisteestä kolme lähintä mittausta. 

## Mikä jäi epäselväksi tai tuottanut vaikeuksia? 

Saako javan Math kirjastoa käyttää, erityisesti Math.sqrt()? Tällä hetkellä tuntuu olevan kuitenkin melko selvää mistä jatkaa ja useita aiheita mistä jatkaa. Esimerkiksi pisteiden lukemista ja luettujen pisteiden sovittamista kuvaan en ole vielä aloittanut tarkemmin miettimään, joten tässä voi nousta vielä ongelmia.

## Mitä teen seuraavaksi? 

Aloitan luultavasti toteuttaa kolmioiden laskemista sekä syötetiedoston lukemista. Syötteen lukemiseen liittyy myös kuinka antaa koordinaateille arvot kuvatiedoston x- ja y-koordinaatistossa. 
