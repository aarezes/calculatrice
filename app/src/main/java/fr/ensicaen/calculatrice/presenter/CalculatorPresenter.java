package fr.ensicaen.calculatrice.presenter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import fr.ensicaen.calculatrice.model.Calculator;
import fr.ensicaen.calculatrice.model.DivideByZeroException;
import fr.ensicaen.calculatrice.model.InvalidFormatException;
import fr.ensicaen.calculatrice.view.MainActivity;

public class CalculatorPresenter {

    MainActivity _mainActivity;
    Calculator _calculator;

    public CalculatorPresenter(MainActivity mainActivity, Calculator calculator) {
        _mainActivity = mainActivity;
        _calculator = calculator;
    }

    public void detectedClick(String str) {
        try {
            switch (str) {
                case "" : _mainActivity.setNumber(str); _calculator.noneMode(); return;
                case "DEL" : _mainActivity.delNumber(); _calculator.noneMode(); return;
                case "=" :
                    if (_calculator.isNone()|| _mainActivity.isEmpty()) {
                        return;
                    }
                    if (_mainActivity.firstIsOperator() || _mainActivity.lastIsOperator()) {
                        throw new InvalidFormatException();
                    }
                    DecimalFormat df = new DecimalFormat("#.#############", DecimalFormatSymbols.getInstance(Locale.FRANCE));
                    String str2;
                    try {
                        str2 = df.format(_calculator.calculate(String.valueOf(_mainActivity.getNumber())));
                    } catch (DivideByZeroException e) {
                        e.setMainactivity(_mainActivity);
                        e.show();
                        str2 = String.valueOf(_mainActivity.getNumber());
                    }
                    _mainActivity.setNumber(str2);
                    _calculator.equalsMode();
                    return;
            }

            if (_calculator.isNone() && !_calculator.isOperator(str.charAt(0))) {
                _mainActivity.setNumber("");
            }
            if(_mainActivity.lastIsOperator() && (_calculator.isOperator(str.charAt(0)) || str.charAt(0) == ',' || str.charAt(0) == '=')) {
                _mainActivity.delNumber();
            }
            _calculator.noneMode();
            _mainActivity.appendNumber(str);

        } catch (InvalidFormatException e) {
            e.setMainactivity(_mainActivity);
            e.show();
        }

    }

}
