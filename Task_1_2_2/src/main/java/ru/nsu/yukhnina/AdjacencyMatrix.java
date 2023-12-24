package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.List;

/**
 * Представление графа в виде матрицы смежности.
 */
public class AdjacencyMatrix<G> implements Graph<G> {
    private List<ArrayList<Edge<G>>> matrix;
    private List<ArrayList<Integer>> warshall;
    private ArrayList<Vertex<G>> verticesName;
    private int countVert;
    private int countEdge;

    /**
     * On MATRIX(i, j) фыves null or object edge.
     */
    public AdjacencyMatrix() {
        matrix = new ArrayList<ArrayList<Edge<G>>>();
        verticesName = new ArrayList<Vertex<G>>();
        countVert = 0;
        warshall = new ArrayList<ArrayList<Integer>>();
        countEdge = 0;
    }

    public int getCountEdge() {
        return countEdge;
    }

    public int getCountVert() {
        return countVert;
    }

    /**
     * Метод для добавления вершины в граф.
     */
    //увеличиваем количество вершин на 1, закидываем её в массив вершин
    public void addVert(G vert1) {
        this.verticesName.add(new Vertex<>(vert1));
        matrix.add(new ArrayList<Edge<G>>());
        //заполняю матрицу нулевыми значениями, чтобы ыбло к чему обращаться
        for (int i = 0; i < this.countVert + 1; i++) {
            this.matrix.get(i).add(null);
            this.matrix.get(countVert).add(null);
        }
        countVert++;
    }

    /**
     * Метод для удаления вершины из графа.
     */
    public void deleteVert(G vert1) {
        int indexVert1 = 0;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(verticesName.get(i).getVert())) {
                indexVert1 = i;
            }
        }
        //удаляю у всех вершин информацию об удаляемой
        for (int i = 0; i < countVert; i++) {
            matrix.get(i).remove(indexVert1);
        }
        //удаляю вершину
        matrix.remove(indexVert1);
        verticesName.remove(indexVert1);
        //уменьшаю количество вершин
        countVert--;
    }

    /**
     * Метод для добавления ребра в граф.
     */
    //возможно edge нужно будет поменять на инт
    public void addEdge(G vert1, G vert2, G newEdge) {
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i).getVert())) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i).getVert())) {
                indexVert2 = i;
            }
        }
        if (indexVert1 == - 1) {
            addVert(vert1);
            indexVert1 = this.countVert - 1;
        }
        if (indexVert2 == -1) {
            addVert(vert2);
            indexVert2 = this.countVert - 1;
        }
        matrix.get(indexVert1).set(indexVert2, new Edge<G>(vert1, vert2, newEdge));
        countEdge++;
    }

    /**
     * Метод для удаления ребра.
     */
    public void deleteEdge(G vert1, G vert2) {
        //удалить ребро из каждой вершины
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i).getVert())) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i).getVert())) {
                indexVert2 = i;
            }
        }
        //если ребра не существует, то и удалять нечего
        if (indexVert1 == - 1) {
            return;
        }
        if (indexVert2 == -1) {
            return;
        }
        //удаление
        matrix.get(indexVert1).set(indexVert2, null);
        countEdge--;
    }

    /**
     * Метод для получения объекта ребра.
     */
    //буду искать в матрице это ребро и потом возвращать его
    public Edge<G> getEdge(G vert1, G vert2) {
        //вынести поиск индекса в отдельную функцию, но к жёсткому дедлайну
        int indexVert1 = - 1;
        int indexVert2 = - 1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i).getVert())) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i).getVert())) {
                indexVert2 = i;
            }
        }
        //если ребра не существует
        if (indexVert1 == - 1) {
            return null;
        }
        if (indexVert2 == -1) {
            return null;
        }
        return matrix.get(indexVert1).get(indexVert2);
    }

    /**
     * Зная вершины соединяющие ребро, изменяем его вес.
     */
    //считаю, что изменение ребра это изменение его значения
    public void setEdge(G vert1, G vert2, G newEdge) {
        addEdge(vert1, vert2, newEdge);
    }

    /**
     * Get vertice object if vertice not null.
     */
    //получаю имя вершины, вывожу все инцедентные рёбра и и нцедентные вершины
    //можно чтобы в тестах не сравнивать с налом весь объект создать
    // где-нибудь вершину с нулевыми полями
    public Vertex<G> getVert(G vert) {
        for (int i = 0; i < countVert; i++) {
            if (vert.equals(this.verticesName.get(i).getVert())) {
                return this.verticesName.get(i);
            }
        }
        //если такой вершины нет null
        return null;
    }

    /**
     * Изменение названия вершины.
     */
    //считаю, что изменение вершины это изменение её имени
    public void setVert(G oldVert, G newVert) {
        for (int i = 0; i < countVert; i++) {
            if (oldVert.equals(this.verticesName.get(i).getVert())) {
                this.verticesName.get(i).setVert(newVert);
            }
        }
    }

    /**
     * Find by name index vert in list.
     */
    public Integer findId(G vert) {
        for (int i = 0; i < countVert; i++) {
            if (vert.equals(this.verticesName.get(i).getVert())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Return lists contains all graph vertices.
     */
    public ArrayList<Vertex<G>> getVertices() {
        return verticesName;
    }
}

