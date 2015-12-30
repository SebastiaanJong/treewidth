import java.util.*;
import java.util.stream.Collectors;

public class SimulatedAnnealing extends LocalSearch {

    public double T;
    public double alpha;

    public Random random;

    public SimulatedAnnealing(Solution initialSolution, double T, double alpha, int seed){
        super(initialSolution);

        this.T = T;
        this.alpha = alpha;

        this.random = new Random(seed);
    }

    public Solution run(Graph g) {
        Solution currentSolution = initialSolution;
        Solution bestSolution = currentSolution.copy();
        int bestScore = Integer.MAX_VALUE;
        int lastImprovement = 0;


        HashMap<Vertex, Set<Vertex>> currentSuccessors;
        int currentScore;

        while(true){
            currentSuccessors = triangulate(currentSolution, g.copy());
            currentScore = score(currentSuccessors);

            if(currentScore < bestScore){
                bestScore = currentScore;
                bestSolution = currentSolution;
                lastImprovement = 0;
            }

            ArrayList<Solution> neighbors = neighborhood(currentSolution, currentSuccessors);
            Collections.shuffle(neighbors, random);

//            System.out.println("Score:" + currentScore + ", Treewidth:" + treeWidth(currentSuccessors) + ", T:" + T + ", lastImprovement:" + lastImprovement);

            for(Solution neighbor: neighbors){
                HashMap<Vertex, Set<Vertex>> neighborSuccessors = triangulate(neighbor, g.copy());
                int neighborScore = score(neighborSuccessors);

                if(neighborScore < currentScore){
                    currentSolution = neighbor;
                    break;
                } else {
                    int diff = currentScore-neighborScore;
                    double lambda = (diff != 0 ? -diff : 1)/T;
                    double p = Math.log(1-random.nextDouble())/(-lambda);
                    double x = random.nextDouble();
                    if(x < p){
                        currentSolution = neighbor;
                        break;
                    }
                }
                lastImprovement++;

            }

            T = T * alpha;
            if(lastImprovement > 200){
                break;
            }
        }
        HashMap<Vertex, Set<Vertex>> bestSuccessors = triangulate(bestSolution, g.copy());

//        System.out.println("Score:" + bestScore + ", Treewidth:" + treeWidth(bestSuccessors) + ", T:" + T);
//        System.out.println(treeWidth(bestSuccessors));
        return bestSolution;
    }
}