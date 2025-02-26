package managers;

import tasks.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_COUNT_TASKS_IN_HISTORY = 10;

    private List<Task> history = new LinkedList<>();
    private Map<Integer, Integer> idAndIndexInHistory = new HashMap<>();


    @Override
    public void addTask(Task task) {
        remove(task.id);

        if (history.size() == MAX_COUNT_TASKS_IN_HISTORY) {
            remove(history.getFirst().id);
        }

        idAndIndexInHistory.put(task.id, history.size());
        history.addLast(task);
    }

    @Override
    public void remove(int id) {
        if (idAndIndexInHistory.containsKey(id)) {
            history.remove((int) idAndIndexInHistory.get(id));
            idAndIndexInHistory.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}