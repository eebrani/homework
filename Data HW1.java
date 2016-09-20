import java.util.Arrays;

public class CustomerServiceRepScores
{
    private int repQuantity;
    private int numberOfPossibleScores;
    private int[][] scores;
    private int[][] lastScores;
    private double[][] averages; 
    private int[][] counter;
    private int average;
    public CustomerServiceRepScores(int repQuantity, int scoreQuantity)
    {
        this.repQuantity = repQuantity;
        this.numberOfPossibleScores = scoreQuantity;
        this.lastScores = new int[this.repQuantity][20];
        this.averages = new double[repQuantity][1];
        this.average = 0;
        this.counter = new int[repQuantity][1];
        this.scores = new
        int[this.repQuantity][this.numberOfPossibleScores];
        //initialize all score counts to zero
        for(int i = 0; i < this.scores.length; i++)
        {
            Arrays.fill(this.scores[i],0);
        }
    }

    /**
     *
     * @param repID the representative who received this score.
     * @param score the score received
     */
    public void addNewScore(int repID, int score)
    {
        this.scores[repID][score-1] += 1;
        recentScore(repID, score);
        count(repID); 
    }

    public void recentScore(int repID, int score)
    {
        this.lastScores[repID][this.counter[repID][0]] = (score);
    }

    public void count(int repID)
    {
        if (this.counter[repID][0] < 20){
            this.counter[repID][0]++;
        }
        else {
            int x = this.counter[repID][0] % 20;
            this.counter[repID][0] = x;
        }
    }

    /**
     *
     * @param repID the id of the rep
     * @return an array of length this.numberOfPossibleScores with the
    current score totals for the rep
     */
    public int[] getCumulativeScoreForRep(int repID)
    {
        return Arrays.copyOf(this.scores[repID],this.scores[repID].length);
    }

    /**
     *
     * @param repID the id of the rep
     * @return an array of length this.numberOfPossibleScores with the
    current score totals for the rep
     */
    public int[] getCumulativeScoreAndAverageForRep(int repID)
    {
        int[][] copy =  Arrays.copyOf(this.scores[repID],this.scores[repID].length+1);
        copy[this.scores[repID]][this.scores[repID].length+1] = getAverage();
        return copy;
    }

    public int getAverage(int repID)
    {  
        if (average <= 2.5) {
            for (int i = 0; i < lastScores.length; i++) {
                average += lastScores[repID][i];
            }
            return average;
        }
        else {
            for (int i = 0; i < lastScores.length; i++) {
                average += lastScores[repID][i];
            }
            System.out.println( "Rep"  +
                repID + "’s running average has dropped to " +
                average); 
            return average;
        }
    }

    public void resetCurrentRep(int repID)
    {
        for (int i =0; i < this.scores.length; i++) {
            Arrays.fill(this.scores[repID],0);
        }
        for (int i =0; i < this.scores.length; i++) {
            this.lastScores[repID][this.counter[repID][i]] = (0);
        }
    }

    public void resetAllReps()
    {
        for(int i = 0; i < this.scores.length; i++)
        {
            Arrays.fill(this.scores[i],0);
        }
        for (int i =0; i < this.scores.length; i++) {
            this.lastScores[i][this.counter[ia][i]] = (0);
        }
    }
}

