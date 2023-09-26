package ru.nsu.yukhnina;

/**Содержит основную функцию sort и вспомогательную heeapify.*/

public class Main {
    /**Постройте max-heap из входных данных.
    *
    * @param arr - массив который будет сортироваться.
    * @return arr отстортированный массив
    */
    public int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    /** Функция для преобразования в кучу.*/
    void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    /**
    * Function for good work build.
    *
    *@param args - что-то.
    */
    public static void main(String[] args) {
        System.out.print("I work");
    }
}