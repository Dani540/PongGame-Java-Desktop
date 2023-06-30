package com.app.fxtestgame.secondVersion.repo;

public class StylesRepo {
    public static String paneStyle(double width, double height) {
        return "-fx-background-color: lightcyan;" +
                "-fx-max-height: " + height + ";" +
                "-fx-min-height: " + height + ";" +
                "-fx-pref-height: " + height + ";" +
                "-fx-max-width: " + width + ";" +
                "-fx-min-width: " + width + ";" +
                "-fx-pref-width: " + width + ";";
    }

    public static String playerStyle(double width, double height) {
        return "-fx-background-color: white;" +
                "-fx-border-width:     2;" +
                "-fx-border-color:     black;" +
                "-fx-min-height:   " + height + ";" +
                "-fx-max-height:   " + height + ";" +
                "-fx-pref-height:  " + height + ";" +
                "-fx-min-width:    " + width + ";" +
                "-fx-max-width:    " + width + ";" +
                "-fx-pref-width:   " + width + ";";
    }

    public static String ballStyle(double size) {
        return "-fx-background-color: white;" +
                "-fx-border-color:          black;" +
                "-fx-border-width:          2;" +
                "-fx-background-radius: " + size + ";" +
                "-fx-border-radius:     " + size + ";" +
                "-fx-max-height:        " + size + ";" +
                "-fx-min-height:        " + size + ";" +
                "-fx-pref-height:       " + size + ";" +
                "-fx-max-width:         " + size + ";" +
                "-fx-min-width:         " + size + ";" +
                "-fx-pref-width:        " + size + ";";
    }

    public static String getLabelStyle(double width, double height) {
        return "-fx-background-color: white;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 2;" +
                "-fx-font-family: 'Lato Black';" +
                "-fx-font-size: 12;" +
                "-fx-text-alignment: center;" +
                "-fx-alignment: center;" +
                "-fx-max-width: " + width + ";" +
                "-fx-min-width: " + width + ";" +
                "-fx-pref-width: " + width + ";" +
                "-fx-max-height: " + height + ";" +
                "-fx-min-height: " + height + ";" +
                "-fx-pref-height: " + height + ";";
    }
}
