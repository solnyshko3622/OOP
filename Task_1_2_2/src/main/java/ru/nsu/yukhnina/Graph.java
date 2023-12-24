package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.List;

/**
 * Интерфейс со всеми реализоваными методами для работы с графафми.
 */
public interface Graph<G> {
    void addVert(G newVert);

    void deleteVert(G vert);

    void addEdge(G vert1, G vert2, G edge);

    void deleteEdge(G vert1, G vert2);

    Edge<G> getEdge(G vert1, G vert2);

    void setEdge(G vert1, G vert2, G newEdge);

    Vertex<G> getVert(G vert);

    void setVert(G oldVert, G newVert);

    ArrayList<Vertex<G>> getVertices();

    Integer findId(G vert);

    int getCountEdge();
}


