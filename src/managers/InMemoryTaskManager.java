package managers;

import enums.State;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager{
    public int newId = 1;

    private Map<Integer, Task> taskMap = new HashMap<>();
    private Map<Integer, SubTask> subTasksMap = new HashMap<>();
    private Map<Integer, Epic> epicMap = new HashMap<>();

    private HistoryManager historyManager = Managers.getDefaultHistory();


    @Override
    public List<Task> getAllTask() {
        return new ArrayList<>(taskMap.values());
    }

    @Override
    public List<SubTask> getAllSubTask() {
        return new ArrayList<>(subTasksMap.values());
    }

    @Override
    public List<Epic> getAllEpic() {
        return new ArrayList<>(epicMap.values());
    }

    @Override
    public void deleteAllTask() {
        taskMap.clear();
    }

    @Override
    public void deleteAllSubTask() {
        for (Integer curSubTaskId : subTasksMap.keySet()) {
            int curEpicId = subTasksMap.get(curSubTaskId).epicId;
            epicMap.get(curEpicId).subTasksId.clear();
            epicMap.get(curEpicId).doneSubTusksId.clear();

            updateEpic(curEpicId);
        }

        subTasksMap.clear();
    }

    @Override
    public void deleteAllEpic() {
        epicMap.clear();
        subTasksMap.clear();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = taskMap.get(id);
        historyManager.addTask(task);

        return task;
    }

    @Override
    public SubTask getSubTaskById(int id) {
        SubTask subTask = subTasksMap.get(id);
        historyManager.addTask(subTask);

        return subTask;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epicMap.get(id);
        historyManager.addTask(epic);

        return epic;
    }

    @Override
    public void deleteTaskById(int id) {
        taskMap.remove(id);
    }

    @Override
    public void deleteSubTaskById(int id) {
        int curEpicId = subTasksMap.get(id).epicId;
        subTasksMap.remove(id);

        epicMap.get(curEpicId).subTasksId.remove((Integer) id);
        epicMap.get(curEpicId).doneSubTusksId.remove((Integer) id);

        updateEpic(curEpicId);
    }

    @Override
    public void deleteEpicById(int id) {
        for (int subTaskId : epicMap.get(id).subTasksId) {
            subTasksMap.remove(subTaskId);
        }

        epicMap.remove(id);
    }

    @Override
    public void createTask(Task task) {
        task.id = newId;
        taskMap.put(task.id, task);
        newId++;
    }

    @Override
    public void createEpic(Epic epic) {
        epic.id = newId;
        epicMap.put(epic.id, epic);
        updateEpic(newId);
        newId++;
    }

    @Override
    public void createSubTask(SubTask subTask) {
        subTask.id = newId;
        epicMap.get(subTask.epicId).subTasksId.add(subTask.id);
        subTasksMap.put(subTask.id, subTask);
        newId++;
    }

    @Override
    public List<SubTask> getSubTasksByEpicId(int id) {
        List<SubTask> result = new ArrayList<>();

        for (Integer subTaskId : epicMap.get(id).subTasksId) {
            result.add(subTasksMap.get(subTaskId));
        }

        return result;
    }

    @Override
    public void updateTask(Task upTask, int id) {
        taskMap.put(id , upTask);
    }

    @Override
    public void updateSubTask(SubTask upSubTask, int id) {
        subTasksMap.put(id, upSubTask);

        if (upSubTask.state.equals(State.DONE)) {
            epicMap.get(upSubTask.epicId).doneSubTusksId.add(id);

            updateEpic(upSubTask.epicId);
        }
    }

    @Override
    public void updateEpic(int id) {
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
