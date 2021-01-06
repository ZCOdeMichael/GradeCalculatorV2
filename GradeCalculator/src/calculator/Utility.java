package calculator;

import java.text.DecimalFormat;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utility {
    
    public static String numFormator(double num) {
        DecimalFormat numberFormat = new DecimalFormat();
        numberFormat.applyPattern("0.00");
        return numberFormat.format(num);
    }
    
    
}
