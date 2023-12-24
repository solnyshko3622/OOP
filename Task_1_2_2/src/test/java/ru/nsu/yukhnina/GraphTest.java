package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.util.Scanner;
import org.junit.jupiter.api.Test;




class GraphTest {

    @Test
    void vertexNotInGraphAdm() {
        AdjacencyMatrix<Integer> adM = new AdjacencyMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertNull(adM.getVert(5));
    }

    @Test
    void vertexInGraphAdm() {
        AdjacencyMatrix<Integer> adM = new AdjacencyMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertEquals(1, adM.getVert(1).getVert());
        assertNotEquals(null, adM.getVert(2));
        assertEquals(2, adM.getVert(2).getVert());
        adM.setVert(4, 5);
        assertEquals(5, adM.getVert(5).getVert());
        assertNull(adM.getVert(4));
    }

    @Test
    void setNullVertexAdm() {
        AdjacencyMatrix<String> adM = new AdjacencyMatrix<String>();
        adM.addVert("I");
        adM.addVert("want");
        adM.addVert("eat");
        adM.addVert("a");
        adM.addVert("lot");
        adM.setVert("study", "free time");
        assertNull(adM.getVert("free time"));
    }

    @Test
    void addEdgeAdm() {
        AdjacencyMatrix<String> adM = new AdjacencyMatrix<String>();
        adM.addVert("dormitoty");
        adM.addVert("NSU");
        adM.addEdge("dormitoty", "NSU", "grust'");
        assertEquals("grust'", adM.getEdge("dormitoty", "NSU").getWeight());
    }

    @Test
    void setEdgeAdm() {
        AdjacencyMatrix<String> adM = new AdjacencyMatrix<String>();
        adM.addVert("dormitoty");
        adM.addVert("NSU");
        adM.addEdge("dormitoty", "NSU", "grust'");
        adM.setEdge("dormitoty", "NSU", "Wuhu Java");
        assertEquals("Wuhu Java", adM.getEdge("dormitoty", "NSU").getWeight());
    }

    @Test
    void setEdgeWithoutVertAdm() {
        AdjacencyMatrix<String> adM = new AdjacencyMatrix<String>();
        adM.setEdge("dormitoty", "NSU", "Wuhu Java");
        assertEquals("Wuhu Java", adM.getEdge("dormitoty", "NSU").getWeight());
    }

    @Test
    void deleteVertAdm() {
        AdjacencyMatrix<Integer> adM = new AdjacencyMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertEquals(1, adM.getVert(1).getVert());
        adM.deleteVert(1);
        adM.deleteVert(2);
        adM.deleteVert(3);
        adM.deleteVert(4);
        assertNull(adM.getVert(1));
        assertNull(adM.getVert(2));
        assertNull(adM.getVert(3));
        assertNull(adM.getVert(4));
        assertNull(adM.getVert(35));
    }

    @Test
    void vertexNullEdgeAdm() {
        AdjacencyMatrix<Integer> adM = new AdjacencyMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        adM.deleteEdge(1, 5);
        assertNull(adM.getEdge(1, 5));
        assertNotEquals(null, adM.getVert(2));
        assertEquals(2, adM.getVert(2).getVert());
        adM.setVert(4, 5);
        assertEquals(5, adM.getVert(5).getVert());
        assertNull(adM.getVert(4));
    }

    @Test
    void notNullEdgeAdm() {
        AdjacencyMatrix<Integer> adM = new AdjacencyMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        adM.addEdge(1, 2, 4);
        adM.addEdge(2, 7, 15);
        assertNotEquals(null, adM.getEdge(1, 2));
        adM.deleteEdge(1, 2);
        assertEquals(15, adM.getEdge(2, 7).getWeight());
        assertNull(adM.getEdge(1, 2));
    }

    @Test
    void vertexNotInGraphAdl() {
        AdjacencyLists<Integer> adL = new AdjacencyLists<>();
        adL.addVert(1);
        adL.addVert(2);
        adL.addVert(3);
        adL.addVert(4);
        assertNull(adL.getVert(5));
    }

