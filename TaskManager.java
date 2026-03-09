import java.util.*;

// Task Class
class Task {
    String title;
    String description;
    String dueDate;
    boolean completed;

    Task(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
        System.out.println("[Notification] New task created: " + title);
    }

    void markCompleted() {
        completed = true;
        System.out.println("[Alert] Task completed successfully: " + title);
    }

    void showDetails() {
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Status: " + (completed ? "Completed ✅" : "Pending ⏳"));
        System.out.println("-----------------------------------");
    }
}

// Linked List Node (CO2)
class Node {
    Task task;
    Node next;

    Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

// Linked List Implementation
class TaskLinkedList {
    Node head;

    void insert(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null)
            temp = temp.next;

        temp.next = newNode;
    }

    void traverse() {
        Node temp = head;
        while (temp != null) {
            temp.task.showDetails();
            temp = temp.next;
        }
    }
}

public class TaskManager {

    // Linear Search (CO1)
    static Task linearSearch(List<Task> tasks, String title) {
        for (Task t : tasks) {
            if (t.title.equalsIgnoreCase(title)) {
                return t;
            }
        }
        return null;
    }

    // Insertion Sort by Due Date (CO1)
    static void insertionSort(List<Task> tasks) {
        for (int i = 1; i < tasks.size(); i++) {
            Task key = tasks.get(i);
            int j = i - 1;

            while (j >= 0 && tasks.get(j).dueDate.compareTo(key.dueDate) > 0) {
                tasks.set(j + 1, tasks.get(j));
                j--;
            }

            tasks.set(j + 1, key);
        }
    }

    public static void main(String[] args) {

        System.out.println("===== SMART TASK MANAGER =====");
        System.out.println("[System] Initializing task manager...");
        System.out.println("[System] Loading tasks...\n");

        List<Task> tasks = new ArrayList<>(); // CO4

        // Queue (CO3)
        Queue<Task> taskQueue = new LinkedList<>();

        // Stack (CO3)
        Stack<Task> completedStack = new Stack<>();

        // HashMap (CO4)
        HashMap<String, Task> taskMap = new HashMap<>();

        // Priority Queue / Heap (CO3)
        PriorityQueue<Task> priorityQueue =
                new PriorityQueue<>(Comparator.comparing(t -> t.dueDate));

        // Linked List (CO2)
        TaskLinkedList linkedList = new TaskLinkedList();

        Task t1 = new Task("Finish Resume", "Update resume with latest projects", "2026-03-10");
        Task t2 = new Task("Interview Prep", "Practice stack and queue problems", "2026-03-12");
        Task t3 = new Task("Portfolio Update", "Add Daily Tracker project", "2026-03-15");

        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        taskQueue.add(t1);
        taskQueue.add(t2);
        taskQueue.add(t3);

        taskMap.put(t1.title, t1);
        taskMap.put(t2.title, t2);
        taskMap.put(t3.title, t3);

        priorityQueue.add(t1);
        priorityQueue.add(t2);
        priorityQueue.add(t3);

        linkedList.insert(t1);
        linkedList.insert(t2);
        linkedList.insert(t3);

        System.out.println("\n[Info] Total Tasks Added: " + tasks.size());

        // Sorting
        insertionSort(tasks);

        System.out.println("\n=== Current Task List ===");
        for (Task t : tasks) {
            t.showDetails();
        }

        System.out.println("[Reminder] Upcoming deadlines are approaching!");
        System.out.println("[Tip] Try to complete tasks before due dates.\n");

        System.out.println("[Action] Marking first task as completed...");

        Task task = linearSearch(tasks, "Finish Resume");

        if (task != null) {
            task.markCompleted();
            completedStack.push(task);
        }

        System.out.println("\n=== Updated Task List ===");
        for (Task t : tasks) {
            t.showDetails();
        }

        System.out.println("[Progress] You completed 1 out of " + tasks.size() + " tasks.");
        System.out.println("[Motivation] Keep going! Finish remaining tasks.");
        System.out.println("[System] Task Manager shutting down...");
    }
}