package ru.spbu.apcyb.svp.tasks.stream;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounterInText {

  private final Path pathToFileRead;
  private final Path pathToFileWrite;
  private final Path pathToDirectoryWrite;

  public WordCounterInText(String pathToFileRead, String pathToFileWrite,
      String pathToDirectoryWrite) {
    this.pathToDirectoryWrite = Path.of(pathToDirectoryWrite);
    this.pathToFileRead = Path.of(pathToFileRead);
    this.pathToFileWrite = Path.of(pathToFileWrite);
  }

  private void checkDir() throws IOException {
    if (!Files.isDirectory(pathToDirectoryWrite)) {
      throw new IOException(
          "The path to the directory where you want to write files contains errors.");
    }
    if (Files.isDirectory(pathToFileRead)) {
      throw new IOException(
          "The path to the file to be scanned contains errors.");
    }
    if (Files.isDirectory(pathToFileWrite)) {
      throw new IOException(
          "The path to the file to write the result to contains errors.");
    }
  }

  public Map<String, Long> countWord() throws IOException {
    checkDir();
    try (Stream<String> lines = Files.lines(pathToFileRead)) {
      return lines.flatMap(words -> Arrays.stream(words.split(" ")))
          .filter(word -> !word.isEmpty())
          .map(String::toLowerCase)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    } catch (IOException e) {
      throw new IOException("The words didn't break correctly.");
    }
  }

  public void printMapToCountFile(Map<String, Long> mapWord) throws IOException {
    checkDir();
    try (FileWriter writer = new FileWriter(pathToFileWrite.toString())) {

      for (Map.Entry<String, Long> wordCount : mapWord.entrySet()) {
        String line = String.format(wordCount.getKey() + ": " + wordCount.getValue() + "\n");
        writer.write(line);
      }

    } catch (IOException e) {
      throw new IOException("Problem with output to file.");
    }
  }

  public void createFileForWord(String word, Long count) throws IOException {
    Path filePath = Path.of(String.valueOf(pathToDirectoryWrite), word + ".txt");

    try (FileWriter writer = new FileWriter(filePath.toString())) {

      for (long i = 0; i < count; i++) {
        writer.write(word + " ");
      }

    } catch (IOException ex) {
      throw new IOException("Problem creating file.");
    }
  }
}

