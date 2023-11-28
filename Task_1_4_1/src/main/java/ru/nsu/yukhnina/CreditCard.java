package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Record book class. Find avg mark,
 * check opportunity red diploma,
 * check opportunity scholarship.
 */
public class CreditCard {
    //один и тот же предмет может повторяться несколько семестров,
    // но на оценку диплома влияет только посленяя, которая и будет храниться тут
    private ArrayList<HashMap<String, Mark>> marks;
    private int semestr;
    private Mark cvalification;

    /**
     * finalMark contains subjects final marks without all current,
     * marks it's all marks,
     * now we think that semestr is 0 and we havent marks.
     * It's for calculate first stipa.
     * And enums its marks we can have.
     */
    public CreditCard() {
        marks = new ArrayList<HashMap<String, Mark>>();
        marks.add(new HashMap<String, Mark>()); //добавляю значения для расчёта стипы за 1 семестр
        semestr = 0;
        cvalification = Mark.EXCELLENT;
    }

    /**
     * Marks we can have.
     */
    public enum Mark {
        EXCELLENT(5),
        GOOD(4),
        SATISFACTORY(3),
        UNSATISFACTORY(2);
        private final int value;

        Mark(int mark) {
            this.value = mark;
        }
    }

    /**
     * Sset cvalification mark.
     * Default it's EXCELLENT to find opportunity red diplom.
     */
    public void setCvalificationMark(Mark mark) {
        cvalification = mark;
    }

    /**
     * Add new mark if I know semesrt numberб subjects name and mark.
     */
    public void setMark(int newSemestr, String subject, Mark mark) {
        // прверяем, что если данных по этому семестру ещё не было,
        // то array list там null, нужно инициализироавть.
        // предположим что пользователь не сильно умный и может начать заполнять с конца,
        // поэтому добавим for для инициализации всех штук
        if (newSemestr > this.semestr) {
            for (int i = this.semestr; i <= newSemestr; i++) {
                marks.add(new HashMap<String, Mark>());
            }
            this.semestr = newSemestr;
        }
        marks.get(newSemestr).put(subject, mark);
    }

    /**
     * Calculate average mark.
     */
    public double getAvgMark() {
        double avgMark = marks.stream()
                .flatMap(map -> map.values().stream())
                .mapToInt(p -> p.value)
                .average()
                .orElse(0);

        return avgMark;
    }

    /**
     * Calculate opportunity have red diploma.
     */
    public boolean redDiplom() {
        //если квалификационная оценка не 5, т смысла проверять дальше нет
        if (cvalification != Mark.EXCELLENT) {
            return false;
        }
        Map<String, Mark> finalMark = marks.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue(),
                        (semGrade1, semGrade2) -> semGrade2));

        if (finalMark.values()
                .stream()
                .filter(p -> p.value <= Mark.SATISFACTORY.value)
                .count() > 0) {
            return false;
        }
        if ((double) finalMark.values()
                .stream()
                .filter(p -> p == Mark.EXCELLENT)
                .count() / (double) finalMark.values()
                .stream()
                .count()
                  >= 0.75) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Find can student live in this semestr.
     */
    public boolean getScholarship(int semestrnum) {
        if (marks
                .get(semestrnum - 1)
                .values()
                .stream()
                .filter(p -> p.value <= Mark.SATISFACTORY.value)
                .count() > 0) {
            return false;
        }
        return true;
    }

}
