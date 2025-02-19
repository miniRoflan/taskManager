package managers;

import enums.State;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager {
    public int newId = 1;

    Map<Integer, Task> taskMap = new HashMap<>();
    Map<Integer, SubTask> subTasksMap = new HashMap<>();
    Map<Integer, Epic> epicMap = new HashMap<>();


    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    public List<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTasksMap.values());
    }

    public List<Epic> getAllEpic() {
        return new ArrayList<>(epicMap.values());
    }

    public void deleteAllTasks() {
        taskMap.clear();
    }

    public void deleteAllSubTasks() {
        for (Integer curSubTaskId : subTasksMap.keySet()) {
            int curEpicId = subTasksMap.get(curSubTaskId).epicId;
            epicMap.get(curEpicId).subTasksId.clear();
            epicMap.get(curEpicId).doneSubTusksId.clear();

            updateEpic(curEpicId);
        }


        subTasksMap.clear();
    }

    public void deleteAllEpic() {
        epicMap.clear();
        subTasksMap.clear();
    }

    public Task getTaskFromId(int id) {
        return taskMap.get(id);
    }

    public SubTask getSubTaskFromId(int id) {
        return subTasksMap.get(id);
    }

    public Epic getEpicFromId(int id) {
        return epicMap.get(id);
    }

    public void deleteTaskFromId(int id) {
        taskMap.remove(id);
    }

    public void deleteSubTaskFromId(int id) {
        int curEpicId = subTasksMap.get(id).epicId;
        subTasksMap.remove(id);

        epicMap.get(curEpicId).subTasksId.remove((Integer) id);
        epicMap.get(curEpicId).doneSubTusksId.remove((Integer) id);

        updateEpic(curEpicId);
        //add updateEpicState method
    }

    public void createTask(Task task) {
        task.id = newId;
        taskMap.put(task.id, task);
        newId++;
    }

    public void createEpic(Epic epic) {
        epic.id = newId;
        epicMap.put(epic.id, epic);
        updateEpic(newId);
        newId++;
    }

    public void createSubTask(SubTask subTask) {
        subTask.id = newId;
        epicMap.get(subTask.epicId).subTasksId.add(subTask.id);
        subTasksMap.put(subTask.id, subTask);
        newId++;
    }

    public List<SubTask> getAllSubTasksFromEpicId(int id) {
        List<SubTask> result = new ArrayList<>();

        for (Integer subTaskId : epicMap.get(id).subTasksId) {
            result.add(subTasksMap.get(subTaskId));
        }

        return result;
    }

    public void updateTask(Task upTask, int id) {
        taskMap.put(id , upTask);
    }

    public void updateSubTask(SubTask upSubTask, int id) {
        subTasksMap.put(id, upSubTask);

        if (upSubTask.state.equals(State.DONE)) {
            epicMap.get(upSubTask.epicId).doneSubTusksId.add(id);

            updateEpic(upSubTask.epicId);
            //add method
        }
    }

    private void updateEpic(int id) {
        if (epicMap.get(id).subTasksId.isEmpty()) {
            for (Integer subId : epicMap.get(id).subTasksId) {
                if (!subTasksMap.get(subId).state.equals(State.NEW)) {
                    epicMap.get(id).state = State.IN_PROGRESS;
                    return;
                }
            }

            epicMap.get(id).state = State.NEW;
        } else if (epicMap.get(id).subTasksId.size() == epicMap.get(id).doneSubTusksId.size()) {
            epicMap.get(id).state = State.DONE;
        } else {
            epicMap.get(id).state = State.IN_PROGRESS;
        }
    }

}
