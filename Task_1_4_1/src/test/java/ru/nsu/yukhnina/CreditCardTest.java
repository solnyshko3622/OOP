package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CreditCardTest {
    @Test
     void testSetMarksAndAvg() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(1, "A", CreditCard.Mark.GOOD);
        testAdd.setMark(2, "A", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "C", CreditCard.Mark.EXCELLENT);
        assertEquals(4.8, testAdd.getAvgMark());
    }

    @Test
    void testRedDiplomWithoutCvalificationMark() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(1, "A", CreditCard.Mark.GOOD);
        testAdd.setMark(2, "A", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "C", CreditCard.Mark.EXCELLENT);
        assertTrue(testAdd.redDiplom());
    }

    @Test
    void testRedDiplomWithExcCvalificationMark() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(1, "A", CreditCard.Mark.GOOD);
        testAdd.setMark(2, "A", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "C", CreditCard.Mark.EXCELLENT);
        testAdd.setCvalificationMark(CreditCard.Mark.EXCELLENT);
        assertTrue(testAdd.redDiplom());
    }

    @Test
    void testRedDiplomWithGoodCvalificationMark() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(1, "A", CreditCard.Mark.GOOD);
        testAdd.setMark(2, "A", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "C", CreditCard.Mark.EXCELLENT);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertFalse(testAdd.redDiplom());
    }

    @Test
    void testRedDiplomWithBadMarks() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(1, "A", CreditCard.Mark.SATISFACTORY);
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(3, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertFalse(testAdd.redDiplom());
    }

    @Test
    void testRedDiplomWithBadMarks2() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(3, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertFalse(testAdd.redDiplom());
    }

    @Test
    void testRedDiplomWithBadMarks3() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(4, "A", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setMark(3, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertFalse(testAdd.redDiplom());
    }

    @Test
    void testAddDataInBadSequince() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(6, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertFalse(testAdd.redDiplom());
    }

    @Test
    void testStupidUser() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(6, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertFalse(testAdd.redDiplom());
    }

    @Test
    void noRedDiplom() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(6, "A", CreditCard.Mark.GOOD);
        testAdd.setMark(4, "GYM", CreditCard.Mark.GOOD);
        testAdd.setMark(2, "A", CreditCard.Mark.GOOD);
        testAdd.setMark(5, "Art", CreditCard.Mark.GOOD);
        testAdd.setMark(4, "B", CreditCard.Mark.GOOD);
        testAdd.setCvalificationMark(CreditCard.Mark.EXCELLENT);
        assertFalse(testAdd.redDiplom());
    }


    @Test
    void testGetStipa() {
        //стипа на текущий семестр расчитывается на основе данных о прошлом
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(6, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "Gym", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setMark(3, "C", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "D", CreditCard.Mark.GOOD);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertTrue(testAdd.getScholarship(1));
        assertTrue(testAdd.getScholarship(2));
        assertFalse(testAdd.getScholarship(3));
        assertFalse(testAdd.getScholarship(4));
        assertTrue(testAdd.getScholarship(5));
        assertTrue(testAdd.getScholarship(6));
    }
}
