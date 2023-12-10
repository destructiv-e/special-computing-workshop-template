package ru.spbu.apcyb.svp.tasks.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordCounterInTextTest {

  @Test
  void simpleTest() throws IOException {
    String pathToFileWrite = "src/test/resources/stream/counts.txt";
    String pathToFileRead = "src/test/resources/stream/text.txt";
    String pathToDirectory = "src/test/resources/stream";

    WordCounterInText counter = new WordCounterInText(pathToFileRead, pathToFileWrite, pathToDirectory);
    counter.printMapToCountFile(counter.countWord());
    counter.createDirectoryWithResultFiles(counter.countWord());

    Path pathTestCount = Path.of("src/test/resources/stream/counts.txt");
    File[] resultFiles =
        (new File("src/test/resources/stream/results"))
            .listFiles();
    assert resultFiles != null;

    int count = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader(pathTestCount.toString()))) {
      while ((reader.readLine()) != null) {
        count++;
      }
    }

    Assertions.assertEquals(65, count);
    Assertions.assertEquals(65, resultFiles.length);
  }
}
