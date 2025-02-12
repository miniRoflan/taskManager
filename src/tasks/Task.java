package tasks;


import enums.State;

public class Task {
    public String title;
    public String description;
    public int id;
    public State state;

    public Task() {
    }

    public Task(String title, String description, int id, State state) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.state = state;
    }

    public Task(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", state=" + state +
                '}';
    }
}
