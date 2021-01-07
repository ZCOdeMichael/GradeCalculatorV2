package calculator;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        addScores();
        
    }

    @FXML
    void on_Edit(ActionEvent event) {
        editScores();
            
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
        if(event.getClickCount() == 2) {
            if(weightList.getSelectionModel().getSelectedIndex() != -1) {
                addScores();
            }
        }
       
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
        // WIP
    }
    
    private void addScores() {
        if(weightList.getSelectionModel().getSelectedIndex() != -1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scores.fxml"));
            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(loader.load()));
                stage.setTitle(weightList.getItems().get(weightList.getSelectionModel().getSelectedIndex()).getWeight() + "% - Weight");
                
                ScoresController controller = loader.getController();
                controller.init(weightList.getItems().get(weightList.getSelectionModel().getSelectedIndex()), this);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void editScores() {
        try {
            if(weightList.getSelectionModel().getSelectedIndex() != -1) {
                double remain = Double.parseDouble(perc_Remaining.getText()) + 
                        (weightList.getItems().get(weightList.getSelectionModel().getSelectedIndex()).getWeight() 
                                - Double.parseDouble(weight_Input.getText()));
                
                if(Double.parseDouble(weight_Input.getText()) < 0 
                    || remain < 0) {
                    Utility.alertGen("Invalid Number of Weights or Invalid Input", "Too many weights entered");
                    return;
                }
                
                weightList.getItems().get(weightList.getSelectionModel().getSelectedIndex()).setWeight(Double.parseDouble(weight_Input.getText()));
                perc_Remaining.setText(String.valueOf(remain));
                updateResult();
                weightList.refresh();
                weightList.getSelectionModel().clearSelection();
                disableButtons(true);
                weight_Input.clear();
            }
        } catch(Exception e) {
            Utility.alertGen("Invalid Input", "Please enter a valid weight");
        }
    }
    
    private void removeWeight() {
        if(weightList.getSelectionModel().getSelectedIndex() == -1) {
            Utility.alertGen("Invalid Selection", "Please select a valid item");
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
                Utility.alertGen("Invalid Number of Weights or Invalid Input", "Too many weights entered");
                return;
            }
            
            if(Double.parseDouble(weight) < 0) {
                Utility.alertGen("Invalid Input", "Please enter a valid weight");
                return;
            }
            
            Weight curr = new Weight(Double.parseDouble(weight));
            weightList.getItems().add(curr);
            perc_Remaining.setText(Double.parseDouble(perc_Remaining.getText()) - Double.parseDouble(weight) + "");
            
            updateResult();
            
        } catch(NumberFormatException e) {
            Utility.alertGen("Number Format Error", "Please enter a valid weight");
        } catch(Exception e) {
            Utility.alertGen("Number Format Error", "Please enter a valid weight");
        }
    }
    
    
    private void updateResult() {
        double score = 0;
        for(Weight ptr : weightList.getItems()) {
            score += ptr.getWeight() * ptr.getTotalScore();
        }
        result.setText(score + " %");
    }
    
    
    public void update() {
        updateResult();
        weightList.refresh();
    }
    
    

}
