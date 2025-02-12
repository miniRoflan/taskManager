package tasks;

import enums.State;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    public List<Integer> doneSubTusksId = new ArrayList<>();
    public List<Integer> subTasksId = new ArrayList<>();

    public Epic(String title, String description, int id) {
        super(title, description, id);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "doneSubTusksId=" + doneSubTusksId +
                ", subTasksId=" + subTasksId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", state=" + state +
                '}';
    }
}
