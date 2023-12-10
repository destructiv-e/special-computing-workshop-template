package ru.spbu.apcyb.svp.tasks.multithreading;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Класс для многопоточного подсчёта тангенсов.
 */
public class TanCalculate {

  private final int numberOfThreads;

  public TanCalculate(int numberOfThreads) {
    this.numberOfThreads = numberOfThreads;
  }

  /**
   * Метод для вычислений тангенса.
   *
   * @param dataList - List данных
   * @return - List результат
   * @throws IOException          - в случае если пользователь использует больше 10 потоков
   * @throws InterruptedException - в случае если поток остановился из-за ошибки
   */
  public List<Double> calculateTan(List<Double> dataList)
      throws IOException, InterruptedException {
    if (numberOfThreads < 10) {
      ExecutorService executorService = Executors.newFixedThreadPool(this.numberOfThreads);
      try {

        Future<List<Double>> futuresList = executorService.submit(
            () -> dataList.parallelStream().map(Math::tan).toList());
        executorService.shutdown();
        return futuresList.get();

      } catch (InterruptedException | ExecutionException ex) {
        Thread.currentThread().interrupt();
        throw new InterruptedException("Error in thread");
      }
    } else {
      throw new IOException("The program simultaneously processes no more than 10 values.");
    }
  }
}