    @Test
    void vertexInGraphAdl() {
        AdjacencyLists<Integer> adM = new AdjacencyLists<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertEquals(1, adM.getVert(1).getVert());
        assertNotEquals(null, adM.getVert(2));
        assertEquals(2, adM.getVert(2).getVert());
        adM.setVert(4, 5);
        assertEquals(5, adM.getVert(5).getVert());
        assertNull(adM.getVert(4));
    }

    @Test
    void setNullVertexAdl() {
        AdjacencyLists<String> adM = new AdjacencyLists<String>();
        adM.addVert("I");
        adM.addVert("want");
        adM.addVert("eat");
        adM.addVert("a");
        adM.addVert("lot");
        adM.setVert("study", "free time");
        assertNull(adM.getVert("free time"));
    }

    @Test
    void addEdgeAdl() {
        AdjacencyLists<String> adL = new AdjacencyLists<String>();
        adL.addVert("dormitoty");
        adL.addVert("NSU");
        adL.addEdge("dormitoty", "NSU", "grust'");
        assertEquals("grust'", adL.getEdge("dormitoty", "NSU").getWeight());
    }

    @Test
    void setEdgeAdl() {
        AdjacencyLists<String> adM = new AdjacencyLists<String>();
        adM.addVert("dormitoty");
        adM.addVert("NSU");
        adM.addEdge("dormitoty", "NSU", "grust'");
        adM.setEdge("dormitoty", "NSU", "Wuhu Java");
        assertEquals("Wuhu Java", adM.getEdge("dormitoty", "NSU").getWeight());
    }

    @Test
    void setNullEdgeAdl() {
        AdjacencyLists<String> adM = new AdjacencyLists<String>();
        adM.setEdge("dormitoty", "NSU", "Wuhu Java");
        assertEquals("Wuhu Java", adM.getEdge("dormitoty", "NSU").getWeight());
    }

    @Test
    void deleteVertAdl() {
        AdjacencyLists<Integer> adM = new AdjacencyLists<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertEquals(1, adM.getVert(1).getVert());
        adM.deleteVert(1);
        adM.deleteVert(2);
        adM.deleteVert(3);
        adM.deleteVert(4);
        assertNull(adM.getVert(1));
        assertNull(adM.getVert(2));
        assertNull(adM.getVert(3));
        assertNull(adM.getVert(4));
        assertNull(adM.getVert(35));
    }


    @Test
    void vertexNotInGraphIdm() {
        IncidentMatrix<Integer> adM = new IncidentMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertNull(adM.getVert(5));
    }

    @Test
    void vertexInGraphIdm() {
        IncidentMatrix<Integer> adM = new IncidentMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertEquals(1, adM.getVert(1).getVert());
        assertNotEquals(null, adM.getVert(2));
        assertEquals(2, adM.getVert(2).getVert());
        adM.setVert(4, 5);
        assertEquals(5, adM.getVert(5).getVert());
        assertNull(adM.getVert(4));
    }

    @Test
    void setNullVertexIdm() {
        IncidentMatrix<String> adM = new IncidentMatrix<String>();
        adM.addVert("I");
        adM.addVert("want");
        adM.addVert("eat");
        adM.addVert("a");
        adM.addVert("lot");
        adM.setVert("study", "free time");
        assertNull(adM.getVert("free time"));
    }

    @Test
    void addEdgeIdm() {
        IncidentMatrix<String> adM = new IncidentMatrix<String>();
        adM.addVert("dormitoty");
        adM.addVert("NSU");
        adM.addEdge("dormitoty", "NSU", "grust'");
        assertEquals("grust'", adM.getEdge("dormitoty", "NSU").getWeight());
    }

    @Test
    void setEdgeIdm() {
        IncidentMatrix<String> adM = new IncidentMatrix<String>();
        adM.addVert("dormitoty");
        adM.addVert("NSU");
        adM.addEdge("dormitoty", "NSU", "grust'");
        adM.setEdge("dormitoty", "NSU", "Wuhu Java");
        assertEquals("Wuhu Java", adM.getEdge("dormitoty", "NSU").getWeight());
    }

