import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GeneticAlgorithm {
    private static Random rng = new Random();

    //Creates the original population of chromosomes
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize){
        ArrayList<Chromosome> listOfChromosomes = new ArrayList<>();
        for(int i = 0; i < populationSize; i++){
            listOfChromosomes.add(new Chromosome(items));
        }
        return listOfChromosomes;
    }

    //Reads in the data from the items file
    public static ArrayList<Item> readData(String fileName) throws FileNotFoundException {
        File itemsFile = new File("items.txt");
        Scanner input = new Scanner(new FileReader(itemsFile));
        input.useDelimiter(",|\\n");

        ArrayList<Item> items = new ArrayList<>();

        while (input.hasNext()){
            String name = input.next();
            double weight = Double.parseDouble(input.next().trim());
            int value = Integer.parseInt(input.next().trim());
            items.add(new Item(name, weight, value));
        }
        return items;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //Reads in data from file and saves it to an ArrayList
        ArrayList<Item> listOfItems = readData("items.txt");

        ArrayList<Chromosome> population = new ArrayList<>();
        population.addAll(initializePopulation(listOfItems, 10));

        for(int i = 0; i < 1000; i++){
            population.sort(Chromosome::compareTo);

            ArrayList<Chromosome> childChromosomes = new ArrayList<>();

            //Creates children chromosomes
            for(int p = 0; p < population.size(); p++){
                childChromosomes.add(population.get(p).crossover(population.get(rng.nextInt(population.size()))));
            }

            childChromosomes.sort(Chromosome::compareTo);
            for(int p = 0; p < 10; p++){
                population.add(childChromosomes.get(p));
            }

            for (Chromosome c: population) {
                if(rng.nextInt(15) == 1){
                    c.mutate();
                }
            }
        }
        population.sort(Chromosome::compareTo);

        System.out.println(population.get(0).ToString());
    }
}
