import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable <Chromosome>{
    //Sets this up for selecting random numbers later on in the class.
    private static Random rng = new Random();

    //Empty constructor
    public Chromosome(){}

    //Creates the Chromosome
    public Chromosome (ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            int ranNum = rng.nextInt(10);
            if(ranNum == 1){
                this.add(new Item(items.get(i)));
                this.get(i).setIncluded(true);
                this.get(i);
            }
            else {
                this.add(new Item(items.get(i)));
                this.get(i).setIncluded(false);
            }
        }
    }

    //Determines which chromosomes from each of the parents should be added.
    public Chromosome crossover(Chromosome Other){
        for(int i = 0; i < this.size(); i ++){
            if(rng.nextInt(2) == 1){
                if(this.get(i).getIncluded()){
                    this.get(i).setIncluded(false);
                }
                else {
                    this.get(i).setIncluded(true);
                }
            }
        }
        return this;
    }

    //Randomly changes if the items that will be included in the mutation
    public void mutate(){
        for(int i = 0; i < this.size(); i++){
            if(rng.nextInt(5) == 1){
                if(this.get(i).getIncluded()){
                    this.get(i).setIncluded(false);
                }
                else{
                    this.get(i).setIncluded(true);
                }
            }
        }
    }

    //Returns the fitness score of the chromosome
    public double getFitness(){
        double totalWeight = 0;
        for(int i = 0; i < this.size(); i++) {
            if (this.get(i).getIncluded() == true) {
                totalWeight += this.get(i).getWeight();
            }
            //By returning 0 here it breaks the loop and makes the algorithm more efficient
            if (totalWeight > 10){
                return 0;
            }
        }
        return totalWeight;
    }

    //Compares the fitness of the two chromosomes and returns values based on which one has a better fitness score or
    // if they're the same
    public int compareTo(Chromosome o) {
        if(this.getFitness() > o.getFitness()){
            return -1;
        }
        else if(this.getFitness() < o.getFitness()){
            return 1;
        }
        else {
            return 0;
        }
    }

    //Returns readable information the name value and weight of each item included in the chromosome
    public String ToString(){
        String returnedValue ="";
        for(Item c: this){
            if(c.getIncluded() == true) {
                returnedValue += c.getName() + " " + c.getWeight() + " " + c.getValue() +" " ;
            }
        }
        if(returnedValue == ""){
            return "There's no items in this chromosome";
        }
        returnedValue += "-> " + this.getFitness();
        return returnedValue;
    }
}