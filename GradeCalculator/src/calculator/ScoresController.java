package calculator;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScoresController {

    @FXML
    private ListView<Weight.Score> scoreList;

    @FXML
    private TextField scoreInput, totalInput;

    @FXML
    private Text currScore;
    
    @FXML
    private Button remove, edit;
    
    private Weight selectedWeight;
    
    private MainController main_Controller;
    
    @FXML
    void on_Add(ActionEvent event) {
        try {
            if(validInput()) {
                Weight.Score curr = selectedWeight.new Score(Double.parseDouble(scoreInput.getText()), Double.parseDouble(totalInput.getText()));
                scoreList.getItems().add(curr);
                selectedWeight.getScores().add(curr);
                
                
                cleanUp();
            }else {
                Utility.alertGen("Number Format Error", "Please enter a valid score");
            }
        } catch(Exception e) {
            Utility.alertGen("Number Format Error", "Please enter a valid score");
        }
        
        
    }

    @FXML
    void on_Edit(ActionEvent event) {
        try {
            if(validInput()) {
                scoreList.getItems().get(scoreList.getSelectionModel().getSelectedIndex()).setScore(Double.parseDouble(scoreInput.getText()));
                scoreList.getItems().get(scoreList.getSelectionModel().getSelectedIndex()).setTotal(Double.parseDouble(totalInput.getText()));
                scoreList.refresh();
                
                selectedWeight.getScores().get(scoreList.getSelectionModel().getSelectedIndex()).setScore(Double.parseDouble(scoreInput.getText()));
                selectedWeight.getScores().get(scoreList.getSelectionModel().getSelectedIndex()).setTotal(Double.parseDouble(totalInput.getText()));
                
                
                cleanUp();
            }
        } catch(Exception e) {
            Utility.alertGen("Number Format Error", "Please enter a valid score");
        }
    }
    
    @FXML
    void on_Mouse_Click(MouseEvent event) {
        if(scoreList.getSelectionModel().getSelectedIndex() != -1) {
            scoreInput.setText(String.valueOf(selectedWeight.getScores().get(scoreList.getSelectionModel().getSelectedIndex()).getScore()));
            totalInput.setText(String.valueOf(selectedWeight.getScores().get(scoreList.getSelectionModel().getSelectedIndex()).getTotal()));
            disableButtons(false);
        }
    }

    @FXML
    void on_Remove(ActionEvent event) throws Exception {
        if(scoreList.getSelectionModel().getSelectedIndex() != -1) {
            int index = scoreList.getSelectionModel().getSelectedIndex();
            
            scoreList.getItems().remove(index);
            selectedWeight.getScores().remove(index);
            
            cleanUp();
        }else {
            Utility.alertGen("Invalid Selection", "Please select a valid item");
        }
    }
    
    private boolean validInput() {
        return !(Double.parseDouble(scoreInput.getText()) < 0 || Double.parseDouble(totalInput.getText()) < 0);
        
    }
    
    private void updateScore() {
        currScore.setText(String.valueOf(selectedWeight.getTotalScore()*100) + " %");
    }
    
    private void cleanUp() throws Exception {
        main_Controller.update();
        updateScore();
        scoreInput.clear();
        totalInput.clear();
        scoreList.getSelectionModel().clearSelection();
        disableButtons(true);
    }
    
    private void disableButtons(boolean bool) {
        remove.setDisable(bool);
        edit.setDisable(bool);
    }
    
    public void init(Weight selectedWeight, MainController main_Controller) {
        this.selectedWeight = selectedWeight;
        this.main_Controller = main_Controller;
        scoreList.getItems().addAll(selectedWeight.getScores());
    }
    
}
