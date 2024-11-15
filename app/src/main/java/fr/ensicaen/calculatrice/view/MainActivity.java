package fr.ensicaen.calculatrice.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import fr.ensicaen.calculatrice.databinding.ActivityMainBinding;
import fr.ensicaen.calculatrice.model.Calculator;
import fr.ensicaen.calculatrice.model.DivideByZeroException;
import fr.ensicaen.calculatrice.presenter.CalculatorPresenter;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding _activityMainBinding;
    Calculator _calculator;
    double d1, d2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        DecimalFormat df = new DecimalFormat("#.###", DecimalFormatSymbols.getInstance(Locale.US));

        _activityMainBinding.number.setText("");
        _calculator = new Calculator();
        CalculatorPresenter calculatorPresenter = new CalculatorPresenter(this, _calculator);


        _activityMainBinding.zeroButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("0");
        });

        _activityMainBinding.oneButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("1");
        });

        _activityMainBinding.twoButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("2");
        });

        _activityMainBinding.threeButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("3");
        });

        _activityMainBinding.fourButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("4");
        });

        _activityMainBinding.fiveButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("5");
        });

        _activityMainBinding.sixButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("6");
        });

        _activityMainBinding.sevenButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("7");
        });

        _activityMainBinding.eightButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("8");
        });

        _activityMainBinding.nineButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("9");
        });

        _activityMainBinding.comaButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick(",");
        });

        _activityMainBinding.acButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("");
        });

        _activityMainBinding.delButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("DEL");
        });

        _activityMainBinding.addButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("+");
        });

        _activityMainBinding.minusButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("-");
        });

        _activityMainBinding.multiplyButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("x");
        });

        _activityMainBinding.divideButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("/");
        });

        _activityMainBinding.equalsButton.setOnClickListener((View v) -> {
            calculatorPresenter.detectedClick("=");
        });

        setContentView(_activityMainBinding.getRoot());
    }

    public void appendNumber(String str) {
         _activityMainBinding.number.append(str);
    }

    public void setNumber(String str) {
        _activityMainBinding.number.setText(str);
    }
    public String getNumber() {
        return String.valueOf(_activityMainBinding.number.getText());
    }
    public void delNumber() {
        if (!isEmpty()) {
        char[] str = _activityMainBinding.number.getText().toString().toCharArray();
        _activityMainBinding.number.setText(str, 0, str.length-1);
        }
    }
    public boolean lastIsOperator() {
        if (isEmpty()) {
            return false;
        }
        return _activityMainBinding.number.getText().charAt(_activityMainBinding.number.getText().length()-1) == ',' || _calculator.isOperator(_activityMainBinding.number.getText().charAt(_activityMainBinding.number.getText().length()-1));
    }
    public boolean firstIsOperator() {
        if (isEmpty()) {
            return false;
        }
        return _calculator.isOperator(_activityMainBinding.number.getText().charAt(0));
    }
    public boolean isEmpty() {
        return _activityMainBinding.number.getText().length() == 0;
    }

    public void displayDivideByZeroError() {
        displayToast("Impossible de diviser par zéro.");
    }
    public void displayInvalidFormatError(String str) {
        displayToast(str);
    }
    private void displayToast(String str) {
        Toast toast = new Toast(getApplicationContext());
        toast.setText(str);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

}