import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ASUS UX360CA on 6/10/2018.
 */
public class InputReader1 {

    String fileName = "graph1";
    Problem1 problem1;

    public InputReader1(Problem1 problem1) {
        Scanner sc = new Scanner(System.in);

        this.problem1 = problem1;
        System.out.println("what kind of grap? 1)graph 2)tree");
        problem1.graphKind = sc.nextInt();

        System.out.println("what kind of search? 1)BFS 2)DFS 3)uniform cost 4)aStar 5)Greedy_BestFirst_Graph");
        problem1.searchKind = sc.nextInt();

        //fileName = new String();//now we are going to get the graph
        //System.out.println("enter file name");
        //fileName = sc.next();

        problem1.graphMat = getGraph();

    }

        private int[][] getGraph() {
            int [][] graph = new int[20][20];//todo i dont know size ???
            int heuristics[] = new int[20];
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int v1, v2, v3;
            line = br.readLine();
            String[] l = line.split(" ");
            v1 = Integer.parseInt(l[0]);
            v2 = Integer.parseInt(l[1]);
            v3 = Integer.parseInt(l[2]);
            graph[v1][v2] = v3;
            while (!(line = br.readLine()).equals("heuristics:")) {
                l = line.split(" ");
                v1 = Integer.parseInt(l[0]);
                v2 = Integer.parseInt(l[1]);
                v3 = Integer.parseInt(l[2]);
                graph[v1][v2] = v3;
            }
            for (int i = 0; i < 20; i++) {
                line = br.readLine();
//                l = line.split(" ");
                v1 = Integer.parseInt(line);
                heuristics[i] = v1;
            }
            problem1.heuristics = heuristics;
//                for (int i = 0; i < 20; i++) {
//                    System.out.println();
//                    for (int j = 0; j < 20; j++) {
//                        System.out.print(graph[i][j]+ " ");
//                    }
//                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
