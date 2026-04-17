import java.util.Scanner;

class PCB {
    int pid;
    PCB next;

    public PCB(int pid) {
        this.pid = pid;
    }
}

class PCBQueue {
    PCB front, rear;

    public void enqueue(int pid) {
        PCB node = new PCB(pid);

        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
    }

    public PCB dequeue() {
        if (front == null) return null;

        PCB temp = front;
        front = front.next;

        if (front == null) rear = null;

        return temp;
    }
}

public class LabFour {

    public static void printQueue(PCB node) {
        while (node != null) {
            System.out.print(node.pid);
            if (node.next != null) System.out.print(", ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        PCBQueue ready = new PCBQueue();
        PCBQueue wait = new PCBQueue();

        // ================= INPUT =================
        System.out.println("Enter 11 PIDs:");

        for (int i = 0; i < 11; i++) {
            int pid = input.nextInt();

            if (i < 5) {
                ready.enqueue(pid);
            } else {
                wait.enqueue(pid);
            }
        }


        // ================= MENU =================
        int choice;

        do {
            System.out.println("\nUtility Menu:\n");
            System.out.println("1.) Add PCB to Ready Queue");
            System.out.println("2.) Add PCB to Wait Queue");
            System.out.println("3.) Run a process");
            System.out.println("4.) Display all of the Processes");
            System.out.println("5.) Display the Ready Queue");
            System.out.println("6.) Display the Wait Queue");
            System.out.println("7.) Exit");

            System.out.print("\nPlease make a selection: ");
            choice = input.nextInt();

            switch (choice) {

                case 1:
                    PCB w = wait.dequeue();
                    if (w != null) ready.enqueue(w.pid);
                    break;

                case 2:
                    PCB r = ready.dequeue();
                    if (r != null) wait.enqueue(r.pid);
                    break;

                case 3:
                    PCB run = ready.dequeue();
                    if (run != null)
                        System.out.println("Process " + run.pid + " executed.");
                    break;

                case 4:
                    System.out.println("The processes on both queues are:\n");

                    printQueue(ready.front);
                    printQueue(wait.front);
                    break;

                case 5:
                    System.out.println("The processes on the ready queue are:\n");
                    printQueue(ready.front);
                    break;

                case 6:
                    System.out.println("The processes on the wait queue are:\n");
                    printQueue(wait.front);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;
            }

        } while (choice != 7);

        input.close();
    }
}
