import java.util.*;

public class AdjacencyListGr {

    private int verticesAmount;
    protected LinkedList<Vertex>[] adjacencyList;


    AdjacencyListGr(int verticesAmount) {
        this.verticesAmount = verticesAmount;
        adjacencyList = new LinkedList[verticesAmount];

        for (int i = 0; i < verticesAmount; ++i)
            adjacencyList[i] = new LinkedList();
    }

    public void addEdge(Vertex v, Vertex v2) { adjacencyList[v.value].add(v2); }


    public void DFS_helper(Vertex v, boolean[] visited) {
        visited[v.value] = true;
        System.out.print(v + " ");
        for (Vertex vert : adjacencyList[v.value]) {
            if (!visited[vert.value])
                DFS_helper(vert, visited);
        }
    }


    public void DFS(Vertex v) {
        boolean[] visited = new boolean[verticesAmount];
        DFS_helper(v, visited);
    }



    public void BFS(Vertex v) {
        boolean[] visited = new boolean[verticesAmount];
        LinkedList<Vertex> queue = new LinkedList<>();
        visited[v.value] = true;
        queue.add(v);

        while (queue.size() != 0) {
            v = queue.poll();
            System.out.print(v + " ");

            for (Vertex vert : adjacencyList[v.value]) {
                if (!visited[vert.value]) {
                    visited[vert.value] = true;
                    queue.add(vert);
                }
            }
        }
    }


    public void printGraph(){
        for (int i = 0; i < verticesAmount; i++) {
            System.out.print("[" + i + "]");
            for (int j = 0; j < adjacencyList[i].size(); j++) {
                System.out.print(" -> " + adjacencyList[i].get(j));
            }
            System.out.println();
        }
    }

    private static class Vertex {
        int value;
        public Vertex(int value){
            this.value = value;
        }
        public String toString() { return  value + " "; }
    }

    public static void main(String[] args) {
        AdjacencyListGr g = new AdjacencyListGr(5);

        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);


        g.addEdge(v0,v1);
        g.addEdge(v0,v4);
        g.addEdge(v2,v1);
        g.addEdge(v3,v4);
        g.addEdge(v3,v2);
        g.addEdge(v2,v3);
        g.addEdge(v4,v2);

        g.printGraph();
        System.out.println("--------DFS---------");
        g.DFS(v0);
        System.out.println("\n--------BFS---------");
        g.BFS(v0);
    }

}
