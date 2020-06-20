import java.util.*;

public class MatrixGraph {

    private static int[][] adjacencyUndirectedMatrix;
    private static int[][] adjacencyMatrix;
    private static int[][] incidenceMatrix;
    private int verticesAmount;
    private ArrayList<Edge> edges;
    private ArrayList<Vertex> vertices;


    public MatrixGraph(int verticesAmount) {
        this.verticesAmount = verticesAmount;
        adjacencyMatrix = new int[verticesAmount][verticesAmount];
        incidenceMatrix = new int[verticesAmount][verticesAmount];
        adjacencyUndirectedMatrix = new int[verticesAmount][verticesAmount];

        edges = new ArrayList<>();
        vertices = new ArrayList<>();

        for(int i = 0; i < verticesAmount; i++){
            for (int j = 0; j < verticesAmount; j++){
                adjacencyMatrix[i][j] = 0;
                incidenceMatrix[i][j] = 0;
                adjacencyUndirectedMatrix[i][j] = 0;
            }
        }
    }


    public void updateAdjacency(){
        for(int i = 0; i < verticesAmount; i++)
            for (int j = 0; j < verticesAmount; j++) {
                adjacencyMatrix[i][j] = 0;
                adjacencyUndirectedMatrix[i][j] = 0;
            }

        for (Edge current : edges) {
            Vertex from = current.from;
            Vertex to = current.to;
            int ii = vertices.indexOf(from);
            int jj = vertices.indexOf(to);
            adjacencyMatrix[ii][jj] = 1;
            adjacencyUndirectedMatrix[ii][jj] = 1;
            adjacencyUndirectedMatrix[jj][ii] = 1;
        }
    }

    public void updateIncidence(){
        for(int i = 0; i < verticesAmount; i++)
            for (int j = 0; j < verticesAmount; j++)
                incidenceMatrix[i][j] = 0;

        for (Edge current : edges) {
            Vertex from = current.from;
            Vertex to = current.to;
            int ii = vertices.indexOf(from);
            int jj = vertices.indexOf(to);
            incidenceMatrix[ii][jj] = 1;
            incidenceMatrix[jj][ii] = -1;
        }
    }

    public void addEdge(Vertex from, Vertex to, int weight){
        edges.add(new Edge(from, to, weight));
    }

    public boolean addVertex(Vertex v){
        for(Vertex vert : vertices){
            if(v.equals(vert)) {
                System.out.println("This vertex is already in the graph");
                return false;
            }
        }
        vertices.add(v);
        return true;
    }


    public void printMatrix(int[][] matrix){
        for(int i = 0; i < verticesAmount; i++) {
            System.out.print(vertices.get(i).toString() + " |");
            for (int j = 0; j < verticesAmount; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }


    private static class Edge{
        Vertex from;
        Vertex to;
        int weight;

        public Edge(Vertex from, Vertex to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Vertex{
        int value;
        public Vertex(int value){ this.value = value; }
        public String toString(){ return value + " ";}
    }


    public void mstKruskal(){
        PriorityQueue<Edge> queue = new PriorityQueue<>(edges.size(), Comparator.comparingInt(o -> o.weight));
        queue.addAll(edges);

        int[] pointers = new int[verticesAmount];
        for (int i = 0; i <verticesAmount ; i++)
            pointers[i] = i;

        ArrayList<Edge> spanningTree = new ArrayList<>();
        int index = 0;
        while(index < verticesAmount-1){
            Edge edge = queue.remove();
            int x_set = find(pointers, edge.from.value);
            int y_set = find(pointers, edge.to.value);

            if(x_set == y_set){
            }else {
                spanningTree.add(edge);
                index++;
                union(pointers,x_set,y_set);
            }
        }
        printEdges(spanningTree);
    }

    public int find(int[] pointers, int value){
        if(pointers[value]!=value)
            return find(pointers, pointers[value]);;
        return value;
    }

    public void union(int[] pointers, int x, int y){
        int x_set_parent = find(pointers, x);
        int y_set_parent = find(pointers, y);
        pointers[y_set_parent] = x_set_parent;
    }

    public static void printEdges(ArrayList<Edge> edgeList){
        for (int i = 0; i <edgeList.size() ; i++) {
            Edge edge = edgeList.get(i);
            System.out.println(i + "| from: " + edge.from.value + " to: " + edge.to.value + "; weight: " + edge.weight + "| ");
        }
    }


    public static void main(String[] args){

        MatrixGraph amg = new MatrixGraph(5);

        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        amg.addVertex(v0);
        amg.addVertex(v1);
        amg.addVertex(v2);
        amg.addVertex(v3);
        amg.addVertex(v4);


        amg.addEdge(v0,v1, 1);
        amg.addEdge(v0,v4, 3);
        amg.addEdge(v2,v1, 2);
        amg.addEdge(v3,v4, 4);
        amg.addEdge(v3,v2,1);
        amg.addEdge(v2,v3,3);
        amg.addEdge(v4,v2,2);


        amg.updateAdjacency();
        amg.updateIncidence();

        System.out.println("adjacency matrix directed:");
        amg.printMatrix(adjacencyMatrix);
        System.out.println("adjacency matrix undirected:");
        amg.printMatrix(adjacencyUndirectedMatrix);
        System.out.println("incidence matrix:");
        amg.printMatrix(incidenceMatrix);

        System.out.println("MST with Kruskal's algorithm:");
        amg.mstKruskal();


    }

}
