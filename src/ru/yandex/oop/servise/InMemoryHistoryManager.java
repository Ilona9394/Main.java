package ru.yandex.oop.servise;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import ru.yandex.oop.model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    public Map<Integer, Node<Task>> historyTaskMap = new HashMap<>();
    private Node<Task> head;
    private Node<Task> tail;


    //  В ключах будут храниться id задач, а в значениях — узлы связного списка.
    // Изначально HashMap пустая. Она будет заполняться по мере добавления новых задач.

    public void linkLast(Task task) {  // метод добавляет задачу в конец связанного списка
        Node<Task> oldTail = tail;
        Node<Task> newNode = new Node<>(task, oldTail, null);

        tail = newNode;
        historyTaskMap.put(task.getId(), newNode);
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
    }

    public List<Task> getTasks() { // метод переводит задачи в обычный аррайлист
        List<Task> arrListHistoryTask = new ArrayList<>();
        Node<Task> node = head;
        while (node != null) {
            arrListHistoryTask.add(node.data);
            node = node.next;
        }
        return arrListHistoryTask;
    }

    @Override
    public void add(Task task) {
        if (historyTaskMap.containsKey(task.getId())) {
            remove(task.getId());
        }
        linkLast(task);
    }

    @Override
    public void remove(int id) { // дописать этот метод
        if (historyTaskMap.containsKey(id)) {
            Node<Task> node = historyTaskMap.get(id);
            removeNode(node);
        }
    }

    public void removeNode(Node<Task> node) {
        if (node != null) {
            historyTaskMap.remove(node.data.getId());
            if (node.prev != null && node.next != null) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            } else if (node.next == null) {
                tail = node.prev;
                tail.next = null;
            } else if (node.prev == null) {
                head = node.next;
                head.prev = null;
            }
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    class Node<T> {

        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

}
