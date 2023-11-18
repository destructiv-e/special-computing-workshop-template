package ru.spbu.apcyb.svp.tasks.atm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Калькулятор комбинаций + количество комбинаций.
 */
public class CombinationCalc {

  private final List<Integer> nominals;
  private final long targetSum;
  private final List<List<Integer>> combinations;
  private long combinationsNumber;

  /**
   * Подсчёт всех комбинаций и их суммы.
   *
   * @param targetSum - целевая сумма
   * @param nominals  - номиналы
   */
  protected CombinationCalc(long targetSum, List<Integer> nominals) {
    this.nominals = nominals;
    this.targetSum = targetSum;
    this.combinationsNumber = 0;
    this.combinations = calculate();
  }

  /**
   * Алгоритм поиска всех комбинаций.
   *
   * @return result - Вычисленные комбинаций.
   */
  public List<List<Integer>> calculate() {

    List<Integer> combination = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    Deque<StackData> todoStack = new ArrayDeque<>();
    todoStack.push(new StackData(nominals, targetSum, combination));

    while (!todoStack.isEmpty()) {

      StackData current = todoStack.pop();

      long s = 0;
      for (long x : current.partial) {
        s += x;
      }

      if (s == current.target) {
        result.add(current.partial);
        combinationsNumber++;
      }

      if (s >= current.target) {
        continue;
      }

      for (int i = 0; i < current.nominals.size(); i++) {
        ArrayList<Integer> remaining = new ArrayList<>();
        int n = current.nominals.get(i);
        for (int j = i; j < current.nominals.size(); j++) {
          remaining.add(current.nominals.get(j));
        }
        ArrayList<Integer> partialRec = new ArrayList<>(current.partial);

        partialRec.add(n);
        todoStack.push(new StackData(remaining, targetSum, partialRec));
      }
    }
    return result;
  }

  protected long getCombinationsNumber() {
    return combinationsNumber;
  }

  protected List<List<Integer>> getCombinations() {
    return combinations;
  }

  private record StackData(List<Integer> nominals, long target, List<Integer> partial) {

  }
}
