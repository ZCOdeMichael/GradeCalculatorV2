package calculator;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Weight {

    private double weight;
    private ObservableList<Score> scoreList;
    
    public Weight(double weight) {
        this.weight = weight;
        this.scoreList = FXCollections.observableArrayList();
    }
    
    public ObservableList<Score> getScores() { return scoreList; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public double getTotalScore() {
        double score = 0;
        double total = 0;
        for(Score ptr : scoreList) {
            score += ptr.getScore();
            total += ptr.getTotal();
        }
        if(total == 0) {
            return 0;
        }
        return score / total;
        
    }
    
    class Score{
        private double total;
        private double score;
        
        public Score(double score, double total) {
            this.total = total;
            this.score = score;
        }
        
        public double getTotal() { return total; }
        public double getScore() { return score; }
        public void setTotal(double total) { this.total = total; }
        public void setScore(double score) { this.score = score; }
        public String toString() {
            return "Score " + score + " / " + total;
        }
    }
    
    public String toString() {
        return "Weight : " + Utility.numFormator(weight) + " % || " + " Total Score % : " + Utility.numFormator((getTotalScore() * 100)) + " %";
    }
    
}
