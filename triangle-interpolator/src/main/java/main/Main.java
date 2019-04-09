package main;

import geometry.Point;
import geometry.Triangle;
import static interpolate.Interpolator.*;
import static io.Writer.*;
import java.util.*;
import ui.UI;
import utils.CsvParse;
import utils.MyArrayList;
import utils.MyHashSet;
import utils.MyMath;

/**
 *
 * @author lroni
 */
public class Main {

    public static void main(String[] args) {
       CsvParse p = new CsvParse(null);
       p.parsePointsFromFile(";", 0, 0, 0);
         
//        UI ui = new UI();
//        ui.start();

    }

}
