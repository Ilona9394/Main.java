package ru.yandex.oop.servise;

import ru.yandex.oop.model.Epic;
import ru.yandex.oop.model.Status;
import ru.yandex.oop.model.Subtask;
import ru.yandex.oop.model.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class FileBackedManeger extends InMemoryTaskManager {

    protected File fileSave;

    protected static String fileSavePath = "id, name, description, status, epic\n";

  //  FileBackedManeger fileBackedManeger = new FileBackedManeger();

    public FileBackedManeger(File fileSave) {
        this.fileSave = fileSave;
        save();
    }



    public void save() throws IOException {

        Writer filewriter = new FileWriter("fileSave",true);


        fileSave.write(historyToString(super.getTaskHistory()));

    }


    static String historyToString(HistoryManager manager) {  // статические метод для сохранения менеджера истории из CSV.
        StringBuilder csv = new StringBuilder();
        for (Task task : manager.getHistory()) {
            csv.append(task.getId()).append(","));
        }
        return csv.toString();
    }

    static List<Integer> historyFromString(String value) { // статические метод для восстановления менеджера истории из CSV.
        Files.readString(Path.of(path));   // В Java есть несколько способов чтения файлов. Вы можете использовать такой:

        return null;
    }

    String toString(Task task) { // метод сохранения задачи в строку
return String.format("%s,%s,%s,%s,%s,\n", task.getId(), task.getTaskName(),task.getTaskDescription() ,task.getStatus());

    }

    Task fromString(String value) {  // метод создания задачи из строки

    }


    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        File file = new File(".resourses/historyTask.csv");
        TaskManager manager = new FileBackedManeger(file);

        Task task10 = new Task("Task 10", "Познать Вселенную", Status.NEW);
        Task task20 = new Task("Task 20", "Подготовить человечество к межгалактическим полётам" +
                " ", Status.NEW);
        manager.createTask(task10);
        manager.createTask(task20);

        Epic epic10 = new Epic("Epic 10", "Прочесть Стивена Хокинга", Status.NEW);
        Epic epic20 = new Epic("Epic 20", "Собрать адронный коллайдер ", Status.NEW);
        manager.createEpic(epic10);
        manager.createEpic(epic20);

        Subtask subtask10 = new Subtask("subStack 10", "Пойти в библиотеку", Status.NEW, epic10);
        manager.createSubStack(subtask10);
        epic10.addSubTasks(subtask10);

        Subtask subtask20 = new Subtask("subStack 10", "Разобрать ПК мужа. " +
                "Из запчастей собрать коллайдер:)", Status.NEW, epic20);
        manager.createSubStack(subtask20);
        epic10.addSubTasks(subtask20);

    }


    // методы преопределенные из InMemoryTaskManager


    @Override
    public void createSubStack(Subtask subtask) {
        super.createSubStack(subtask);
        save();
    }

}


