import java.util.Scanner;

/**
 * LinkedListOperation
 */
public class WordGame {

    public static void main(String[] args) {
        String word = "";
        Scanner input = new Scanner(System.in);
        /**
         * WordGame
         */
        System.out.println("Word Game******************************************");
        while (word.length() == 0) {
            System.out.print("Input a word of any length : ");
            word = input.nextLine();
            System.out.println("");
        }
        LinkedList list = new LinkedList();
        // use one loop to generate link.
        for (int i = word.length() - 1; i > -1; i--) {
            list.insertCH(word.charAt(i) + "");
        }

        // clean the set of words based on the rules
        boolean keepClean = true;
        while (keepClean) {
            int cnt = 0;
            System.out.println("");
            list.displayList();
            cnt += removeRM(list);
            cnt += removeVowel(list);
            if (cnt == 0) {
                keepClean = false;
            }
        }

        /**
         * NumberGame
         */
        System.out.println("");
        System.out.println("Number Game******************************************");
        System.out.println("You will need to enter two lists of sorted numbers");
        word = "";
        LinkedList list_1 = new LinkedList();
        while (word.length() == 0) {
            System.out.print("List 1: ");
            word = input.nextLine();
            if (!createNumberList(list_1, word)) {
                word = "";
                System.out.println("");
                System.out.println("The numbers must be sorted");
            }
            System.out.println("");
        }
        word = "";
        LinkedList list_2 = new LinkedList();
        while (word.length() == 0) {
            System.out.print("List 2: ");
            word = input.nextLine();
            if (!createNumberList(list_2, word)) {
                word = "";
                System.out.println("");
                System.out.println("The numbers must be sorted");
            }
            System.out.println("");
        }
        list_1.displayList();
        System.out.println("");
        list_2.displayList();

        LinkedList merge_list = new LinkedList();
        LinkedList duplicate_list = new LinkedList();
        Link current_1 = list_1.first;
        Link current_2 = list_2.first;
        while (current_1 != null || current_2 != null) {
            if (current_1 == null) {
                wordGameDuplicates(merge_list, duplicate_list, current_2);
                merge_list.insertInt(current_2.intData);
                current_2 = current_2.next;
            } else if (current_2 == null) {
                wordGameDuplicates(merge_list, duplicate_list, current_1);
                merge_list.insertInt(current_1.intData);
                current_1 = current_1.next;
            } else if (current_1.intData >= current_2.intData) {
                wordGameDuplicates(merge_list, duplicate_list, current_1);
                merge_list.insertInt(current_1.intData);
                current_1 = current_1.next;
            } else {
                wordGameDuplicates(merge_list, duplicate_list, current_2);
                merge_list.insertInt(current_2.intData);
                current_2 = current_2.next;
            }
        }
        System.out.println("");
        merge_list.displayList();
        System.out.println("");
        duplicate_list.displayList();
        System.out.println("");
        System.out.println("SubLists of duplicates:");
        if (duplicate_list.first == null) {
            System.out.println("No duplicates found");
        } else {
            Link current = duplicate_list.first;
            while (current != null) {
                System.out.print(current + " ");
                if (current.next != null && current.intData != current.next.intData) {
                    System.out.println("");
                }
                current = current.next;
            }
        }

        // close the scanner so as to avoid memory leakage
        input.close();

    }

    public static void wordGameDuplicates(LinkedList merge_list, LinkedList duplicate_list, Link current) {
        if (merge_list.first != null) {
            if (current.intData == merge_list.first.intData) {
                duplicate_list.insertInt(current.intData);
            } else if (duplicate_list.first != null && merge_list.first.intData == duplicate_list.first.intData) {
                duplicate_list.insertInt(merge_list.first.intData);
            }
        }
    }

    public static boolean createNumberList(LinkedList list, String data) {
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == ' ') {
                list.first.intData = Integer.parseInt(list.first.chData);
                if (list.first.next != null && list.first.intData < list.first.next.intData) {
                    return false;
                }
                continue;
            }
            if (i == 0 || data.charAt(i - 1) == ' ') {
                list.insertCH(data.charAt(i) + "");
            } else {
                list.first.chData = list.first.chData + "" + data.charAt(i);
            }
            if (i == (data.length() - 1)) {
                list.first.intData = Integer.parseInt(list.first.chData);
            }
        }
        return true;
    }

    public static int removeVowel(LinkedList list) {
        // remove vowels
        Link current = list.first; // search for link
        Link previous = null;
        int didDelete = 0;
        while (current != null) // until end of list,
        {
            if (previous != null && current.next != null && !isVowel(previous.chData) && !isVowel(current.next.chData)
                    && isVowel(current.chData)) {
                previous.next = current.next;
                current = current.next;
                didDelete++;
            }
            previous = current;
            current = current.next;
        }
        return didDelete;
    }

    public static int removeRM(LinkedList list) {
        // remove the rms
        Link current = list.first; // search for link
        Link previous = null;
        int didDelete = 0;
        while (current != null) // until end of list,
        {
            if (current.next != null && (current.chData + current.next.chData).equals("rm")) {
                int delCnt = 3;
                didDelete++;
                while (delCnt-- > 0) {
                    if (previous == null) {
                        list.first = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    current = current.next;
                }
            }
            previous = current;
            current = current.next;
        }
        return didDelete;
    }

    public static boolean isVowel(String a) {
        switch (a) {
            case "a":
            case "A":
            case "e":
            case "E":
            case "i":
            case "I":
            case "o":
            case "O":
            case "u":
            case "U":
                return true;
            default:
                return false;
        }
    }
}

/**
 * LinkedList
 */
class LinkedList {
    Link first;

    public void insertCH(String ch) { // make new link
        insert(ch, null);
    }

    public void insertInt(Integer in) { // make new link
        insert(null, in);
    }

    private void insert(String ch, Integer in) { // make new link
        Link newLink = new Link(ch, in);
        newLink.next = first; // newLink --> old first
        first = newLink; // first --> newLink
    }

    public void displayList() {
        System.out.println("List (first-->last): ");
        Link current = first; // start at beginning of list
        while (current != null) // until end of list,
        {
            System.out.print(current + " ");// print data
            current = current.next; // move to next link
        }
    }
}

/**
 * Link
 */
class Link {
    String chData;
    Integer intData;
    Link next;

    public Link(String ch, Integer in) {
        chData = ch;
        intData = in;
    }

    public void displayLink() {
        System.out.print("Link {" + chData + " } ");
    }

    @Override
    public String toString() {
        if (intData != null)
            return "" + intData;
        return "" + chData;
    }
}