# Viikko 1

Käytetyt tunnit: 12

## Mitä olen tehnyt tällä viikolla?

Ensimmäisellä viikolla sain mielestäni hyvin mietittyä, miten lähden ongelmaa ratkomaan ja näiden suunnitelmien pohjalta on hyvä lähteä rakentamaan toteutusta. Havainnollistin myös määrittelydokumenttiin, kuinka algoritmin olisi tarkoitus ratkaista interpolointiongelma. Vielä en varsinaista koodia ole riviäkään kirjoittanut, vaan vain laittanut maven yms. ympäristöt alulle. 


## Mitä opin tällä viikolla?

Opin tällä viikolla paljon eri interpolointimenetelmistä sekä barysentrisistä koordinaateista. Matemaattista teoriaa aiheeni parissa olen myös pyrkinyt omaksumaan, mutta varmasti joudun vielä tulevina viikkoina palaamaan kaavoihin, joilla varsinaista interpolointia tehdään. Olen kuitenkin mielestäni omaksunut melko hyvin perusidean, miten ongelmaa voisi lähteä ratkomaan (siitä lisää määrittelydokumentissa). 

## Mikä jäi epäselväksi tai tuottanut vaikeuksia?

Työn parissa hieman epäselvää on, onko lähestymistapani interpolointiin kuitenkaan oikea ja saadaanko sillä oikeita tuloksia. Periaatteessa sen pitäisi toimia ainakin pienillä syötteillä (vähän mittauksia joista interpoloida), mutta suuremmilla syötteillä algoritmissa voisi mahdollisesti tapahtua jotain kummallista. Se jääköön nähtäväksi kun toteutus edistyy. Myös algoritmin tehokkuus on tällä hetkellä melko avoin ja vaikea arvioida, enne kuin pääsee tarkemmin toteutuksen pariin minkälaisia vaiheita ratkaisu lopulta vaatii. 

Yhtenä kysymysmerkkinä tällä hetkellä on myös, saako javan File- tai Scanner-luokkia käyttää tiedoston lukemiseen ja kirjoittamiseen? Millä tasolla I/O-operaatiot tiedostoon täytyy toteuttaa itse? Myös lopputuloksena tuotettavan kuvan kirjoittamiseen käytettävät kirjastot ovat vielä täysin kysymysmerkkinä. Palaan näihin vielä myöhemmässä vaiheessa, kun  algoritmi on siinä vaiheessa, että sillä saa tuotettua jotain mistä generoida kuvatiedosto.

## Mitä teen seuraavaksi?

Seuraavaksi lähden toteuttamaan simppeliä proof of concept vain muutamalla interpoloitavalla pisteellä ja katson, minkälaisia tuloksia saan lähestymistavallani. Tässä vaiheessa täytyisi myös päättää minkälaista nearest neighbour algoritmia käytän, sekä toteuttaa tämä valittu algoritmi. Myös jo ensimmäisiä yksikkötestejä olisi tarkoitus tehdä, hetki kun toteutuksessa on otettu ensimmäiset askeleet oikeaan suuntaan.