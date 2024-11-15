package fr.ensicaen.calculatrice.model;

import fr.ensicaen.calculatrice.view.MainActivity;

public class InvalidFormatException extends Exception{

    private MainActivity _mainActivity;

    public InvalidFormatException() {
    }

    public void setMainactivity(MainActivity mainactivity) {
        _mainActivity = mainactivity;
    }

    public void show(){
        _mainActivity.displayInvalidFormatError("Le format n'est pas valide.");
    }

}
