import java.util.Vector;

/**
 * Created by ASUS UX360CA on 12/31/2018.
 */
public class DFS_Graph_limited {//limited depth
    int numOfSeenNodes;
    int numOfExpandedNodes;
    boolean hasBeenInStack[];
    Vector<Integer> bestRoute;
    int cost;
    int MaxMem;
    Graph graph;
    boolean hasFound;
    private int stackTop;

    Vector<Integer>[] routes; //route from i to the node
    int [] expanded;

    public DFS_Graph_limited(Graph graph) {
        hasBeenInStack = new boolean[20];
        numOfSeenNodes = 0;
        MaxMem = 0;
        expanded = new int[20];
        this.graph = graph;
        routes = new Vector[20];
        for (int i = 0; i < 20; i++) {
            routes[i] = new Vector<Integer>();
        }
    }

    void findBestRoute(int i, int j) {//limited depth
        hasBeenInStack [0] = true;
        routes[i].add(i);
        stackTop++;
        DFS(i,j);

        bestRoute = routes[j];
        cost = bestRoute.size() - 1;

        for (int k = 0; k < 20; k++) {
            numOfExpandedNodes += expanded[k];
        }

//        for (int k = 0; k < 20; k++) {
//            System.out.println(routes[k]);
//        }
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
    }

    private void DFS(int i, int j){
        if (hasFound)
            return;
        stackTop++;
        if (MaxMem < stackTop)
            MaxMem = stackTop;
        numOfSeenNodes++;

        if (i == j){
            hasFound=true;
            return;
        }
        Vector<Integer> neighbours = graph.neighbours(i);
        int size = neighbours.size();
        for (int k = 0; k < size; k++) {
            int n = neighbours.get(k);//a neighbour of i
            if(!hasBeenInStack[n]){
                expanded[i] = 1;
                routes[n] = (Vector<Integer>) routes[i].clone();
                routes[n].add(n);
                hasBeenInStack[n] = true;

                DFS(n,j);

            }
        }
        stackTop--;
    }

    public static void main(String[] args) {
        new Problem1().solve();
    }
}
