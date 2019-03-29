# Viikko 3

Käytetyt tunnit: 22

## Mitä olen tehnyt tällä viikolla? 

Tällä viikolla olen edennyt mielestäni hyvin aiheen ydinongelman kanssa. Viime viikolla sain tehtyä interpoloinnin yhden kolmion sisällä ja tällä viikolla jatkoin aiheen parissa laajentaen tämän useamman kolmion muodostamaan verkkoon.  

Tein myös testejä sen mitä kerkesin ja tällä hetkellä testit kattavat täysin Line-luokan ja muidenkin geometry paketin testejä on aloitettu.  Tällä viikolla en kerennyt edes aloittaa pisteiden lukemista tiedostosta vaikka oli tarkoitus ja se onkin seuraava asia johon alan tutustua. 

Interpolointia kehittäessä kävi myös ilmi, että näillä näkymin joudun toteuttamaan myös ArrayList- ja HashSet-luokat.

## Miten ohjelma on edistynyt? 

Työskentelin suurimman osan ajasta erilaisten suoriin ja kolmioihin liittyvien funktioiden ja yhtälöiden parissa. Näiden avulla sain tehtyä kasaan O(n^3) aikaisen algoritmin, jolla luoda annetuista pisteistä verkko valideja Delaunay kolmioita. Kolmioiden oikeellisuutta täytyy vielä tutkia tarkemmin, mutta nopealla vilkaisulla näyttää lupaavalta. Projektin juurikansiossa on esimerkkikuvat algoritmilla tuotetusta lopputuloksesta.

Nopean interpolointitestin lopputulokseen en ole vielä aivan tyytyväinen ja voi olla, että toteutan myös jotain vaihtoehtoisia (hitaampia) tapoja tehdä interpolointi ja vertailla tuloksia. 

## Mitä opin tällä viikolla / tänään? 

Opin paljon geometriasta ja esimerkiksi suoran yhtälöistä ja muista geometrian laskutoimituksista. Myös hash coden laskemisesta tuli hieman luettua, kuinka saisin luokilleni laskettua sen.

## Mikä jäi epäselväksi tai tuottanut vaikeuksia? 

Tällä viikolla ei ollut suurempia vaikeuksia. Hieman epäselvää tällä hetkellä on millä tasolla tiedoston luku/kirjoitus täytyy toteuttaa itse, mutta siitä varmasti vielä lisää ens viikolla.

## Mitä teen seuraavaksi? 

Aloitan toteuttamaan pisteiden lukua tiedostosta ja csv-datan käsittelyä. Tarkoituksena olisi myös aloittaa ArrayList- ja HashSet-luokkien toteuttaminen. Pyrin luultavasti keskittymään tulevalla viikolla enemmän sovelluksen muihin toiminnallisuuksiin, kuin itse interpolointiin. Luultavasti alkaisi olla myös hyvä aika miettiä minkälaisen käyttöliittymän sovellukseen toteuttaa.
