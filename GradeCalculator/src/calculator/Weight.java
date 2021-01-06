package calculator;

import java.util.ArrayList;

public class Weight {

    private double weight;
    private ArrayList<Score> scoreList;
    
    public Weight(double weight) {
        this.weight = weight;
        this.scoreList = new ArrayList<>();
    }
    
    public ArrayList<Score> getScores() { return scoreList; }
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
        
        public Score(double total, double score) {
            this.total = total;
            this.score = score;
        }
        
        public double getTotal() { return total; }
        public double getScore() { return score; }
    }
    
    public String toString() {
        return "Weight : " + Utility.numFormator(weight) + " % || " + " Total Score % : " + Utility.numFormator((getTotalScore() * 100)) + " %";
    }
    
}
