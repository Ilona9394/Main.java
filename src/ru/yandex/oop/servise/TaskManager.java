package ru.yandex.oop.servise;

import ru.yandex.oop.model.Epic;
import ru.yandex.oop.model.Subtask;
import ru.yandex.oop.model.Task;

import java.util.List;

public interface TaskManager {

    void updateStatusSubStack(Subtask subtask, Epic epic);

    void updateStatusTask(Task task);

    void updateEpic(Epic epic);

    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubStack(Subtask subtask);

    void removeAllTask();

    void removeAllEpic();

    void removeAllSub();

    List<Subtask> getEpicSubtask(Epic epic);

    List<Task> getBigTask();

    List<Epic> getEpicTask();

    List<Subtask> getSubTask();

    Task getTaskHistory(int id);

    Epic getEpicHistory(int id);

    Subtask getSubTaskHistory(int id);

}

