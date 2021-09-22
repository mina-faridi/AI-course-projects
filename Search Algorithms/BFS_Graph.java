import java.util.Vector;

/**
 * Created by ASUS UX360CA on 12/31/2018.
 */
public class BFS_Graph {
    int numOfSeenNodes;
    int numOfExpandedNodes;
    Vector<Integer> bestRoute;
    int cost;
    int MaxMem;
    Graph graph;

    Vector<Integer>[] routes; //route from i to the node
    private Vector<Integer> queue;
    int [] expanded;
    boolean [] hasBeenInQueue;

    public BFS_Graph(Graph graph) {
        numOfSeenNodes = 0;
        MaxMem = 0;
        hasBeenInQueue = new boolean[20];
        expanded = new int[20];
        this.graph = graph;
        queue = new Vector<Integer>();
        routes = new Vector[20];
        for (int i = 0; i < 20; i++) {
            routes[i] = new Vector<Integer>();
        }
    }

    void findBestRoute(int i, int j) {
//        System.out.println("hi");
        queue.add(i);
        hasBeenInQueue[i] = true;
        routes[i].add(i);

//        System.out.println("hi2");

        while (!queue.isEmpty() && !hasBeenInQueue[j]) {
            //System.out.println(queue);
            //System.out.println("hi3");
            if (queue.size()>MaxMem)
                MaxMem = queue.size();

            Vector<Integer> neighbours = graph.neighbours(queue.get(0));
            int size = neighbours.size();
            //System.out.println(neighbours);
            for (int k = 0; k < size; k++) {
                //   System.out.println("hi4");
                int n = neighbours.get(k);//a neighbour of i

                if(!hasBeenInQueue[n]){
                    expanded[queue.get(0)] = 1;
                    routes[n] = (Vector<Integer>) routes[queue.get(0)].clone();
                    routes[n].add(n);
                    queue.add(n);
                    hasBeenInQueue[n] = true;
                    numOfSeenNodes++;
                }
            }
            queue.remove(0);

        }
        bestRoute = routes[j];
        cost = bestRoute.size() - 1;

        for (int k = 0; k < 20; k++) {
            numOfExpandedNodes += expanded[k];
        }

//        System.out.println(bestRoute);
        System.out.print("best route is : ");
        for (int k = 0; k < bestRoute.size(); k++) {
            System.out.print(" " + graph.cities[bestRoute.get(k)]);
        }
        System.out.println();
        System.out.println("numOfExpandedNodes: " + numOfExpandedNodes);
        System.out.println("numOfVisitedNodes: " + numOfSeenNodes);
        System.out.println("cost is: " + cost);
        System.out.println("Max memory used is: "+MaxMem);
//        for (int k = 0; k < 20; k++) {
//            System.out.println(routesFromOrigin[k]);
//        }
//        System.out.println(bestRoute);
    }

    public static void main(String[] args) {
        new Problem1().solve();
    }
}//todo!!!
