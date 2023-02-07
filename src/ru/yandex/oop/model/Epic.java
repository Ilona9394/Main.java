package ru.yandex.oop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {

    protected List<Subtask> subTasks = new ArrayList<>();

    public Epic(String taskName, String taskDescription, Status status) {
        super(taskName, taskDescription, status);
    }

    @Override
    public boolean isEpic() {
        return true;
    }

    public void addSubTasks(Subtask subtask) {
        subTasks.add(subtask);
    }

    public List<Subtask> getSubTaskIds() {
        return subTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subTasks, epic.subTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subTasks);
    }
}
