package ru.yandex.oop.model;


public class Subtask extends Task {
    private Epic epic;

    public Subtask(String taskName, String taskDescription, Status status, Epic epic) {
        super(taskName, taskDescription, status);
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
}