    @Test
    void setNullIdm() {
        IncidentMatrix<String> adM = new IncidentMatrix<String>();
        adM.setEdge("dormitoty", "NSU", "grust'");
        assertEquals("grust'", adM.getEdge("dormitoty", "NSU").getWeight());
    }

    @Test
    void deleteVertIdm() {
        IncidentMatrix<Integer> adM = new IncidentMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertEquals(1, adM.getVert(1).getVert());
        adM.deleteVert(1);
        adM.deleteVert(2);
        adM.deleteVert(3);
        adM.deleteVert(4);
        adM.deleteVert(17);
        assertNull(adM.getVert(1));
        assertNull(adM.getVert(2));
        assertNull(adM.getVert(3));
        assertNull(adM.getVert(4));
        assertNull(adM.getVert(35));
    }

    @Test
    void findWay() {
        AdjacencyLists<Integer> adL = new AdjacencyLists<Integer>();
        adL.addEdge(1, 2, 3);
        adL.addEdge(2, 3, 4);
        adL.addEdge(1, 3, 10);
        FindWay<Integer> sort = new FindWay<>(1, adL);
        assertEquals(7, sort.getWay(adL, 3));
    }

    @Test
    void findWay2() {
        IncidentMatrix<Integer> adL = new IncidentMatrix<>();
        try {
            File file = new File("src/test/java/ru/nsu/yukhnina/input.txt");
            Scanner scanner = new Scanner(file);
            int vert1;
            int vert2;
            int weight;
            int number1 = scanner.nextInt();
            for (int i = 0; i < number1; i++) {
                vert1 = scanner.nextInt();
                vert2 = scanner.nextInt();
                weight = scanner.nextInt();
                adL.addEdge(vert1, vert2, weight);
            }

            FindWay<Integer> sort = new FindWay<>(1, adL);
            assertEquals(7, sort.getWay(adL, 3));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findWay3() {
        AdjacencyMatrix<Integer> adL = new AdjacencyMatrix<>();
        adL.addEdge(1, 2, 3);
        adL.addEdge(2, 3, 4);
        adL.addEdge(1, 3, 10);
        FindWay<Integer> sort = new FindWay<>(1, adL);
        assertEquals(7, sort.getWay(adL, 3));
    }

    @Test
    void bellmanFord() {
        AdjacencyMatrix<Integer> adL = new AdjacencyMatrix<>();
        adL.addEdge(1, 2, 3);
        adL.addEdge(2, 3, 4);
        adL.addEdge(1, 3, 10);
        FindWay<Integer> sort = new FindWay<>(1, adL);
        sort.bellmanFord(adL, 1, 3);
        assertEquals(7, sort.bellmanFord(adL, 1, 3));
    }

    @Test
    void bellmanFordNegativeCycle() {
        AdjacencyMatrix<Integer> adL = new AdjacencyMatrix<>();
        adL.addEdge(1, 2, 3);
        adL.addEdge(2, 3, 4);
        adL.addEdge(1, 3, 10);
        adL.addEdge(3, 4, -8);
        adL.addEdge(4, 1, -10);
        FindWay<Integer> sort = new FindWay<>(1, adL);
        sort.bellmanFord(adL, 1, 3);
        assertEquals(-1, sort.bellmanFord(adL, 1, 3));
    }


    @Test
    void bellmanFordNegativeEdge() {
        AdjacencyMatrix<Integer> adL = new AdjacencyMatrix<>();
        adL.addEdge(1, 2, -2);
        adL.addEdge(2, 3, 5);
        adL.addEdge(1, 3, 7);
        FindWay<Integer> sort = new FindWay<>(1, adL);
        sort.bellmanFord(adL, 1, 3);
        assertEquals(3, sort.bellmanFord(adL, 1, 3));
    }

}