package ru.spbu.apcyb.svp.tasks.atm;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Класс Main.
 */
public class Main {

  /**
   * Метод для вывода в консоль.
   */
  public static void main(String[] args) {
    scannerStream(System.in);
  }

  /**
   * Метод сканнера.
   *
   * @param input - входной поток
   */
  public static void scannerStream(InputStream input) {
    Scanner scan = new Scanner(input);
    System.out.println("Задача 1 \n Банкомат \n Введите количество номиналов: ");
    int numbNomin = scan.nextInt();
    int[] nomin = new int[numbNomin];
    System.out.println("Введите номиналы: ");
    for (int i = 0; i < nomin.length; i++) {
      nomin[i] = scan.nextInt();
    }
    System.out.println("Введите целевую сумму: \n");
    int sum = scan.nextInt();
    Atm atm = new Atm(nomin, sum);
    System.out.println("Количество комбинаций: " + atm.getNumberOfCombinations());
    System.out.println("\n Комбинации: \n");
    for (int i = 0; i < atm.getResultCombination().size(); i++) {
      System.out.println(atm.getResultCombination().get(i));
    }
  }
}

