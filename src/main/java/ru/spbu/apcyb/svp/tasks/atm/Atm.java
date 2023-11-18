package ru.spbu.apcyb.svp.tasks.atm;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;


/**
 * Класс банкомат.
 */
public class Atm {

  private final List<Integer> nominals;
  private final long sum;
  private List<List<Integer>> resultCombination;
  private long numberOfCombinations;

  /**
   * Конструктор класса банкомат. Преобразуем полученный массив в List для удобства.
   *
   * @param nominals - полученный массив номиналов
   */
  public Atm(int[] nominals, long sum) {
    //С помощью библиотеки Apache Commons Lang преобразуем int[] в List<Integer>
    this.nominals = List.of(ArrayUtils.toObject(nominals));
    this.sum = sum;
    this.calcAtmCombination(nominals);
  }

  /**
   * Метод, который подсчитывает комбинации и их количество.
   */
  public void calcAtmCombination(int[] nominals) {
    nominalsCheck(nominals);
    CombinationCalc calc = new CombinationCalc(sum, this.nominals);
    this.resultCombination = calc.getCombinations();
    numberOfCombinations = calc.getCombinationsNumber();
  }

  /**
   * Метод, который удаляет повторяющиеся элементы в массиве, так же сортирует его.
   *
   * @param nominals - массив, который требуется осортировать
   */
  public void nominalsCheck(int[] nominals) {
    List<Integer> nominalsList = Arrays.stream(nominals).sorted().distinct().boxed().toList();
    for (Integer nominal : nominalsList) {
      if (nominal < 0) {
        throw new
            IllegalArgumentException(
            "Incorrect data: the array of denominations contains negative values");
      }
    }
    if (sum <= 0) {
      throw new IllegalArgumentException("Incorrect data: target amount must be greater than 0");
    }
    if (nominalsList.isEmpty()) {
      throw new IllegalArgumentException("Incorrect data: denomination array is empty");
    }
  }

  public List<List<Integer>> getResultCombination() {
    return resultCombination;
  }

  public long getNumberOfCombinations() {
    return numberOfCombinations;
  }

  public List<Integer> getNominals() {
    return nominals;
  }
}

