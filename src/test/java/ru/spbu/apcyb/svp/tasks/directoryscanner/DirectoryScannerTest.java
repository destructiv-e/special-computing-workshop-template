package ru.spbu.apcyb.svp.tasks.directoryscanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectoryScannerTest {

  @Test
  void countTest() throws IOException {
    Path pathRead = Path.of("src/test/resources/read");
    Path pathWrite = Path.of("src/test/resources/write/result.txt");

    Directory directory = new Directory(pathRead);
    int a = 6;
    int b = directory.traversal().size();

    Assertions.assertEquals(a, b);
  }

  @Test
  void pathTest() throws IOException {
    Path pathRead = Path.of("src/test/resources/read");
    Path pathWrite = Path.of("src/test/resources/write/result.txt");

    String expected = """
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\3.txt
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir\\2.txt
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir\\directory
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir\\directory\\1.txt
        C:\\Users\\HOME\\IdeaProjects\\special-computing-workshop-templateee\\src\\test\\resources\\read\\dir\\directory\\empty
        """;

    FilePrint.pathScanToFile(pathRead, pathWrite);
    String actual = Files.readString(Path.of(pathWrite.toUri()));

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void emptyTest() throws IOException {
    Path pathRead = Path.of("src/test/resources/read/dir/directory/empty");
    Path pathWrite = Path.of("src/test/resources/write/result.txt");

    FilePrint.pathScanToFile(pathRead, pathWrite);
    String actual = Files.readString(Path.of(pathWrite.toUri()));

    Assertions.assertEquals("", actual);
  }

  @Test
  void pathReadErrorTest() {
    Path pathRead = Path.of("src/test/resources/read/dir/directory/empty2");
    Path pathWrite = Path.of("src/test/resources/write/result.txt");

    Exception e = Assertions.assertThrows(RuntimeException.class,
        () -> FilePrint.pathScanToFile(pathRead, pathWrite));

    Assertions.assertEquals(null, e.getMessage());
  }

  @Test
  void stringReadErrorTest() {
    String pathRead = "src/test/resources/read/dir/directory/empty2";
    String pathWrite = "src/test/resources/write/result.txt";

    Exception e = Assertions.assertThrows(RuntimeException.class,
        () -> FilePrint.stringScanToFile(pathRead, pathWrite));

    Assertions.assertEquals("The path to the directory that needs to be scanned was passed incorrectly.", e.getMessage());
  }

  @Test
  void stringWriteErrorTest() {
    String pathRead = "src/test/resources/read";
    String pathWrite = "src\\test\\resources\\write\\resul2222t.txt";

    Exception e = Assertions.assertThrows(RuntimeException.class,
        () -> FilePrint.stringScanToFile(pathRead, pathWrite));

    Assertions.assertEquals("The path to the directory or file in which the scan result should be written was passed incorrectly.", e.getMessage());
  }
}
