package ru.yandex.oop;

import ru.yandex.oop.model.*;
import ru.yandex.oop.servise.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager manager = Managers.getDefault(); // создание обьектов для работы с задачами через класс Managers
        HistoryManager historyManager = Managers.getDefaultHistory();// создание обьектов для работы с просмотреными
        // задачами через класс Managers

        Task task1 = new Task("Task 1", "Собрать коробки", Status.NEW);
        manager.createTask(task1);
        Task task2 = new Task("Task 2", "Отметить Новый Год", Status.NEW);
        manager.createTask(task2);

        Epic epic1 = new Epic("Epic1", "Найти коробки", Status.NEW);
        manager.createEpic(epic1);

        Epic epic2 = new Epic("Epic2", "Организовать праздник", Status.NEW);
        manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("subStack1", "Придти на склад", Status.NEW, epic1);
        Subtask subtask2 = new Subtask("subStack2", "Начать поиск", Status.NEW, epic1);
        Subtask subtask3 = new Subtask("subStack3", "Прекратить поиск", Status.NEW, epic1);

        manager.createSubStack(subtask1);
        manager.createSubStack(subtask2);
        manager.createSubStack(subtask3);
        epic1.addSubTasks(subtask1);
        epic1.addSubTasks(subtask2);
        epic1.addSubTasks(subtask3);

        System.out.println("Эпик: " + epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);

        manager.getTaskHistory(task1.getId());  // просматриваем задачи,епики, подзадачи
        manager.getTaskHistory(task2.getId());
        manager.getEpicHistory(epic1.getId());
        manager.getSubTaskHistory(subtask1.getId());
        manager.getSubTaskHistory(subtask2.getId());
        manager.getSubTaskHistory(subtask3.getId());
        System.out.println("просмотр до");
        System.out.println(historyManager.getHistory());

        manager.getTaskHistory(task1.getId());
        manager.getTaskHistory(task1.getId());
        manager.getEpicHistory(epic1.getId());
        System.out.println("просмотр после");
        System.out.println(historyManager.getHistory());

    }
}
