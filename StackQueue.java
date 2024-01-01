import java.util.Random;
import java.util.Scanner;

/**
 * StackQueue
 */
public class StackQueue {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Create a Scanner object

        int size;
        while (true) {
            System.out.print("Please enter data size [2 to 20]: ");
            size = input.nextInt(); // Read document size
            // ensure the user provides a valid size limit
            if (size > 1 && size < 21) {
                break;
            }
            // display error and try again
            System.out.println("Minimum of 2 and Maximum of 20 is allowed");
        }
        // close the scanner so as to avoid memory leakage
        input.close();
        // generate random number
        Random rand = new Random();
        /*
         * Raw data will be storced in stack_1, sorted result will be stored in stack_2,
         * queue will be used for proccessing to find the max number
         */
        Stack stack_1 = new Stack(size);
        Stack stack_2 = new Stack(size);
        Queue queue_1 = new Queue(size);
        Item[] result = new Item[size];
        System.out.print("Before random number generation");
        display(stack_1, stack_2, queue_1, result);
        // keep track of the max item
        Item max = new Item(-1, -1);// useing -1 for initialization
        // generate the items
        for (int i = 0; i < size; i++) {
            int k = rand.nextInt(10) + 1;
            Item item = new Item(k, i + 1);
            if (item.key > max.key) {
                if (max.key != -1) {
                    stack_1.push(item);
                }
                max = item;
            } else {
                stack_1.push(item);
            }
        }
        System.out.print("After random number generation");
        display(stack_1, stack_2, queue_1, result);

        // push the known max
        stack_2.push(max);

        for (int i = 0; i < size - 1; i++) {
            max = stack_1.peek();
            while (!stack_1.isEmpty()) {
                Item x = stack_1.pop();
                if (max.key < x.key)
                    max = x;
                queue_1.insert(x);
            }
            // push the known max
            stack_2.push(max);
            // return the items
            int k = 0; // used to track when a max has been dropped
            while (!queue_1.isEmpty()) {
                Item x = queue_1.remove();
                // check if its the sam as the last max we just worked with
                if (max.key == x.key && k < 1) {
                    k++;
                } else {
                    stack_1.push(x);
                }
            }
        }
        System.out.print("After sorting");
        display(stack_1, stack_2, queue_1, result);

        // filling the result
        for (int i = 0; i < size; i++) {
            result[i] = stack_2.pop();
        }

        // display result
        System.out.print("Final Result");
        display(stack_1, stack_2, queue_1, result);
    }

    public static void display(Stack stack_1, Stack stack_2, Queue queue_1, Item[] result) {
        System.out.println("");
        System.out.print("Stack1(bottom -> top):");
        stack_1.displayStack();
        System.out.println("");
        System.out.print("Stack2(bottom -> top):");
        stack_2.displayStack();
        System.out.println("");
        System.out.print("Queue(front -> rear):");
        queue_1.displayQueue();
        System.out.println("");
        System.out.print("OutputArray:");
        for (int i = 0; i < result.length; i++) {
            if (result[i] == null) {
                System.out.print("-:- ");
            } else {
                System.out.print(result[i] + " ");
            }
        }
        System.out.println("");
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
    }
}

/**
 * Item
 */
class Item {
    int key;
    int timestamp;

    Item(int k, int t) {
        key = k;
        timestamp = t;
    }

    @Override
    public String toString() {

        return key + ":" + timestamp;
    }
}

class Stack {
    private int maxSize; // size of stack array
    private Item[] items;
    private int top; // top of stack
    // --------------------------------------------------------------

    public Stack(int s) // constructor
    {
        maxSize = s; // set array size
        items = new Item[maxSize]; // create array
        top = -1; // no items yet
    }

    // --------------------------------------------------------------
    public void push(Item j) // put item on top of stack
    {
        items[++top] = j; // increment top, insert item
    }

    public Item pop() // take item from top of stack
    {
        return items[top--]; // access item, decrement top
    }

    // --------------------------------------------------------------
    public Item peek() // peek at top of stack
    {
        return items[top];
    }

    // --------------------------------------------------------------
    public boolean isEmpty() // true if stack is empty
    {
        return (top == -1);
    }

    // --------------------------------------------------------------
    public boolean isFull() // true if stack is full
    {
        return (top == maxSize - 1);
    }

    // --------------------------------------------------------------
    public void displayStack() {
        if (!this.isEmpty()) {
            Stack temp = new Stack(top + 1);
            while (!this.isEmpty()) {
                Item x = this.pop();
                temp.push(x);
                System.out.print(x + " ");
            }
            while (!temp.isEmpty()) {
                this.push(temp.pop());
            }
        }
    }
    // --------------------------------------------------------------
} // end class StackX

class Queue {
    private int maxSize;
    private Item[] queArray;
    private int front;
    private int rear;
    private int nItems;

    // --------------------------------------------------------------
    public Queue(int s) // constructor
    {
        maxSize = s;
        queArray = new Item[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    // --------------------------------------------------------------
    public void insert(Item j) // put item at rear of queue
    {
        if (rear == maxSize - 1) // deal with wraparound
            rear = -1;
        queArray[++rear] = j; // increment rear and insert
        nItems++; // one more item
    }

    // --------------------------------------------------------------
    public Item remove() // take item from front of queue
    {
        Item temp = queArray[front++]; // get value and incr front
        if (front == maxSize) // deal with wraparound
            front = 0;
        nItems--; // one less item
        return temp;
    }

    // --------------------------------------------------------------
    public Item peekFront() // peek at front of queue
    {
        return queArray[front];
    }

    // --------------------------------------------------------------
    public boolean isEmpty() // true if queue is empty
    {
        return (nItems == 0);
    }

    // --------------------------------------------------------------
    public boolean isFull() // true if queue is full
    {
        return (nItems == maxSize);
    }

    // --------------------------------------------------------------
    public int size() // number of items in queue
    {
        return nItems;
    }

    // --------------------------------------------------------------
    public void displayQueue() {
        if (!this.isEmpty()) {
            Queue temp = new Queue(nItems);
            while (!this.isEmpty()) {
                Item x = this.remove();
                temp.insert(x);
                System.out.print(x + " ");
            }
            while (!temp.isEmpty()) {
                this.insert(temp.remove());
            }
        }
    }
    // --------------------------------------------------------------
} // end class Queue