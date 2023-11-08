package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;



class RabinKarpAlgorithmTest {

    @Test
    void shortFile() throws Exception {
        ArrayList<Integer> resultActual = RabinKarpAlgorithm.find("input.txt", "ed");
        int[] resultException = {6, 15, 20, 31};
        for (int i = 0; i < 4; i++) {
            assertEquals(resultException[i], resultActual.get(i));
        }
    }



    //Тест задан такими данными:
    //    f = open('text.txt', 'w')
    //    f.write("find")
    //    for i in range(10000):
    //       f.write('aaaaaa')
    //    f.write("find")
    //    for i in range(10000):
    //       f.write('aaaaaa')
    //    f.write("find")
    @Test
    void mediumFile() throws Exception {
        ArrayList<Integer> resultActual = RabinKarpAlgorithm.find("text.txt", "find");
        int[] resultException = {1, 60005, 120009};
        for (int i = 0; i < 3; i++) {
            assertEquals(resultException[i], resultActual.get(i));
        }
    }

    @Test
    void bigFile() throws Exception {
        String fffile = "src/test/java/ru/nsu/yukhnina/testBigFile.txt";
        RandomAccessFile f = new RandomAccessFile(fffile, "rw");
        f.setLength(15L * 1024 * 1024 * 1024);
        try (FileWriter writer = new FileWriter(fffile, false)) {
            writer.write("text");
            for (int j = 0; j < 9; j++) {
                for (int i = 0; i < 9; i++) {
                    writer.write("Hello Gold! Hi honey! I'm so tried(((");
                }
            }
            writer.write("text");
            writer.write("text");
            writer.write("text");
            writer.flush();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        ArrayList<Integer> resultActual = RabinKarpAlgorithm.find(fffile, "text");
        for (int i = 0; i < 3; i++) {
            assertEquals(4, resultActual.size());
        }
        (new File(fffile)).delete();
    }

    //f = open('text3.txt', 'w')
    //for i in range(100): f.write("repeat")
    //f.close()


    @Test
    void allWordsToFind() throws Exception {
        ArrayList<Integer> resultActual = RabinKarpAlgorithm.find("text3.txt", "repeat");
        assertEquals(100, resultActual.size());

    }

    @Test
    void notInFile() throws Exception {
        ArrayList<Integer> resultActual = RabinKarpAlgorithm.find("text4.txt", "fruits");
        assertEquals(0, resultActual.size());

    }

}