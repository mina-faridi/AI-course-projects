import java.util.Vector;

/**
 * Created by ASUS UX360CA on 1/1/2019.
 */
public class UniformCost_Graph {

    int numOfSeenNodes;
    int numOfExpandedNodes;
    Vector<Integer> bestRoute;
    int cost;
    int MaxMem;
    Graph graph;

    Vector<Integer>[] routes; //route from i to the node
    int[] costs;
    private Vector<Integer> queue;
    int[] expanded;
    boolean[] visited;

    public UniformCost_Graph(Graph graph) {
        numOfSeenNodes = 0;
        MaxMem = 0;
        visited = new boolean[20];
        expanded = new int[20];
        this.graph = graph;
        queue = new Vector<Integer>();
        routes = new Vector[20];
        costs = new int[20];
        for (int i = 0; i < 20; i++) {
            routes[i] = new Vector<Integer>();
        }
    }

    void findBestRoute(int i, int j) {
        queue.add(i);
        visited[i] = true;
        routes[i].add(i);


        while (!queue.isEmpty() && !visited[j]) {
            System.out.println(queue);
            System.out.println("cost of queue members: ");
            for (int k = 0; k < queue.size(); k++) {

                System.out.print(" " + costs[queue.get(k)]);
            }
            System.out.println();
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
                    routes[n] = (Vector<Integer>) routes[node].clone();
                    costs[n] = costs[node] + graph.cost(node, n);
                    routes[n].add(n);
                    queue.add(n);
                    numOfSeenNodes++;
                    visited[n] = true;
                }
            }
            queue.removeElement(node);
        }
        bestRoute = routes[j];
        cost = costs[j];
        for (int k = 0; k < 20; k++) {
            numOfExpandedNodes += expanded[k];
        }

//        System.out.println(bestRoute);
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
        int minCost = 1000000000;
        int minNode = 0;
        for (int i = 0; i < size; i++) {
            int n = queue.get(i);
            if (costs[n] < minCost) {
                minCost = costs[n];
                minNode = n;
            }
        }
        return minNode;
    }

    public static void main(String[] args) {
        new Problem1().solve();
    }
}
