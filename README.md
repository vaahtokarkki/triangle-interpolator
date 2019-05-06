# Linear interpolation with triangulation 

Tiralabra 2019 IV 

Ohjelmassa on toteutettu kaksi erilaista interpolointimenetelmää. Näistä varsinainen projektin aihe on [Delaunay kolmiointiin](https://en.wikipedia.org/wiki/Delaunay_triangulation) ja arvojen laskeminen näiden avulla käyttäen [barysentrisiä koordinaatteja](https://en.wikipedia.org/wiki/Barycentric_coordinate_system). Ohjelmassa on myös toteutettu [käänteisen etäisyyden menetelmään](https://en.wikipedia.org/wiki/Inverse_distance_weighting) perustuva interpolointi, joka on melko simppeli toteuttaa. Menetelmä on hyvin yleinen ja tämän lopputulos näyttää useimmille tutulta, esimerkiksi sääkartoista. 

 Netbeansissa suoraan ajettuna käyttöliittymässä interpoloinnin edistymistä näyttävä palkki ei näy oikein ja sen saa toimimaan ajamalla ohjelman suoraan konsolista kansiosta `triangle-interpolator`komennolla:

 ```bash
mvn exec:java -Dexec.mainClass="main.Main"
 ```

 Jacoco testikattavuuden generointi:
 ```bash
mvn jacoco:report
 ```

 Checkstyle-raportin generointi:
 ```bash
mvn jxr:jxr checkstyle:checkstyle
 ```

 Jar-tiedoston tekeminen `target/` kansioon:
 ```bash
mvn package
 ```

## TODO

* Colors JUnit test
* Writer getColorForColorScheme() JUnit
* ~~Jar commandline arguments (data folder)~~
* More comparisions (wappu)
* Javadoc generation
* Dokumentaatioon miksi ei sin, cos, asin
* Dokumentaatioon lähteitä

## Dokumentaatio

* [Käyttöohje](documentation/kayttoohje.md)
* [Määrittelydokumentti](documentation/maarittelydokumentti.md)
* [Toteutusdokumentti](documentation/toteutusdokumentti.md)
* [Testausdokumentti](documentation/testausdokumentti.md)

## Viikkoraportit

* [Viikko 1](documentation/viikkoraportit/viikko1.md)
* [Viikko 2](documentation/viikkoraportit/viikko2.md)
* [Viikko 3](documentation/viikkoraportit/viikko3.md)
* [Viikko 4](documentation/viikkoraportit/viikko4.md)
* [Viikko 5](documentation/viikkoraportit/viikko5.md)
* [Viikko 6](documentation/viikkoraportit/viikko6.md)