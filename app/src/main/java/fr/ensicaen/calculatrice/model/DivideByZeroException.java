package fr.ensicaen.calculatrice.model;

import fr.ensicaen.calculatrice.view.MainActivity;

public class DivideByZeroException extends Exception{

    private MainActivity _mainActivity;

    public DivideByZeroException() {
    }

    public void setMainactivity(MainActivity mainactivity) {
        _mainActivity = mainactivity;
    }

    public void show(){
        _mainActivity.displayDivideByZeroError();

    }

}
