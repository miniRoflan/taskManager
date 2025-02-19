package mainClass;


import enums.State;
import managers.InMemoryTaskManager;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        Task task1 = new Task("Task1", "aboba", 0, State.NEW);
        Task task2 = new Task("Task2", "a", 0, State.NEW);

        inMemoryTaskManager.createTask(task1);
        inMemoryTaskManager.createTask(task2);

        Epic epic1 = new Epic("Epic1", "2 suba", 0);
        Epic epic2 = new Epic("Epic2", "1 sub", 0);

        inMemoryTaskManager.createEpic(epic1);
        inMemoryTaskManager.createEpic(epic2);

        for (Task t : inMemoryTaskManager.getAllTasks()) {
            System.out.println(t);
        }

        System.out.println("\n\n");


        SubTask subTaskE1V1 = new SubTask("stE1V1", "ura", 0, State.NEW, 3);
        SubTask subTaskE1V2 = new SubTask("stE1V2", "AARAR", 0, State.NEW, 3);

        SubTask subTaskE2V1 = new SubTask("stE2V1", "hzzzz", 0, State.NEW, 4);

        inMemoryTaskManager.createSubTask(subTaskE1V1);
        inMemoryTaskManager.createSubTask(subTaskE1V2);
        inMemoryTaskManager.createSubTask(subTaskE2V1);


        for (Epic e : inMemoryTaskManager.getAllEpic()) {
            System.out.println(e);
        }

        System.out.println("\n\n");

        for (SubTask s : inMemoryTaskManager.getAllSubTasks()) {
            System.out.println(s);
        }


        System.out.println("\n=======AFTER UPDATE==========\n");

        inMemoryTaskManager.deleteTaskFromId(1);
        task2.state = State.DONE;
        inMemoryTaskManager.updateTask(task2, 2);

        for (Task t : inMemoryTaskManager.getAllTasks()) {
            System.out.println(t);
        }

        System.out.println("\n\n");


        subTaskE1V1.state = State.DONE;
        subTaskE1V2.state = State.IN_PROGRESS;
        subTaskE2V1.state = State.DONE;

        inMemoryTaskManager.updateSubTask(subTaskE1V1, 5);
        inMemoryTaskManager.updateSubTask(subTaskE1V2, 6);
        inMemoryTaskManager.updateSubTask(subTaskE2V1, 7);


        for (Epic e : inMemoryTaskManager.getAllEpic()) {
            System.out.println(e);
        }

        System.out.println("\n\n");

        for (SubTask s : inMemoryTaskManager.getAllSubTasks()) {
            System.out.println(s);
        }


        System.out.println("\n===========AFTER DELETE==========\n");



        inMemoryTaskManager.deleteSubTaskFromId(6);
        inMemoryTaskManager.deleteAllEpic();

        for (Epic e : inMemoryTaskManager.getAllEpic()) {
            System.out.println(e);
        }

        System.out.println("\n\n");

        for (SubTask s : inMemoryTaskManager.getAllSubTasks()) {
            System.out.println(s);
        }
    }
}
