package managers;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getAllTask();

    List<SubTask> getAllSubTask();

    List<Epic> getAllEpic();

    void deleteAllTask();

    void deleteAllSubTask();

    void deleteAllEpic();

    Task getTaskById(int id);

    SubTask getSubTaskById(int id);

    Epic getEpicById(int id);

    void deleteTaskById(int id);

    void deleteSubTaskById(int id);

    void deleteEpicById(int id);

    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubTask(SubTask subTask);

    List<SubTask> getSubTasksByEpicId(int id);

    void updateTask(Task upTask, int id);

    void updateSubTask(SubTask upSubTask, int id);

    void updateEpic(int id);
}
