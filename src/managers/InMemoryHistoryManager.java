package managers;

import tasks.Task;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> history = new LinkedList<>();


    @Override
    public void addTask(Task task) {
        if (history.size() == 10) {
            history.removeFirst();
        }

        history.addLast(task);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}