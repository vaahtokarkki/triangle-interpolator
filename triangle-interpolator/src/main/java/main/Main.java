package main;

import ui.UI;

public class Main {

    public static void main(String[] args) {
        String dataFolder = null;

        if (args.length > 0) {
            dataFolder = args[0];
        }

        UI ui = new UI();
        ui.start(dataFolder);
    }

}
