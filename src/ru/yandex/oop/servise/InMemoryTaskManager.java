package ru.yandex.oop.servise;

import ru.yandex.oop.model.*;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    private int id;

    private Map<Integer, Task> bigTask = new HashMap<>();  // мапа для хранения всех задач
    private Map<Integer, Epic> epicTask = new HashMap<>();  // мапа для хранения всех задач
    private Map<Integer, Subtask> subTask = new HashMap<>();  // мапа для хранения всех задач

    HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();

    @Override
    public Task getTaskHistory(int id) {
        Task task = bigTask.get(id);
        inMemoryHistoryManager.add(task);
    return task;
    }

    @Override
    public Epic getEpicHistory(int id) {
        Epic epic = epicTask.get(id);
        inMemoryHistoryManager.add(epic);
    return epic;
    }

    @Override
    public Subtask getSubTaskHistory(int id) {
        Subtask subtask = subTask.get(id);
        inMemoryHistoryManager.add(subtask);
        return subtask;
    }

    public List<Task> getBigTask() {
        List<Task> listBigTask = new ArrayList<>(bigTask.values());
        return listBigTask;
    }

    public List<Epic> getEpicTask() {
        List<Epic> listEpicTask = new ArrayList<>(epicTask.values());
        return listEpicTask;
    }

    public List<Subtask> getSubTask() {
        List<Subtask> listSubTask = new ArrayList<>(subTask.values());
        return listSubTask;
    }

    public List<Subtask> getEpicSubtask(Epic epic) {
        return epic.getSubTaskIds();
    }

    @Override
    public void updateStatusSubStack(Subtask subtask, Epic epic) {
        subTask.put(subtask.getId(), subtask);
        updateEpic(epic);
    }

    @Override
    public void updateStatusTask(Task task) {
        bigTask.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        List<Subtask> list = epic.getSubTaskIds();
        int statusNew = 0; // переменная для status "NEW"
        int statusDone = 0; // переменная для status "DONE"
        int statusInProgress = 0; // переменная для status "IN_PROGRESS"
        for (Subtask subtask : list) {
            if (subtask.getStatus().equals(Status.IN_PROGRESS)) {
                statusInProgress++;
            } else if (subtask.getStatus().equals(Status.NEW)) {
                statusNew++;
            } else if (subtask.getStatus().equals(Status.DONE)) {
                statusDone++;
            }
        }
        if (statusInProgress > 0) {
            epic.setStatus(Status.IN_PROGRESS);
        } else if (statusNew == epic.getSubTaskIds().size()) {
            epic.setStatus(Status.NEW);
        } else if (statusDone == epic.getSubTaskIds().size()) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public void createTask(Task task) {   // метод для создания задачи
        id++;
        task.setId(id);
        bigTask.put(task.getId(), task);
    }

    @Override
    public void createEpic(Epic epic) {
        id++;
        epic.setId(id);
        epicTask.put(epic.getId(), epic);
    }

    @Override
    public void createSubStack(Subtask subtask) {
        id++;
        subtask.setId(id);
        subTask.put(subtask.getId(), subtask);
    }

    @Override
    public void removeAllTask() {
        subTask.clear();
    }

    @Override
    public void removeAllEpic() {
        subTask.clear();
        epicTask.clear();
    }

    @Override
    public void removeAllSub() {
        bigTask.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryTaskManager manager = (InMemoryTaskManager) o;
        return id == manager.id && Objects.equals(bigTask, manager.bigTask) && Objects.equals(epicTask, manager.epicTask)
                && Objects.equals(subTask, manager.subTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bigTask, epicTask, subTask);
    }
}
