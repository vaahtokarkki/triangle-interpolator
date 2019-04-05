# Toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelma lukee csv-tiedostosta pisteet ja interpoloi niistä matriisin, jonka kirjoittaa tiedostoon. Lopputuloksesta voi tehdä mustavalkoisen, tai värillisien (toteuttamatta) ja interpoloidut arvot voi luokitella tai tallentaa tarkat arvot kuvaan. Luokittelemalla interpoloidusta kuvasta saa hieman pelkistetymmän ja erityisesti idw-interpoloinnissa miellyttävämmän näköisen.

## Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)

Delaunay kolmoiden muodostaminen on tällä hetkellä O(n^3) aikainen, mutta tämä suoritetaan vain kerran interpoloinnin aikana. Algoritmiin on nopeampia ratkaisuja olemassa, mutta jätän niiden mahdollisen toteuttamisen myöhemmäksi jos jää aikaa, koska ne ei ole aivan triviaaleja toteuttaa.

Interpoloinnin vaatima aika on vahvasti sidoksissa kuvan resoluutioon, koska jokaiselle pikselille täytyy laskea arvo. Tällöin luettujen pisteiden määrällä ei ole suurta vaikutusta, ainakin jos pisteitä on alle 100. Tarkempi suorituskykyvertailu tehdään myöhemmin.

## Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)

## Työn mahdolliset puutteet ja parannusehdotukset

## Lähteet