# Viikko 5

Käytetyt tunnit: 22

# Mitä olen tehnyt tällä viikolla?

Olen tehnyt ohjelman käyttöliittymän perusidealtaan valmiiksi, pientä parantelua vaatii vielä, mutta sain sen kuitenkin käytettävään kuntoon. Testejä on myös paranneltu.

# Miten ohjelma on edistynyt?

Olen tehnyt hieman parantelua ja erityisesti lopputuloksen oikeellisuuden tarkastelua. Pieniä bugeja löytyi, mutta ne liittyivät pisteiden ja koordinaattien käsittelyyn ja on ratkaistavissa melko simppelisti. Olen myös löytänyt hieman oikeaa testidataa (ilmanlaatu) ja tehnyt sillä testiajoja sekä vertaillut sitä karttaan miten pisteet asettuvat. Tästä huomasinkin bugin, että pisteet pysyvät oikeilla paikoilla suhteessa toisiinsa, mutta kun ne skaalataan sopimaan lopulliseen kuvaan, niiden mittasuhteet vääristyvät (aspect ratio). 

Muutamiin ohjelmaluokkiin täyty myös laittaa importit käyttöliittymässä käytettävän progresbar luokan vuoksi, mutta nämä on merkattu ja tätä käytetään seuraamaan for-looppien edistymistä.

# Mitä opin tällä viikolla

Javan BufferedImage ja Graphics2D luokista.

# Mikä jäi epäselväksi tai tuottanut vaikeuksia

Tiedostonkirjoitusluokan testaus on vielä hieman epäselvää. Riittääkö testaukseksi, että tarkistetaan vain tulee kansioon oikealla nimellä tiedosto ja mahdollisesti leveys/korkeus täsmää? Varsinaisen kuvan sisällön testaaminen ei liene aivan tarkoituksenmukaista? 

# Mitä teen seuraavaksi?

Kaivelen lisää oikeata testidataa ja pyrin saamaan varmistuksen lopputuloksien oikeellisuudesta. Tällä viikolla heräsi myös kiinnostus, jos lopulliseen kuvaan saisi myös liitettyä taustakartan esimerkiksi OpenStreetMap-apista. Selvitän myös vertailun mahdollisuutta johonkin nopeaan Delaunay algoritmiin. 