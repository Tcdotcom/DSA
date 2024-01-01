import java.util.Random;
import java.util.Scanner;

public class OneDimArray_Test {
        public static final int SIZE = 7;
        public static final int MIN = 10;
        public static final int MAX = 100;
        public static void main(String[] args) {
            int[] a = new int[SIZE];
            int[] b = new int[SIZE];
            int[] c = new int[SIZE];
    
            System.out.println("Initial values/first array: ");
            printArray(a, a.length);
            System.out.println("Initial values/second array: ");
            printArray(b, b.length);
            System.out.println("Initial values/third array: ");
            printArray(c, c.length);
            System.out.print("Enter " + a.length + " integers: ");
            readArray(a, a.length);
            System.out.println("The first array is: ");
            printArray(a, a.length);
            //display sum
            System.out.println("The sum of the elements/first array is: " + sumArray(a, a.length));
            //display max
            System.out.println("Max value/first array is: " + maxArray(a, a.length));
            //display min
            System.out.println("Min value/first array is: " + minArray(a, a.length));
            //copy first array into second
            copyArray(a, b, a.length);
            System.out.println("After copy, the second array is: ");
            printArray(b, b.length);
            //generate third array using random numbers
            randArray(c, c.length, MIN, MAX);
            System.out.println("The third array (using random numbers) is: ");
            printArray(c, c.length);
            System.out.println();
        }
    
        //Method to input data from user
        public static void readArray(int[] list, int size) {
            Scanner input = new Scanner(System.in);
            for (int i = 0; i < size; i++)
                list[i] = input.nextInt();
        }
    
        //Method to create an array using random numbers
        public static void randArray(int[] list, int size, int low, int up) {
            Random rand = new Random();
            for (int i = 0; i < size; i++)
                list[i] = rand.nextInt(up - low + 1) + low;
        }
    
        //Method to print the array
        public static void printArray(int[] list, int size) {
            for (int i = 0; i < size; i++)
                System.out.print(list[i] + " ");
            System.out.println();
        }
    
        //Method to find sum
        public static int sumArray(int[] list, int size) {
            int sum = 0;
            for (int i = 0; i < size; i++)
                sum = sum + list[i];
            return sum;
        }
    
        //Method to find max
        public static int maxArray(int[] list, int size) {
           int max = list[0];
            for (int i = 1; i < size; i++) {
                if(list[i] > max)
                    max = list[i];
            }
            return max;
        }
    
        //Method to find min
        public static int minArray(int[] list, int size) {
            int min = list[0];
            for (int i = 1; i < size; i++) {
                if(list[i] < min)
                    min = list[i];
            }
            return min;
        }
    
        //Method to copy one array into another array
        public static void copyArray(int[] source, int[] destination, int size) {
            for (int i = 0; i < size; i++)
                destination[i] = source[i];
        }
    }//close class
    
