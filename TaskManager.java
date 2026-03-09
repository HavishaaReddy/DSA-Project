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

public class TaskManager {
//CO 1: Linear Search
    static Task linearSearch(List<Task> tasks, String title) {
        for (Task t : tasks) {
            if (t.title.equalsIgnoreCase(title)) {
                return t;
            }
        }
        return null;
    }
// CO 1: Insertion Sort
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
        Scanner sc = new Scanner(System.in);

        System.out.println("===== SMART TASK MANAGER =====");
        // Co 2: Linked Lists 
        List<Task> tasks = new ArrayList<>();
        // CO 3: Stack implementation   
        Stack<Task> completedStack = new Stack<>();

        // CO4: Hash Table using HashMap
        HashMap<String, Task> taskMap = new HashMap<>();

        boolean running = true;

        while (running) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks & Update Progress");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (InputMismatchException e) {
                System.out.println("[Error] Please enter a valid number.");
                sc.nextLine(); // clear buffer
                continue;
            }
            //Menu system
            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter task description: ");
                    String description = sc.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dueDate = sc.nextLine();

                    Task userTask = new Task(title, description, dueDate);
                    tasks.add(userTask);
                    taskMap.put(title, userTask); // CO4: store task in HashMap
                    System.out.println("[Info] Task added successfully!");
                    break;

                case 2:
                    if (tasks.isEmpty()) {
                        System.out.println("[Info] No tasks available.");
                    } else {
                        insertionSort(tasks);
                        System.out.println("\n=== Current Task List ===");
                        for (Task t : tasks) {
                            t.showDetails();
                        }

                        // Notification alert
                        System.out.println("[Notification] You have " + tasks.size() + " tasks in total.");

                        // Ask how many tasks completed
                        System.out.print("Enter number of tasks completed: ");
                        int completedCount;
                        try {
                            completedCount = sc.nextInt();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("[Error] Invalid input. Please enter a number.");
                            sc.nextLine();
                            break;
                        }

                        for (int i = 0; i < completedCount; i++) {
                            System.out.print("Enter title of completed task: ");
                            String completedTitle = sc.nextLine();
                            Task foundTask = linearSearch(tasks, completedTitle);
                            if (foundTask != null && !foundTask.completed) {
                                foundTask.markCompleted();
                                completedStack.push(foundTask);
                            } else {
                                System.out.println("[Error] Task not found or already completed.");
                            }
                        }
                        // CO2: Linked lists operations
                        // Display remaining tasks
                        System.out.println("\n=== Remaining Tasks ===");
                        int remaining = 0;
                        for (Task t : tasks) {
                            if (!t.completed) {
                                t.showDetails();
                                remaining++;
                            }
                        }
                        System.out.println("[Info] Remaining tasks: " + remaining);

                        // Progress percentage
                        int total = tasks.size();
                        int done = completedStack.size();
                        double progress = (total == 0) ? 0 : ((double) done / total) * 100;
                        System.out.printf("[Progress] You completed %d out of %d tasks (%.2f%%).\n", done, total, progress);
                    }
                    break;

                case 3:
                    System.out.print("Enter the title of the task to mark completed: ");
                    String searchTitle = sc.nextLine();
                    // CO 3- Stack applications
                    Task foundTask = linearSearch(tasks, searchTitle);
                    if (foundTask != null) {
                        foundTask.markCompleted();
                        completedStack.push(foundTask);
                    } else {
                        System.out.println("[Error] Task not found!");
                    }
                    break;

                case 4:
                    running = false;
                    System.out.println("[System] Task Manager shutting down...");
                    break;

                default:
                    System.out.println("[Error] Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
