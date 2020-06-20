public class DijkstraAdjacencyMatrix {

    private static class Graph{
        private int verticesAmount;
        private int[][] adjacencyMatrix;

        public Graph(int verticesAmount) {
            this.verticesAmount = verticesAmount;
            adjacencyMatrix = new int[verticesAmount][verticesAmount];
        }

        public void addEdge(int from, int to, int weight) {
            adjacencyMatrix[from][to] = weight;
            adjacencyMatrix[to][from] = weight;
        }


        private int getMinDistance(boolean[] shortestPathTree, int[] rank){
            int minRank = Integer.MAX_VALUE;
            int vertex = -1;
            for (int i = 0; i < verticesAmount; i++) {
                if(!shortestPathTree[i] && minRank > rank[i]){
                    minRank = rank[i];
                    vertex = i;
                }
            }
            return vertex;
        }

        public void dijkstra(int source){
            boolean[] shortestPath = new boolean[verticesAmount];
            int[] distance = new int[verticesAmount];
            int INFINITY = Integer.MAX_VALUE;

            for (int i = 0; i < verticesAmount; i++)
                distance[i] = INFINITY;
            distance[source] = 0;

            for (int i = 0; i < verticesAmount; i++) {
                int vert_u = getMinDistance(shortestPath, distance);
                shortestPath[vert_u] = true;

                for (int vert2 = 0; vert2 < verticesAmount; vert2++) {
                    if(adjacencyMatrix[vert_u][vert2] > 0){
                        if(!shortestPath[vert2] && adjacencyMatrix[vert_u][vert2]!=INFINITY){
                            int newRank =  adjacencyMatrix[vert_u][vert2] + distance[vert_u];
                            if(newRank < distance[vert2])
                                distance[vert2] = newRank;
                        }
                    }
                }
            }
            for (int i = 0; i < verticesAmount; i++)
                System.out.println("from " + source + " to " +   + i + " : " + distance[i]);
        }
    }

    public static void main(String[] args) {

        Graph graph = new Graph(6);

        graph.addEdge(0, 1, 7);
        graph.addEdge(0, 2, 9);
        graph.addEdge(0, 5, 14);
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 11);
        graph.addEdge(2, 5, 2);
        graph.addEdge(3, 4, 11);
        graph.addEdge(4, 5, 9);


        graph.dijkstra(0);
    }
}