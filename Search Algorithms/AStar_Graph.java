import java.util.Vector;

/**
 * Created by ASUS UX360CA on 1/1/2019.
 */
public class AStar_Graph {

    int numOfSeenNodes;
    int numOfExpandedNodes;
    Vector<Integer> bestRoute;
    int cost;
    int MaxMem;
    Graph graph;

    private Vector<Integer> queue; //it is a vector for keeping
    Vector<Integer>[] routesFromOrigin; //route from i to the node
    int[] costsFromOrigin;
    int [] heuristicsToGoal;
    int[] expanded;
    boolean[] visited;

    public AStar_Graph(Graph graph) {
        numOfSeenNodes = 0;
        MaxMem = 0;
        visited = new boolean[20];

        expanded = new int[20];
        this.graph = graph;
        queue = new Vector<Integer>();
        routesFromOrigin = new Vector[20];
        costsFromOrigin = new int [20];
        heuristicsToGoal = graph.heuristics;
        for (int i = 0; i < 20; i++) {
            routesFromOrigin[i] = new Vector<Integer>();
        }
    }

    void findBestRoute(int i, int j) {
        queue.add(i);
        visited[i] = true;
        routesFromOrigin[i].add(i);


        while (!queue.isEmpty() && !visited[j]) {
//            System.out.println(queue);
//            System.out.println("cost of queue members: ");
//            for (int k = 0; k < queue.size(); k++) {
//
//                System.out.print(" " + costsFromOrigin[queue.get(k)]);
//            }
//            System.out.println();
//            for (int k = 0; k < queue.size(); k++) {
//
//                System.out.print(" " + (costsFromOrigin[queue.get(k)] + heuristicsToGoal[queue.get(k)]));
//            }
//            System.out.println();
            if (queue.size() > MaxMem)
                MaxMem = queue.size();
            int node = getLeastCost();

            Vector<Integer> neighbours = graph.neighbours(node);
            int size = neighbours.size();
            //System.out.println(neighbours);
            for (int k = 0; k < size; k++) {
                //   System.out.println("hi4");
                int n = neighbours.get(k);//a neighbour of i

                if (!visited[n]) {
                    expanded[node] = 1;
                    routesFromOrigin[n] = (Vector<Integer>) routesFromOrigin[node].clone();
                    routesFromOrigin[n].add(n);
                    costsFromOrigin[n] = costsFromOrigin[node] + graph.cost(node,n);
                    queue.add(n);
                    numOfSeenNodes++;
                    visited[n] = true;
                }
            }
            queue.removeElement(node);
        }
        bestRoute = routesFromOrigin[j];
        cost = costsFromOrigin[j];
        for (int k = 0; k < 20; k++) {
            numOfExpandedNodes += expanded[k];
        }

        System.out.print("best route is : ");
        for (int k = 0; k < bestRoute.size(); k++) {
            System.out.print(" " + graph.cities[bestRoute.get(k)]);
        }
        System.out.println();
        System.out.println("numOfExpandedNodes " + numOfExpandedNodes);
        System.out.println("numOfVisitedNodes " + numOfSeenNodes);
        System.out.println("cost is: " + cost);
        System.out.println("Max memory used is: "+MaxMem);
//        for (int k = 0; k < 20; k++) {
//            System.out.println(costsFromOrigin[k]);
//        }
    }

    private int getLeastCost() {
        int size = queue.size();
        int minTotalCost = 1000000000;
        int minNode = 0;
        for (int i = 0; i < size; i++) {
            int n = queue.get(i);
            if (costsFromOrigin[n] + heuristicsToGoal[n] < minTotalCost) {
                minTotalCost = costsFromOrigin[n] + heuristicsToGoal[n];
                minNode = n;
            }
        }
        return minNode;
    }

    public static void main(String[] args) {
        new Problem1().solve();
    }

}
