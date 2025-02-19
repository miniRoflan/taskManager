package mainClass;


import enums.State;
import managers.Manager;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        Task task1 = new Task("Task1", "aboba", 0, State.NEW);
        Task task2 = new Task("Task2", "a", 0, State.NEW);

        manager.createTask(task1);
        manager.createTask(task2);

        Epic epic1 = new Epic("Epic1", "2 suba", 0);
        Epic epic2 = new Epic("Epic2", "1 sub", 0);

        manager.createEpic(epic1);
        manager.createEpic(epic2);

        for (Task t : manager.getAllTasks()) {
            System.out.println(t);
        }

        System.out.println("\n\n");


        SubTask subTaskE1V1 = new SubTask("stE1V1", "ura", 0, State.NEW, 3);
        SubTask subTaskE1V2 = new SubTask("stE1V2", "AARAR", 0, State.NEW, 3);

        SubTask subTaskE2V1 = new SubTask("stE2V1", "hzzzz", 0, State.NEW, 4);

        manager.createSubTask(subTaskE1V1);
        manager.createSubTask(subTaskE1V2);
        manager.createSubTask(subTaskE2V1);


        for (Epic e : manager.getAllEpic()) {
            System.out.println(e);
        }

        System.out.println("\n\n");

        for (SubTask s : manager.getAllSubTasks()) {
            System.out.println(s);
        }


        System.out.println("\n=======AFTER UPDATE==========\n");

        manager.deleteTaskFromId(1);
        task2.state = State.DONE;
        manager.updateTask(task2, 2);

        for (Task t : manager.getAllTasks()) {
            System.out.println(t);
        }

        System.out.println("\n\n");


        subTaskE1V1.state = State.DONE;
        subTaskE1V2.state = State.IN_PROGRESS;
        subTaskE2V1.state = State.DONE;

        manager.updateSubTask(subTaskE1V1, 5);
        manager.updateSubTask(subTaskE1V2, 6);
        manager.updateSubTask(subTaskE2V1, 7);


        for (Epic e : manager.getAllEpic()) {
            System.out.println(e);
        }

        System.out.println("\n\n");

        for (SubTask s : manager.getAllSubTasks()) {
            System.out.println(s);
        }


        System.out.println("\n===========AFTER DELETE==========\n");



        manager.deleteSubTaskFromId(6);
        manager.deleteAllEpic();

        for (Epic e : manager.getAllEpic()) {
            System.out.println(e);
        }

        System.out.println("\n\n");

        for (SubTask s : manager.getAllSubTasks()) {
            System.out.println(s);
        }
    }
}
