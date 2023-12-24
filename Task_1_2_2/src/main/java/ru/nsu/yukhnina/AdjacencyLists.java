package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.List;

/**
 * Представление графа в виде списка смежности.
 */
public class AdjacencyLists<G> implements Graph<G> {
    //будем считать , что список смежности это список рёбер для каждой вершиныб
    //буду хранить массив вершин, чтобы по ним получать индекс необходимого подмассива

    private ArrayList<ArrayList<Edge<G>>> edgesName;
    private int vertexCount;
    private int edgesCount;
    private ArrayList<Vertex<G>> vertices;

    /**
     * Список смежности, где по индексу вершины лежит array list смежных ей вершин.
     */
    public AdjacencyLists() {
        edgesName = new ArrayList<ArrayList<Edge<G>>>();
        vertexCount = 0;
        vertices = new ArrayList<Vertex<G>>();
        edgesCount = 0;
    }

    public int getCountEdge() {
        return edgesCount;
    }

    /**
     * Add new vert in graph.
     */
    public void addVert(G newVert) {
        //добавили новую вершину в массив вершин, увеличили количество,
        vertices.add(new Vertex<G>(newVert));
        vertexCount++;
        edgesName.add(new ArrayList<Edge<G>>());
    }

    /**
     * Get vertices object.
     */
    public Vertex<G> getVert(G vert) {
        for (int i = 0; i < vertexCount; i++) {
            if (vert.equals(this.vertices.get(i).getVert())) {
                return this.vertices.get(i);
            }
        }
        //если нет такой вершины возвращаю null
        return null;
    }

    /**
     * Change vertice name.
     */
    public void setVert(G oldVert, G newVert) {
        for (int i = 0; i < vertexCount; i++) {
            if (oldVert.equals(this.vertices.get(i).getVert())) {
                this.vertices.get(i).setVert(newVert);
            }
        }
    }

    /**
     * If we know vert nameб delite it in graph.
     */
    public void deleteVert(G vert) {
        //ищу индекс вершины
        int indexVert = -1;
        for (int i = 0; i < vertexCount; i++) {
            if (vert.equals(this.vertices.get(i).getVert())) {
                indexVert = i;
                break;
            }
        }
        //если такой вершины нет, то удалять нечего
        if (indexVert < 0) {
            return;
        }
        //удаляем всеинцеденты=ные рёбра
        vertices.remove(indexVert);
        edgesName.remove(indexVert);
        this.vertexCount--;
    }

    /**
     * Add edge in graph.
     */
    public void addEdge(G vert1, G vert2, G newEdge) {
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < vertexCount; i++) {
            if (vert1.equals(this.vertices.get(i).getVert())) {
                indexVert1 = i;
            }
            if (vert2.equals(this.vertices.get(i).getVert())) {
                indexVert2 = i;
            }
        }
        if (indexVert1 == - 1) {
            addVert(vert1);
            indexVert1 = this.vertexCount - 1;
        }
        edgesName.get(indexVert1).add(new Edge<>(vert1, vert2, newEdge));
        if (indexVert2 == - 1) {
            addVert(vert2);
        }
        edgesCount++;
    }

    /**
     * Получение объекта ребра.
     */
    public Edge<G> getEdge(G vert1, G vert2) {
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < vertexCount; i++) {
            if (vert1.equals(this.vertices.get(i).getVert())) {
                indexVert1 = i;
            }
            if (vert2.equals(this.vertices.get(i).getVert())) {
                indexVert2 = i;
            }
        }
        if (indexVert1 == - 1) {
            return null;
        }
        if (indexVert2 == - 1) {
            return null;
        }
        for (Edge<G> edge : edgesName.get(indexVert1)) {
            if (edge.getVertTo().equals(vert2)) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Method for change edges weight.
     */
    public void setEdge(G vert1, G vert2, G newEdge) {
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < vertexCount; i++) {
            if (vert1.equals(this.vertices.get(i).getVert())) {
                indexVert1 = i;
            }
            if (vert2.equals(this.vertices.get(i).getVert())) {
                indexVert2 = i;
            }
        }
        if (indexVert1 == - 1) {
            addVert(vert1);
            indexVert1 = this.vertexCount;
        }
        if (indexVert2 == - 1) {
            addVert(vert2);
        }
        //если всё таки нашли это ребро
        for (Edge<G> edge : edgesName.get(indexVert1)) {
            if (edge.getVertFrom().equals(vert1) && edge.getVertTo().equals(vert2)) {
                edge.setWeight(newEdge);
                return;
            }
        }
        //если не нашли такое ребро, создаём
        addEdge(vert1, vert2, newEdge);
        edgesCount++;
    }

    /**
     * Метод для удаления ребра из графа.
     */
    public void deleteEdge(G vert1, G vert2) {
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < vertexCount; i++) {
            if (vert1.equals(this.vertices.get(i))) {
                indexVert1 = i;
            }
            if (vert2.equals(this.vertices.get(i))) {
                indexVert2 = i;
            }
        }
        if (indexVert1 == - 1) {
            return;
        }
        if (indexVert2 == - 1) {
            return;
        }
        for (Edge<G> edge : edgesName.get(indexVert1)) {
            if (edge.getVertFrom().equals(vert1) && edge.getVertTo().equals(vert2)) {
                edgesName.get(indexVert1).remove(edge);
                return;
            }
        }
        edgesCount--;
    }

    /**
     * Find vert index in list.
     */
    public Integer findId(G vert) {
        for (int i = 0; i < vertexCount; i++) {
            if (vert.equals(this.vertices.get(i).getVert())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Return all graph veretices list.
     */
    public ArrayList<Vertex<G>> getVertices() {
        return vertices;
    }
}
