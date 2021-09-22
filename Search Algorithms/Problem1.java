/**
 * Created by ASUS UX360CA on 12/31/2018.
 */
public class Problem1 {
    int searchKind;// = 3;//todo changeeeee
    int graphKind; //2.tree or 1.graph//todo changeeeeeeeee
    int[][] graphMat;
    int heuristics[];

    public Problem1() {
        new InputReader1(this);//reads the graphMat, searchKind, tree or graph
    }

    void solve() {//todo upgrade and complete
        int i = 0, j = 12;
        if (graphKind == 1) {//graph
            if (searchKind == 1) {
                BFS_Graph BFS = new BFS_Graph(new Graph(graphMat,heuristics));
                BFS.findBestRoute(i, j);
                System.out.println("bfs finished");

            } else if (searchKind == 2) {
                DFS_Graph_limited dfs = new DFS_Graph_limited(new Graph(graphMat,heuristics));
                dfs.findBestRoute(i, j);
                System.out.println("dfs finished");
            }
            else if (searchKind == 3){
                UniformCost_Graph uni = new UniformCost_Graph(new Graph(graphMat,heuristics));
                uni.findBestRoute(i,j);
                System.out.println("uniform cost finished");
            }else  if(searchKind == 4){
                AStar_Graph aStarGraph = new AStar_Graph(new Graph(graphMat,heuristics));
                aStarGraph.findBestRoute(i, j);
                System.out.println("aStarGraph finished");
            }else  if(searchKind == 5){
                Greedy_BestFirst_Graph gre = new Greedy_BestFirst_Graph(new Graph(graphMat,heuristics));
                gre.findBestRoute(i, j);
                System.out.println("Greedy finished");
            }
        } else if (graphKind == 2) {//tree
            if (searchKind == 1) {
                BFS_Tree BFS = new BFS_Tree(new Graph(graphMat,heuristics));
                BFS.findBestRoute(i, j);
                System.out.println("bfs finished");
            }
        }
    }
}
