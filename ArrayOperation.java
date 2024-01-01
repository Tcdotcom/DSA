
import java.util.Random;

/**
 * ArrayOperation
 */
public class ArrayOperation {

    public static void main(String[] args) {
        // PART 1
        System.out.println("PART 1");
        // create a tally array of the expected possible value length,
        // in our case 100
        int[] tally = new int[101];
        // generating random numbers
        int[] listA = new int[50];
        int[] listB = new int[50];
        int[] common = new int[50];
        int[] thelist = new int[100];
        Random rand = new Random();
        // generate random number between 1 and 49
        int rand_n = rand.nextInt(49) + 1;
        int listA_len = rand_n;
        int listB_len = rand_n;

        // generating rand_n number of numbers
        for (int i = 0; i < rand_n; i++) {
            // populating listA
            while (true) {
                int val = rand.nextInt(101);
                if (tally[val] == 0 || tally[val] == 2) {
                    tally[val]++;
                    listA[i] = val;
                    break;
                }
            }
            // populating listB
            while (true) {
                int val = rand.nextInt(101);
                if (tally[val] == 0 || tally[val] == 1) {
                    tally[val] = tally[val] + 2;
                    listB[i] = val;
                    break;
                }
            }
        }
        // displaying listA
        System.out.print("ListA: ");
        for (int i = 0; i < rand_n; i++) {
            System.out.print(" " + listA[i]);
        }
        // displaying listB
        System.out.println("");
        System.out.print("ListB: ");
        for (int i = 0; i < rand_n; i++) {
            System.out.print(" " + listB[i]);
        }

        // removing duplicates
        for (int i = 0; i < rand_n; i++) {
            // check if it is equal to 3 in the tally
            if (tally[listA[i]] == 3) {
                listA[i] = -1;
                listA_len--;
            }
            if (tally[listB[i]] == 3) {
                listB[i] = -1;
                listB_len--;
            }
        }
        // generating sorted merged array and sortted common from tally array
        int common_cnt = 0;
        int thelist_cnt = 0;
        for (int i = 0; i < 100; i++) {
            // check if it is equal to 3 in the tally
            if (tally[i] == 3) {
                common[common_cnt++] = i;
            }
            if (tally[i] > 0 && tally[i] < 3) {
                thelist[thelist_cnt++] = i;
            }
        }
        // displaying common
        System.out.println("");
        System.out.print("Common: ");
        displayArr(common, common_cnt);
        // shift values in listA to fill -1 spaces
        int stop = 2;
        for (int i = 0; i < rand_n && stop > 0; i++) {
            // listA
            if (listA[i] < 0) {
                int next = i;
                int walker = i;
                while (walker < rand_n) {
                    walker++;
                    if (listA[walker] > -1) {
                        listA[next] = listA[walker];
                        listA[walker] = -1;
                        next++;
                    }
                }
                stop--;
            }
            // listB
            if (listB[i] < 0) {
                int next = i;
                int walker = i;
                while (walker < rand_n) {
                    walker++;
                    if (listB[walker] > -1) {
                        listB[next] = listB[walker];
                        listB[walker] = -1;
                        next++;
                    }
                }
                stop--;
            }
        }

        // displaying listA
        System.out.println("");
        System.out.print("ListA After: ");
        displayArr(listA, listA_len);
        // displaying listB
        System.out.println("");
        System.out.print("ListB After: ");
        displayArr(listB, listB_len);
        // displaying theList
        System.out.println("");
        System.out.print("TheList: ");
        displayArr(thelist, thelist_cnt);
        // PART 1
        System.out.println("");
        System.out.println("");
        System.out.println("PART 2");
        // choosing shorter array
        int[] shortList;
        int[] longList;
        int shortLen = 0;
        int longLen = 0;
        if (listB_len < listA_len) {
            shortList = listB;
            longList = listA;
            shortLen = listB_len;
            longLen = listA_len;
        } else {
            shortList = listA;
            longList = listB;
            shortLen = listA_len;
            longLen = listB_len;
        }
        int sum = 0;
        int skip = 0;
        for (int i = 0; i < shortLen; i++) {
            if (shortList[i] < longLen) {
                sum += longList[shortList[i]];
            } else {
                skip++;
            }
        }
        // displaying listA
        System.out.print("ListA: ");
        displayArr(listA, listA_len);
        // displaying listB
        System.out.println("");
        System.out.print("ListB: ");
        displayArr(listB, listB_len);
        System.out.println("");
        System.out.println("SUM: " + sum);
        System.out.println("SKIPS: " + skip);
    }

    public static void displayArr(int[] arr, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(" " + arr[i]);
        }
    }
}