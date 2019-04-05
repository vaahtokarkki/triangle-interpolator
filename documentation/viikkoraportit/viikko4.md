# Viikko 4

Käytetyt tunnit: 19

# Mitä olen tehnyt tällä viikolla?
Tällä viikolla olen saanut toteutettua omat tietorakenteet, sekä muutamia Javan Math-luokan metodeja itse. Pisteiden lukemisen tiedostosta olen saanut toteutettua toimivaksi. Luettujen pisteiden esikäsittelyn sain myös ratkaistua osittain ja ne saadaan nyt siirrettyä oikeassa suhteessa kuvan pikseleiksi. Testejä olen tehnyt jo aiemmin toteutetuille luokille, joskin itse interpolointi vaatii vielä lisää testausta.  

# Miten ohjelma on edistynyt?
Omat tietorakenteet tehty ja osittain lisätty niitä ohjelman toteutukseen. Myös tiedoston luku ja csv tiedoston käsittely pisteiksi toteutettu.  	

# Mitä opin tällä viikolla
Opettelin vaihtoehtoisen interpolointimenetelmän, [inverse distance weighting](https://gisgeography.com/inverse-distance-weighting-idw-interpolation/). Vertailun vuoksi tein myös tämän, mutta yllätyksekseni menetelmä oli reilusti hitaampi, mutta antaa tuloksena tutumman ja miellyttävämmän lopputuloksen.  Inverse distance menetelmän hitaus johtuu, että jokaisen pikselin interpoloinnissa käytettyjen pisteiden etäisyyksien laskemiseen käytetään Math.pow(), joka on melko hidas operaatio.

# Mikä jäi epäselväksi tai tuottanut vaikeuksia


# Mitä teen seuraavaksi?
Aloitan tekstipohjaisen käyttöliittymän toteutuksen, sekä jatkan testauksen parissa. Tarkoituksena olisi myös etsiä oikeaa dataa, jota syöttää ohjelmalle ja tarkastella kuinka interpolointi käyttäytyy tällöin. 