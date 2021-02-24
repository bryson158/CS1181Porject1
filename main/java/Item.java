public class Item {
    private final String name;
    private final double weight;
    private final double value;
    private boolean included;

    //Constructor for the item class
    public Item(String name, double weight, double value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    //Changes the values of the item to be equal to the other item
    public Item(Item other){
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
    }

    //Will change the value of the included variable of the item
    public void setIncluded(boolean included) {
        this.included = included;
    }

    //Get methods for returning values of the items when needed
    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }

    public boolean getIncluded(){
        return included;
    }

    @java.lang.Override
    public String toString() {
        return  name + " (" + weight + " lbs, $" + value + ')';
    }
}