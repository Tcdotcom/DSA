import java.util.Scanner;

/**
 * Recursion
 */
public class Recursion {
    public static void main(String[] args) {
        String word = "";
        Scanner input = new Scanner(System.in);
        /**
         * WordGame
         */
        while (word.length() == 0) {
            System.out.print("Input a word of any length : ");
            word = input.nextLine();
            System.out.println("");
        }
        SELinkList selist = new SELinkList();
        DELinkList delist = new DELinkList();
        // use one loop to generate link both lists.
        for (int i = word.length() - 1; i > -1; i--) {
            selist.insertFirst(word.charAt(i));
            delist.insertFirst(word.charAt(i));
        }
        System.out.println("Reverse list skim******************************************");
        System.out.print("List as is: ");
        selist.displayList();
        System.out.println("");
        System.out.print("List in recursive reverse: ");
        SkimLinkedList(selist.first);
        System.out.println("");
        System.out.println("Recursive Palendrum Check******************************************");
        System.out.print("List as is: ");
        delist.displayList();
        System.out.println("");
        if (palundrome(delist)) {
            System.out.println("It is a palindrome");
        } else {
            System.out.println("It is not a palindrome");
        }
        System.out.println("BONUS: power calculator******************************************");
        int number;
        int power;
        System.out.print("Enter number : ");
        number = input.nextInt();
        System.out.print("Enter power : ");
        power = input.nextInt();
        System.out.println(number + "^" + power + " = " + power(number, power));

        // close the scanner so as to avoid memory leakage
        input.close();
    }

    public static void SkimLinkedList(Link node) {
        if (node.next != null) {
            SkimLinkedList(node.next);
        }
        node.displayLink();
    }

    public static boolean palundrome(DELinkList list) {
        if (!list.isEmpty()) {
            if (list.first.dData == list.last.dData) {
                list.deleteFirst();
                if (!list.isEmpty()) {
                    list.deleteLast();
                }
                boolean check = true;
                if (!list.isEmpty()) {
                    check = palundrome(list);
                }
                return check;
            } else {
                return false;
            }
        }
        return false;
    }

    public static int mult(int mult, int multend, int power) {
        int _num = mult;
        int num = multend;
        while (multend > 1) {
            multend--;
            mult += _num;
        }
        if (power >= 1) {
            return mult(mult, power(num, power), 0);
        }
        return mult;
    }

    public static int power(int num, int power) {
        if (power == 0) {
            return 1;
        }
        if (power == 1) {
            return num;
        }
        if (power > 1) {
            return mult(num, num, power - 2);
        }
        return num;
    }
}

// double ended linked list
class DELinkList {
    public Link first; // ref to first link
    public Link last; // ref to last link
    // -------------------------------------------------------------

    public DELinkList() // constructor
    {
        first = null; // no links on list yet
        last = null;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true if no links
    {
        return first == null;
    }

    // -------------------------------------------------------------
    public void insertFirst(char dd) // insert at front of list
    {
        Link newLink = new Link(dd); // make new link
        if (isEmpty()) // if empty list,
            last = newLink; // newLink <-- last
        else
            first.previous = newLink; // newLink <-- old first
        newLink.next = first; // newLink --> old first
        first = newLink;
    }

    // -------------------------------------------------------------
    public void insertLast(char dd) // insert at end of list
    {
        Link newLink = new Link(dd); // make new link
        if (isEmpty()) // if empty list,
            first = newLink; // first --> newLink
        else {
            last.next = newLink; // old last --> newLink
            newLink.previous = last; // old last <-- newLink
        }
        last = newLink; // newLink <-- last
    }

    // -------------------------------------------------------------
    public Link deleteFirst() // delete first link
    { // (assumes non-empty list)
        Link temp = first;
        if (first.next == null) // if only one item
            last = null; // null <-- last
        else
            first.next.previous = null; // null <-- old next
        first = first.next; // first --> old next
        return temp;
    }

    // -------------------------------------------------------------
    public Link deleteLast() // delete first link
    { // (assumes non-empty list)
        Link temp = last;
        if (first.next == null) // if only one item
            first = null; // first --> null
        else
            last.previous.next = null; // old previous --> null
        last = last.previous; // old previous <-- last
        return temp;
    }

    public void displayList() {
        System.out.print("List (first-->last): ");
        Link current = first; // start at beginning of list
        while (current != null) // until end of list,
        {
            current.displayLink(); // print data
            current = current.next; // move to next link
        }
        System.out.println("");
    }
}

// Single ended linked list
class SELinkList {
    public Link first; // ref to first link on list
    // -------------------------------------------------------------

    public SELinkList() // constructor
    {
        first = null; // no items on list yet
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true if list is empty
    {
        return (first == null);
    }

    public void insertFirst(char dd) { // make new link
        Link newLink = new Link(dd);
        newLink.next = first; // newLink --> old first
        first = newLink; // first --> newLink
    }

    public void displayList() {
        System.out.print("List (first-->last): ");
        Link current = first; // start at beginning of list
        while (current != null) // until end of list,
        {
            current.displayLink(); // print data
            current = current.next; // move to next link
        }
        System.out.println("");
    }
}

class Link {
    public char dData; // data item
    public Link next; // next link in list
    public Link previous; // previous link in list
    // -------------------------------------------------------------

    public Link(char dd) // constructor
    {
        dData = dd; // (‘next’ is automatically
    } // set to null)
      // -------------------------------------------------------------

    public void displayLink() // display ourself
    {
        System.out.print("{" + dData + "}");
    }
}
// end
