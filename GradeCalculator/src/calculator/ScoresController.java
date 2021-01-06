package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ScoresController {

    @FXML
    private ListView<Weight.Score> scoreList;

    @FXML
    private TextField scoreInput, totalInput;

    @FXML
    private Text currScore;
    
    private Weight selectedWeight;
    
    private MainController main_Controller;
    
    @FXML
    void on_Add(ActionEvent event) {
        Weight.Score curr = selectedWeight.new Score(Double.parseDouble(scoreInput.getText()), Double.parseDouble(totalInput.getText()));
        scoreList.getItems().add(curr);
        selectedWeight.getScores().add(curr);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeightList.fxml"));
        MainController controller = loader.getController();
        controller.updateList();
    }

    @FXML
    void on_Edit(ActionEvent event) {

    }

    @FXML
    void on_Remove(ActionEvent event) {

    }
    
    public void init(Weight selectedWeight, MainController cumain_Controllerrr) {
        this.selectedWeight = selectedWeight;
        this.main_Controller = main_Controller;
        scoreList.getItems().addAll(selectedWeight.getScores());
    }

}
