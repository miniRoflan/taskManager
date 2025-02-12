package tasks;

import enums.State;

public class SubTask extends Task {
    public int epicId;

    public SubTask(String title, String description, int id, State state, int epicId) {
        super(title, description, id, state);
        this.epicId = epicId;
    }

    public SubTask() {
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epicId=" + epicId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", state=" + state +
                '}';
    }
}
