package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainController {

    @FXML
    private ListView<Weight> weightList;

    @FXML
    private TextField weight_Input;

    @FXML
    private Text perc_Remaining, result;

    @FXML
    private Button add, remove, edit, save, addScore;
    
    @FXML
    void on_Add(ActionEvent event) {
        addWeight(weight_Input.getText());
        
    }
    
    @FXML
    void on_Add_Scores(ActionEvent event) {

    }

    @FXML
    void on_Edit(ActionEvent event) {
        
    }

    @FXML
    void on_Input(ActionEvent event) {
        addWeight(weight_Input.getText());
    }

    @FXML
    void on_Mouse_Clicked(MouseEvent event) {
        if(event.getButton() == MouseButton.SECONDARY) {
            removeWeight();
            return;
        }
        /*
        Double click detection
        if(event.getClickCount() == 2) {
            System.out.println("We got'em BOI!!! " + weightList.getSelectionModel().getSelectedIndex());
        }
        */
        if(weightList.getSelectionModel().getSelectedIndex() != -1) {
            disableButtons(false);
            weight_Input.setText(String.valueOf(weightList.getItems().get(weightList.getSelectionModel().getSelectedIndex()).getWeight()));
        }
        
        
    }

    @FXML
    void on_Remove(ActionEvent event) {
        removeWeight();
        
    }
    

    @FXML
    void on_Save(ActionEvent event) {

    }
    
    private void editScores() {
        
    }
    
    private void removeWeight() {
        if(weightList.getSelectionModel().getSelectedIndex() == -1) {
            alertGen("Invalid Selection", "Please select a valid item");
            return;
        }
        perc_Remaining.setText(String.valueOf(Double.parseDouble(perc_Remaining.getText()) 
                                    + weightList.getItems().get(weightList.getSelectionModel().getSelectedIndex()).getWeight()));
        weightList.getItems().remove(weightList.getSelectionModel().getSelectedIndex());
        updateResult();
        
        weightList.getSelectionModel().clearSelection();
        disableButtons(true);
        
    }
    
    private void disableButtons(boolean bool) {
        addScore.setDisable(bool);
        remove.setDisable(bool);
        edit.setDisable(bool);
    }
    
    private void addWeight(String weight) {
        try {
            if(Double.parseDouble(perc_Remaining.getText()) == 0 ||
                    (Double.parseDouble(perc_Remaining.getText()) - Double.parseDouble(weight)) < 0) {
                alertGen("Invalid Number of Weights", "Too many weights entered");
                return;
            }
            
            if(Double.parseDouble(weight) < 0) {
                alertGen("Invalid Input", "Please enter a valid weight");
                return;
            }
            
            Weight curr = new Weight(Double.parseDouble(weight));
            weightList.getItems().add(curr);
            perc_Remaining.setText(Double.parseDouble(perc_Remaining.getText()) - Double.parseDouble(weight) + "");
            
            updateResult();
            
        } catch(NumberFormatException e) {
            alertGen("Number Format Error", "Please enter a valid weight");
        } catch(Exception e) {
            alertGen("Number Format Error", "Please enter a valid weight");
        }
    }
    
    
    private void updateResult() {
        double score = 0;
        for(Weight ptr : weightList.getItems()) {
            score += ptr.getWeight() * ptr.getTotalScore();
        }
        result.setText(score + " %");
    }
    
    private void alertGen(String header, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    

}
