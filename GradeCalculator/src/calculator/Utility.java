package calculator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utility {
    
    public static String numFormator(double num) {
        DecimalFormat numberFormat = new DecimalFormat();
        numberFormat.applyPattern("0.00");
        return numberFormat.format(num);
    }
    
    public static void alertGen(String header, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public static void addToOut(FileOutputStream fd, String line) throws IOException {
        for(int i = 0; i < line.length(); i++) {
            fd.write((byte) line.charAt(i));
        }
        fd.write((byte) '\n');
    }
}
