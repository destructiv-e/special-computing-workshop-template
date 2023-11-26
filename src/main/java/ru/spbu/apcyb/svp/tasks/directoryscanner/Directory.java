package ru.spbu.apcyb.svp.tasks.directoryscanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Класс директории.
 */
public class Directory {

  private final Path pathDirectory;
  private final List<Path> res = new ArrayList<>();

  /**
   * Конструктор директории в случае, если хотим получить из строки.
   *
   * @param pathString - строка, с путём к файлу/каталогу
   */
  public Directory(String pathString) {
    Path path = Path.of(pathString).toAbsolutePath();
    if (!Files.isDirectory(path)) {
      throw new IllegalArgumentException();
    } else {
      pathDirectory = path;
    }
  }

  /**
   * Конструктор директории в случае, если передаётся объект путь.
   *
   * @param path - строка, с путём к файлу/каталогу
   */
  public Directory(Path path) {
    if (!Files.isDirectory(path)) {
      throw new IllegalArgumentException();
    } else {
      pathDirectory = path;
    }
  }

  /**
   * Метод, который проходит все каталоги по заданному пути и записывает пути к ним (и файлам
   * внутри) в List.
   *
   * @return - List, в котором хранятся пути
   * @throws IOException - исключение
   */
  public List<Path> traversal() throws IOException {

    try (Stream<Path> streamDir = Files.walk(this.pathDirectory)) {

      List<Path> listDir = streamDir.toList();

      for (Path value : listDir) {

        Path f1 = Path.of(value.toUri());

        if (Files.isDirectory(f1)) {
          try (Stream<Path> str = Files.list(Path.of(value.toUri()))) {
            List<Path> strlist = str.toList();
            res.addAll(strlist);
          }
        }
      }
      return res;
    } catch (IOException e) {
      throw new IOException(
          "Provided string is not a directory: " + this.pathDirectory.toString());
    }
  }
}
