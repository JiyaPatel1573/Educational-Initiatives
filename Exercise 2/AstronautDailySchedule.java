import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Task class (Product)
class Task {
    private String description;
    private Date startTime;
    private Date endTime;
    private String priority;
    private boolean isCompleted;

    public Task(String description, Date startTime, Date endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(startTime) + " - " + sdf.format(endTime) + ": " + description + " [" + priority + "]" +
                (isCompleted ? " (Completed)" : "");
    }
}

// Factory class (TaskFactory)
class TaskFactory {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static Task createTask(String description, String startTime, String endTime, String priority) throws ParseException {
        Date start = timeFormat.parse(startTime);
        Date end = timeFormat.parse(endTime);
        return new Task(description, start, end, priority);
    }
}

// Observer interface (to notify on task conflicts)
interface TaskObserver {
    void notifyConflict(Task existingTask, Task newTask);
}

// Concrete Observer class
class ConflictNotifier implements TaskObserver {
    @Override
    public void notifyConflict(Task existingTask, Task newTask) {
        System.out.println("Error: Task conflicts with existing task \"" + existingTask.getDescription() + "\".");
    }
}

// Singleton class (ScheduleManager)
class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private TaskObserver observer;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observer = new ConflictNotifier();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        for (Task t : tasks) {
            if (isOverlapping(t, task)) {
                observer.notifyConflict(t, task);
                return;
            }
        }
        tasks.add(task);
        tasks.sort(Comparator.comparing(Task::getStartTime));
        System.out.println("Task added successfully. No conflicts.");
    }

    public void removeTask(String description) {
        Task taskToRemove = null;
        for (Task t : tasks) {
            if (t.getDescription().equals(description)) {
                taskToRemove = t;
                break;
            }
        }
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Error: Task not found.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
        } else {
            for (Task t : tasks) {
                System.out.println(t);
            }
        }
    }

    public void viewTasksByPriority(String priority) {
        boolean found = false;
        for (Task t : tasks) {
            if (t.getPriority().equalsIgnoreCase(priority)) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks with priority " + priority + ".");
        }
    }

    public void markTaskAsCompleted(String description) {
        for (Task t : tasks) {
            if (t.getDescription().equals(description)) {
                t.markCompleted();
                System.out.println("Task marked as completed.");
                return;
            }
        }
        System.out.println("Error: Task not found.");
    }

    private boolean isOverlapping(Task t1, Task t2) {
        return t1.getStartTime().before(t2.getEndTime()) && t2.getStartTime().before(t1.getEndTime());
    }
}

// Main class
public class AstronautDailySchedule{
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ScheduleManager scheduleManager = ScheduleManager.getInstance();

        while (true) {
            System.out.println("\nAstronaut Daily Schedule Organizer");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. View Tasks by Priority");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter task description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter start time (HH:mm): ");
                        String startTime = scanner.nextLine();
                        System.out.print("Enter end time (HH:mm): ");
                        String endTime = scanner.nextLine();
                        System.out.print("Enter priority (High/Medium/Low): ");
                        String priority = scanner.nextLine();

                        Task task = TaskFactory.createTask(description, startTime, endTime, priority);
                        scheduleManager.addTask(task);
                    } catch (ParseException e) {
                        System.out.println("Error: Invalid time format.");
                    }
                    break;

                case 2:
                    System.out.print("Enter task description to remove: ");
                    String taskToRemove = scanner.nextLine();
                    scheduleManager.removeTask(taskToRemove);
                    break;

                case 3:
                    scheduleManager.viewTasks();
                    break;

                case 4:
                    System.out.print("Enter task description to mark as completed: ");
                    String taskToComplete = scanner.nextLine();
                    scheduleManager.markTaskAsCompleted(taskToComplete);
                    break;

                case 5:
                    System.out.print("Enter priority level (High/Medium/Low): ");
                    String priorityLevel = scanner.nextLine();
                    scheduleManager.viewTasksByPriority(priorityLevel);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
