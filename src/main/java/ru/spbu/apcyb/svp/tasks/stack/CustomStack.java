package ru.spbu.apcyb.svp.tasks.stack;

import java.util.EmptyStackException;
import java.util.Stack;
import ru.spbu.apcyb.svp.tasks.arraylist.CustomArrayList;

/**
 * Кастомный класс Stack.
 *
 * @param <T> - generic
 */
public class CustomStack<T> extends Stack<T> {

  transient CustomArrayList<T> list;

  /**
   * Создание объекта класса Stack.
   *
   * @param customArrayList - линейный список на основе которого создаётся стек
   */
  public CustomStack(T[] customArrayList) {
    list = new CustomArrayList<>(customArrayList);
  }

  /**
   * Создание объекта класса Stack.
   */
  public CustomStack() {
    list = new CustomArrayList<>();
  }

  /**
   * Добавление эл-та.
   *
   * @param element the item to be pushed onto this stack.
   * @return - эл-т, который добавили
   */
  @Override
  public T push(T element) {
    list.add(element);
    return element;
  }

  /**
   * Удаление эл-та.
   *
   * @return - эл-т, который удалили
   */
  @Override
  public synchronized T pop() {
    try {
      return list.remove(list.size() - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new EmptyStackException();
    }
  }

  /**
   * Получение эл-та без удаления.
   *
   * @return - эл-т, который получили
   */
  @Override
  public synchronized T peek() {
    try {
      return list.get(list.size() - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new EmptyStackException();
    }
  }

  /**
   * Проверка на пустоту.
   *
   * @return - true - пусто; false - не пусто
   */
  @Override
  public synchronized boolean isEmpty() {
    return list.isEmpty();
  }

  /**
   * Метод сравнения.
   *
   * @param obj the Object to be compared for equality with this Vector
   * @return - true - равны; false - различны
   */
  @Override
  public synchronized boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    return list.equals(((CustomStack<?>) obj).list);
  }

  /**
   * HashCode.
   *
   * @return - size
   */
  @Override
  public synchronized int hashCode() {
    return list.size();
  }
}
