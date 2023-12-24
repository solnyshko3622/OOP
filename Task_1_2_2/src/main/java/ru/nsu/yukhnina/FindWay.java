package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * класс, использующий граф для поиска путей между вершинами.
 */
public class FindWay<G> {
    ArrayList<Integer> ways;
    ArrayList<Integer> used;

    /**
     * Создаю список кратчайших расстояний до вершин от необходимой,
     * расстояние до начальной вершины 0,
     * и список флагов использовалась ли вершина ранее.
     */
    public FindWay(G vert, Graph<G> graph) {
        ways = new ArrayList<>();
        used = new ArrayList<>();

        for (int i = 0; i < graph.getVertices().size(); i++) {
            ways.add(Integer.MAX_VALUE);
            used.add(0);
        }

        ways.set(graph.findId(vert), 0);
        dijkstra(graph);
    }

    /**
     * сам алгоритм Дейкстры.
     */
    private void dijkstra(Graph<G> graph) {
        Integer v;

        for (int i = 0; i < graph.getVertices().size(); i++) {
            v = null;

            for (int j = 0; j < graph.getVertices().size(); j++) {
                if (used.get(j) == 0 && (v == null || ways.get(j) < ways.get(v))) {
                    v = j;
                }
            }

            if (ways.get(v) == Integer.MAX_VALUE) {
                break;
            }

            used.set(v, 1);
            Vertex<G> vert = graph.getVertices().get(v);
            for (Vertex<G> currVert : graph.getVertices()) {
                Edge<G> edge = graph.getEdge(vert.getVert(), currVert.getVert());

                if (edge != null && ways.get(v)
                        + (Integer) edge.getWeight() < ways.get(graph.findId(currVert.getVert()))) {
                    ways.set(graph.findId(currVert.getVert()), ways.get(v)
                            + (Integer) edge.getWeight());
                }
            }
        }
    }

    /**
     * Bellman-Ford alghoritm to find shortly way.
     */
    public int bellmanFord(Graph<G> graph, G vert, G vert2) {
        int src = graph.findId(vert);
        int verticesCount = graph.getVertices().size();
        int[] dist = new int[verticesCount];

        // Step 1: Initialize distances from src to all
        // other vertices as INFINITE
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Step 2: Relax all edges |verticesCount| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |verticesCount| - 1 edges
        int weight;
        for (int i = 1; i < verticesCount; ++i) {
            for (int j = 0; j < verticesCount; ++j) {
                for (int k = 0; k < verticesCount; k++) {
                    Edge<G> edge = graph.getEdge(graph.getVertices().get(j).getVert(),
                            graph.getVertices().get(k).getVert());
                    if (edge != null) {
                        weight = (int) edge.getWeight();
                        if (dist[j] != Integer.MAX_VALUE && dist[j] + weight < dist[k]) {
                            dist[k] = dist[j] + weight;
                        }
                    }
                }
            }
        }

        for (int j = 0; j < verticesCount; j++) {
            for (int k = 0; k < verticesCount; k++) {
                Edge<G> edge = graph.getEdge(graph.getVertices().get(j).getVert(),
                        graph.getVertices().get(k).getVert());
                if (edge != null) {
                    weight = (int) edge.getWeight();
                    if (dist[j] != Integer.MAX_VALUE && dist[j] + weight < dist[k]) {
                        dist[k] = dist[j] + weight;
                        System.out.println("negative cycle");
                        return -1;
                    }
                }
            }
        }
        return dist[graph.findId(vert2)];
    }

    public Integer getWay(Graph<G> g, G vert2) {
        return ways.get(g.findId(vert2));
    }
}
