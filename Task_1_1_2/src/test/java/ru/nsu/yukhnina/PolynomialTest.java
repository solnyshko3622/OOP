package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PolynomialTest {

    @Test
    void addWithDifLen() {
        Polynomial p1 = new Polynomial(new float[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new float[] {3, 2, 8});
        Polynomial result = new Polynomial(new float[] {7, 5, 14, 7});
        assertEquals(result, p1.add(p2));
    }

    @Test
    void addFloatWithEqLen() {
        Polynomial p3 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p4 = new Polynomial(new float[] {3, 2, 8, 10.5F});
        Polynomial result = new Polynomial(new float[] {7, 5.2F, 14, 17.5F});
        assertEquals(result, p3.add(p4));
    }

    @Test
    void differenceWithEqLen() {
        Polynomial p5 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p6 = new Polynomial(new float[] {3, 2, 8, 10.5F});
        Polynomial result = new Polynomial(new float[] {1, 1.2F, -2, -3.5F});
        assertEquals(result, p5.difference(p6));
    }

    @Test
    void differenceWithDifLen() {
        Polynomial p7 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p8 = new Polynomial(new float[] {3, 2, 8});
        Polynomial result = new Polynomial(new float[] {1, 1.2F, -2, 7F});
        assertEquals(result, p7.difference(p8));
    }

    @Test
    void differenceWithYourself() {
        Polynomial p9 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial result = new Polynomial(new float[] {0});
        assertEquals(result, p9.difference(p9));
    }

    @Test
    void differenceWithDifLen2() {
        Polynomial p10 = new Polynomial(new float[] {4, 3.2F, 6});
        Polynomial p11 = new Polynomial(new float[] {3, 2, 8, 10});
        Polynomial result = new Polynomial(new float[] {1, 1.2F, -2, -10F});
        assertEquals(result, p10.difference(p11));
    }

    @Test
    void differenceWithEmptySecond() {
        Polynomial p12 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p13 = new Polynomial(new float[] {});
        Polynomial result = new Polynomial(new float[] {4, 3.2F, 6, 7});
        assertEquals(result, p12.difference(p13));
    }

    @Test
    void differenceWithEmptyFirst() {
        Polynomial p14 = new Polynomial(new float[] {});
        Polynomial p15 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial result = new Polynomial(new float[] {-4, -3.2F, -6, -7});
        assertEquals(result, p14.difference(p15));
    }

    @Test
    void multAndTest() {
        Polynomial p16 = new Polynomial(new float[] {1, 0, 4, 5, 0, 0, 0, 1});
        Polynomial p17 = new Polynomial(new float[] {0, 3, 1});
        Polynomial result = new Polynomial(new float[] {0, 3, 1, 12, 19, 5, 0, 0, 3, 1});
        assertEquals(result, p16.mult(p17));
    }

    @Test
    void calculationAtPoint1() {
        Polynomial p18 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        assertEquals(p18.calculationAtPoint(1), 156);
    }

    @Test
    void calculationAtPoint2() {
        Polynomial p19 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        assertEquals(p19.calculationAtPoint(2), 1424.0);
    }

    @Test
    void differential() {
        Polynomial p20 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        Polynomial result1 = new Polynomial(new float[] {6, 48, 108, 200, 360, 0, 0});
        Polynomial result2 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        assertEquals(result1, p20.differential(2));
        //проверка что изначальное не меняется
        assertEquals(result2, p20);
    }

    @Test
    void testEqualsFalse() {
        Polynomial p21 = new Polynomial(new float[] {1, 0, 4, 5, 0, 0, 0, 1});
        Polynomial p22 = new Polynomial(new float[] {0, 3, 1});
        assertEquals(p21.equals(p22), false);
    }

    @Test
    void testEqualsEmpty() {
        Polynomial p23 = new Polynomial(new float[] {});
        assertEquals(p23.equals(new Polynomial(new float[] {})), true);
    }

    @Test
    void testEqualsTrue() {
        Polynomial p24 = new Polynomial(new float[] {1, 2, 3, 4});
        Polynomial p25 = new Polynomial(new float[] {1, 2, 3, 4});
        assertEquals(p24.equals(p25), true);
    }

    @Test
    void  testsFromTask() {
        Polynomial p26 = new Polynomial(new float[] {4, 3, 6, 7});
        Polynomial p27 = new Polynomial(new float[] {3, 2, 8});
        assertEquals("7.0x^3 + 6.0x^2 + 19.0x + 6.0", p26.add(p27.differential(1)).toString());
        assertEquals(3510, p26.mult(p27).calculationAtPoint(2));
    }

    @Test
    void printOneElem() {
        Polynomial p20 = new Polynomial(new float[] {1});
        assertEquals("1.0", p20.toString());
    }

    @Test
    void printEmptyPolynom() {
        Polynomial p21 = new Polynomial(new float[] {});
        assertEquals("0.0", p21.toString());
    }

    @Test
    void differential0() {
        Polynomial p22 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        Polynomial result = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        assertEquals(result, p22.differential(0));
    }

    @Test
    void differential1() {
        Polynomial p23 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        Polynomial result = new Polynomial(new float[] {2, 6, 24, 36, 50, 72});
        assertEquals(result, p23.differential(1));
    }

    @Test
    void differential2() {
        Polynomial p24 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        Polynomial result = new Polynomial(new float[] {6, 48, 108, 200, 360});
        //2 дифференциал то же самое, что и продифиренцировать функцию один раз,
        // а потом результат ещё раз
        assertEquals(p24.differential(1).differential(1).toString(),
                p24.differential(2).toString());
        //ну и проверка что и то и то выводит что надо, а не одинаково косячат
        assertEquals(result, p24.differential(2));
    }

    @Test
    void differential3() {
        Polynomial p24 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        Polynomial result = new Polynomial(new float[] {48, 216, 600, 1440});
        assertEquals(result, p24.differential(3));
    }

    @Test
    void differential4() {
        Polynomial p25 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        Polynomial result = new Polynomial(new float[] {216, 1200, 4320});
        assertEquals(result, p25.differential(4));
    }

    @Test
    void differential5() {
        Polynomial p25 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        Polynomial result = new Polynomial(new float[] {1200, 8640});
        assertEquals(result, p25.differential(5));
    }

    @Test
    void differential6() {
        Polynomial p25 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        Polynomial result = new Polynomial(new float[] {8640});
        assertEquals(result, p25.differential(6));
    }

    @Test
    void differential7AndEmptyPrint() {
        Polynomial p25 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        Polynomial result = new Polynomial(new float[] {});
        Polynomial result1 = new Polynomial(new float[] {0});
        Polynomial result2 = new Polynomial(new float[] {0, 0, 0, 0, 0, 0, 0});
        assertEquals(result, p25.differential(7));
        assertEquals(result1, p25.differential(7));
        assertEquals(result2, p25.differential(7));
    }

    @Test
    void differential8AndEmptyPrint() {
        Polynomial p25 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        Polynomial result = new Polynomial(new float[] {});
        Polynomial result1 = new Polynomial(new float[] {0});
        Polynomial result2 = new Polynomial(new float[] {0, 0, 0, 0, 0, 0, 0});
        assertEquals(result, p25.differential(8));
        assertEquals(result1, p25.differential(8));
        assertEquals(result2, p25.differential(8));
    }

    @Test
    void plusWithNegativeItself() {
        Polynomial p1 = new Polynomial(new float[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new float[] {-4, -3, -6, -7});
        Polynomial result = new Polynomial(new float[] {0, 0, 0, 0});
        assertEquals(result, p1.add(p2));
    }
}