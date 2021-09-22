import java.util.Vector;

/**
 * Created by ASUS UX360CA on 12/31/2018.
 */
public class Graph {
    int [][] graphMat;
    int heuristics[];
    String cities[];

    public Graph(int[][] graphMat, int [] heuristics) {
        this.graphMat = graphMat;
        this.heuristics = heuristics;
         cities = new String[]{"Arad", "Zeriland", "Oradea", "Timisoara", "Sibiu", "Lugoj", "Mehadia", "Fagaras", "Rimnico vilcea", "Dobreta"
         , "Craiova", "Pitesti", "Bucharest", "Giurgiu", "Urziceni", "Neamt", "Iasi", "Vaslui" , "Hirsova", "Eforie"};
    }

    boolean isNeighbour(int i, int j){
        return (graphMat[i][j] != 0);
    }
    Vector<Integer> neighbours (int i){
        Vector<Integer> neighbours = new Vector<>();
        for (int j = 0; j < graphMat.length; j++) {
            if (isNeighbour(i,j))
            neighbours.add(j);
        }
        return neighbours;
    }
    int cost(int i , int j){
        return graphMat[i][j];
    }


}
