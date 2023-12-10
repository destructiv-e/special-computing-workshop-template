package ru.spbu.apcyb.svp.tasks.stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounterInText {

  private final Path pathToFileRead;
  private final Path pathToFileWrite;
  private final Path pathToGlobalDirectory;


  public WordCounterInText(String pathToFileRead, String pathToFileWrite,
      String pathToGlobalDirectory) {
    this.pathToFileRead = Path.of(pathToFileRead);
    this.pathToFileWrite = Path.of(pathToFileWrite);
    this.pathToGlobalDirectory = Path.of(pathToGlobalDirectory);
  }

  private void checkDir() throws IOException {
    if (!Files.isDirectory(pathToGlobalDirectory)) {
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
    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(pathToFileWrite)) {
      for (Map.Entry<String, Long> entry : mapWord.entrySet()) {
        String getKey = entry.getKey();
        String getValue = entry.getValue().toString();
        bufferedWriter.write(getKey + " : " + getValue + "\n");
      }
    } catch (IOException e) {
      throw new IOException(e + "\nError while writing to output file");
    }
  }

  private void createFileForWord(String word, Long count, Path pathToDirectoryWrite) throws IOException {
    Path filePath = Path.of(pathToDirectoryWrite.toString(), word + ".txt");

    try (FileWriter writer = new FileWriter(filePath.toString())) {

      for (long i = 0; i < count; i++) {
        writer.write(word + " ");
      }

    } catch (IOException ex) {
      throw new IOException("Failed to create a file.");
    }
  }

  public void createDirectoryWithResultFiles(Map<String, Long> wordCounts) throws IOException {
    Path newDirectory = Path.of(pathToGlobalDirectory.toString() + "/results");
    try {
      Path directoryToSave = Files.createDirectories(newDirectory);
      for (Entry<String, Long> entry : wordCounts.entrySet()) {
        String word = entry.getKey();
        Long number = entry.getValue();
        CompletableFuture.runAsync(
            () -> {
              try {
                createFileForWord(word, number, directoryToSave);
              } catch (IOException e) {
                throw new InputMismatchException("the file could not be created.");
              }
            });
      }
    } catch (IOException ex) {
      throw new IOException("The directory could not be created. ");
    }
  }
}
