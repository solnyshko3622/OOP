package ru.nsu.yukhnina;

import static java.lang.String.valueOf;

import java.util.Arrays;
import java.util.Objects;


/**
 * Класс, в котором определены все штучки для многочлена.
 * Сложениеб вычитание, умножение, нахождение производных любого порядка.
 */
public class Polynomial {
    float[] polynomIndexes;
    int maxIndex;

    Polynomial(float[] array) {
        maxIndex = array.length;
        polynomIndexes = new float[maxIndex];
        System.arraycopy(array, 0, polynomIndexes, 0, maxIndex);
    }

    /**
     * Функция для сложения двух многочленов.
     *
     * @param p - второе слагаемое.
     * @return результат сложения двух многочленов.
     */
    Polynomial add(Polynomial p) {
        int minLenght = Math.min(maxIndex, p.maxIndex);
        int maxLenght = Math.max(maxIndex, p.maxIndex);
        Polynomial result = new Polynomial(new float[maxLenght]);
        //перебираем всё до конца наименьшего массива.
        for (int i = 0; i < minLenght; i++) {
            result.polynomIndexes[i] = polynomIndexes[i] + p.polynomIndexes[i];
        }
        //если массивы не равны по длинне, то должно ещё что-то остаться, добавляем.
        if (maxLenght - p.maxIndex >= 0) {
            System.arraycopy(polynomIndexes, minLenght, result.polynomIndexes,
                    minLenght, maxLenght - p.maxIndex);
        }
        if (maxLenght - maxIndex >= 0) {
            System.arraycopy(p.polynomIndexes, minLenght, result.polynomIndexes,
                    minLenght, maxLenght - maxIndex);
        }
        return result;
    }

    /**
     *Нахождение разности двух полиномов.
     *
     * @param p - вычитаемое.
     * @return разность многочленов.
     */
    Polynomial difference(Polynomial p) {
        int minLenght = Math.min(maxIndex, p.maxIndex);
        int maxLenght = Math.max(maxIndex, p.maxIndex);
        Polynomial result = new Polynomial(new float[maxLenght]);
        //перебираем всё до конца наименьшего массива
        for (int i = 0; i < minLenght; i++) {
            result.polynomIndexes[i] = polynomIndexes[i] - p.polynomIndexes[i];
        }
        //если массивы не равны по длинне, то должно ещё что-то остаться, добавляем
        if (maxLenght - p.maxIndex >= 0) {
            System.arraycopy(polynomIndexes, minLenght, result.polynomIndexes,
                    minLenght, maxLenght - p.maxIndex);
        }
        for (int i = 0; i < maxLenght - maxIndex; i++) {
            result.polynomIndexes[minLenght + i] = -p.polynomIndexes[minLenght + i];
        }
        return result;
    }

    /**
     * Произведение двух полиномов.
     *
     * @param p - второй множитель.
     * @return произведение многочленов.
     */
    Polynomial mult(Polynomial p) {
        int maxLenght = Math.max(maxIndex, p.maxIndex);
        float[] resultArray = new float[maxIndex + p.maxIndex + 1];
        //на всякий случай заполняю нулями
        Arrays.fill(resultArray, 0);
        Polynomial result = new Polynomial(resultArray);
        for (int i = 0; i < maxIndex; i++) {
            for (int j = 0; j < p.maxIndex; j++) {
                result.polynomIndexes[i + j] += polynomIndexes[i] * p.polynomIndexes[j];
            }
        }
        return result;
    }

    /**
     * Вычисление значения полинома в точке x.
     *
     * @param x - точка,в которой нужно вычислить значение многочлена.
     * @return чему равен многочлен в точке x.
     */
    float calculationAtPoint(float x) {
        float helperX = 1;
        float result = 0;
        for (int i = 0; i < maxIndex; i++) {
            result += helperX * polynomIndexes[i];
            helperX *= x;
        }
        return result;
    }

    /**
     * Нахождение производной.
     *
     * @param n - порядок производной.
     * @return производная n-ной степени многочлена.
     */
    Polynomial differential(int n) {
        //если степень дифииренцирования больше чем количество индексов
        if (n > maxIndex) {
            return new Polynomial(new float[] {0});
        }
        Polynomial result = new Polynomial(polynomIndexes);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < result.maxIndex; j++) {
                result.polynomIndexes[j - 1] = result.polynomIndexes[j] * j;
            }
            result.maxIndex--;
            result.polynomIndexes[result.maxIndex] = 0;

        }
        return result;
    }

    /**
     * Сравнение полиномов.
     * Я не стала писать Override, потому что он на него ругался и так работает.
     *
     * @param o -объект, с которым нужно сравнить.
     * @return true or fale  или равны многочлены или нет.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Polynomial myPolinom = (Polynomial) o;
        //прверяем, что все значения где общие индексы не пустые равны, дальше будем смотреть,
        // что если у одного
        // полинома на месте начиная с номера n  ничего нет, а у другого нули, то они эквивалентны
        int minIndex = Math.min(this.maxIndex, myPolinom.maxIndex);
        for (int i = 0; i < minIndex; i++) {
            if (myPolinom.polynomIndexes[i] != this.polynomIndexes[i]) {
                return false;
            }
        }
        for (int i = minIndex; i < this.maxIndex; i++) {
            if (0 != this.polynomIndexes[i]) {
                return false;
            }
        }
        for (int i = minIndex; i < myPolinom.maxIndex; i++) {
            if (0 != myPolinom.polynomIndexes[i]) {
                return false;
            }
        }
        return true;
    }

    /**
    * Переопределяем hash тк поменяли equials.
    *
    * @return something i dont know.
    */
    @Override
    public int hashCode() {
        return Objects.hash(maxIndex, Arrays.hashCode(polynomIndexes));
    }

    /** переводит полином из вида, удобного программе в человеческий вид.
     *
     * @return многочлен в человеческом виде.
     */
    public String toString() {
        StringBuilder result = new StringBuilder("");
        if (maxIndex == 0) {
            return "0.0";
        }
        boolean sign = false;
        for (int i = maxIndex - 1; i > 1; i--) {
            if (polynomIndexes[i] > 0 && sign) {
                result.append(" + " + polynomIndexes[i] + "x^" + i);
            }
            if (polynomIndexes[i] < 0) {
                result.append(" - " + Math.abs(polynomIndexes[i]) + "x^" + i);
                sign = true;
            }
            if (polynomIndexes[i] > 0 && !sign) {
                result.append(polynomIndexes[i] + "x^" + i);
                sign = true;
            }
        }
        //для x в первой степени
        if (maxIndex > 1 && polynomIndexes[1] > 0 && sign) {
            result.append(" + " + polynomIndexes[1] + "x");
        }
        if (maxIndex > 1 && polynomIndexes[1] < 0) {
            result.append(" - " + Math.abs(polynomIndexes[1]) + "x");
            sign = true;
        }
        if (maxIndex > 1 && polynomIndexes[1] > 0 && !sign) {
            result.append(polynomIndexes[1] + "x");
            sign = true;
        }
        //для x в нулевой степени
        if (polynomIndexes[0] > 0 && sign) {
            result.append(" + " + polynomIndexes[0]);
        }
        if (polynomIndexes[0] < 0) {
            result.append(" - " + Math.abs(polynomIndexes[0]));
            sign = true;
        }
        if (polynomIndexes[0] >= 0 && !sign) {
            result.append(valueOf(polynomIndexes[0]));
        }
        return result.toString();
    }
}