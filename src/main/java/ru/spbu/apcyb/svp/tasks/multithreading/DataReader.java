package ru.spbu.apcyb.svp.tasks.multithreading;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Класс для чтения и сохранения в List данных из файла.
 */
public class DataReader {

  private final Path path;

  public DataReader(Path path) {
    this.path = path;
  }

  /**
   * Метод, для чтения и сохранения данных из файла.
   *
   * @return - List данных
   * @throws IOException - в случае, если путь не ведёт к файлу
   */
  public List<Double> readFile() throws IOException {
    if (Files.isDirectory(Path.of(path.toUri()))) {
      throw new IOException("Provided file is a directory");
    }
    try (Scanner scanner = new Scanner(new File(path.toString()))) {
      List<Double> numbers = new ArrayList<>();
      while (scanner.hasNextLine()) {
        StringTokenizer stringTokenizer = new StringTokenizer(scanner.nextLine(), " ");
        while (stringTokenizer.hasMoreTokens()) {
          double num = Double.parseDouble(stringTokenizer.nextToken());
          numbers.add(num);
        }
      }
      return numbers;
    }
  }
}
