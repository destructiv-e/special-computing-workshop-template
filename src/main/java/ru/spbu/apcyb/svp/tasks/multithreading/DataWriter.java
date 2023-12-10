package ru.spbu.apcyb.svp.tasks.multithreading;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для записи данных в файл.
 */
public class DataWriter {

  private final Path pathFile;
  private static final String EXEPTION_NOT_FILE = "Not file.";

  public DataWriter(Path path) {
    this.pathFile = path;
  }

  /**
   * Метод, для записи 1000000 рандомных чисел в файл.
   *
   * @throws IOException - в случае если путь не ведёт к файлу
   */
  public void writeRandomToFile() throws IOException {
    if (Files.isDirectory(pathFile)) {
      throw new IOException(EXEPTION_NOT_FILE);
    }
    double[] data = new double[1000000];
    for (int i = 0; i < data.length; i++) {
      data[i] = Math.random() * 100;
    }
    List<String> dataList = Arrays.stream(data).mapToObj(String::valueOf).toList();
    Files.write(pathFile, dataList, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
        StandardOpenOption.APPEND);
  }

  /**
   * Метод для записи массива в файл.
   *
   * @param dataArray - массив данных
   * @throws IOException - в случае если путь не ведёт к файлу
   */
  public void writeArrayToFile(double[] dataArray) throws IOException {
    if (Files.isDirectory(pathFile)) {
      throw new IOException(EXEPTION_NOT_FILE);
    }
    List<String> dataList = Arrays.stream(dataArray).mapToObj(String::valueOf).toList();
    Files.write(pathFile, dataList, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
        StandardOpenOption.APPEND);
  }

  /**
   * Метод для записи List в файл.
   *
   * @param dataList - List данных
   * @throws IOException - в случае если путь не ведёт к файлу
   */
  public void writeListToFile(List<Double> dataList) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathFile.toFile()))) {
      for (Double listElem : dataList) {
        writer.write(listElem.toString());
        writer.newLine();
      }
    } catch (IOException ex) {
      throw new IOException(EXEPTION_NOT_FILE);
    }
  }
}
