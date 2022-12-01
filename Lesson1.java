import java.util.Arrays;

public class Lesson1 {
    public static void main(String[] args) {
        int[] arr1 = new int[]{10, 20, 30, 40, 50};
        int[] arr2 = new int[]{5, 15, 0, 35, 45};
        int[] arr3 = new int[]{2};
        try {
            var newArr = getNewArrDiv(arr1, arr2);
            System.out.println(Arrays.toString(newArr));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try{
            var newArr2 = getNewArrMinus(arr1, arr3);
            System.out.println(Arrays.toString(newArr2));
        }
        catch (RuntimeException e){
            System.out.println(e);
        }

        String s = null;
        try {
            printLengthMessage(s);
        }
        catch (RuntimeException e){
            System.out.println(e);
        }
    }

    public static int[] getNewArrDiv(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new RuntimeException("Длины массивов не равны");
        }
        var newArr = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            if (arr2[i] == 0) {
                throw new RuntimeException("Деление на 0");
            } else {
                newArr[i] = arr1[i] / arr2[i];
            }
        }
        return newArr;
    }

    public static int[] getNewArrMinus(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new RuntimeException("Длины массивов не равны");
        }
        var newArr = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++)
            newArr[i] = arr1[i] - arr2[i];
        return newArr;
    }

    public static void printLengthMessage(String s){
        if(s == null){
            throw new RuntimeException("nullpointer");
        }
        System.out.println(s.length());
    }
}