package ru.spbu.apcyb.svp.tasks.multithreading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TanCalculateTest {

  @Test
  void testSimpleOne() throws IOException, InterruptedException {
    Path fileRead = Path.of("src/test/resources/multithreading/read1.txt");
    Path fileWrite = Path.of("src/test/resources/multithreading/write1.txt");
    Path fileCheck = Path.of("src/test/resources/multithreading/result_test1.txt");
    int numberOfThreads = 9;
    List<Double> dataList = new DataReader(fileRead).readFile();
    List<Double> resultList = new TanCalculate(numberOfThreads).calculateTan(dataList);
    DataWriter dataWriter = new DataWriter(fileWrite);
    dataWriter.writeListToFile(resultList);


    Assertions.assertEquals(-1, Files.mismatch(fileWrite, fileCheck));
  }

  @Test
  void testSimpleOneHundred() throws IOException, InterruptedException {
    Path fileRead = Path.of("src/test/resources/multithreading/read100.txt");
    Path fileWrite = Path.of("src/test/resources/multithreading/write100.txt");
    Path fileCheck = Path.of("src/test/resources/multithreading/result_test100.txt");
    int numberOfThreads = 9;
    List<Double> dataList = new DataReader(fileRead).readFile();
    List<Double> resultList = new TanCalculate(numberOfThreads).calculateTan(dataList);
    DataWriter dataWriter = new DataWriter(fileWrite);
    dataWriter.writeListToFile(resultList);


    Assertions.assertEquals(-1, Files.mismatch(fileWrite, fileCheck));
  }

  @Test
  void testSimpleMillion() throws IOException, InterruptedException {
    Path fileRead = Path.of("src/test/resources/multithreading/read1000000.txt");
    Path fileWrite = Path.of("src/test/resources/multithreading/write1000000.txt");
    Path fileCheck = Path.of("src/test/resources/multithreading/result_test1000000.txt");
    int numberOfThreads = 9;
    List<Double> dataList = new DataReader(fileRead).readFile();
    List<Double> resultList = new TanCalculate(numberOfThreads).calculateTan(dataList);
    DataWriter dataWriter = new DataWriter(fileWrite);
    dataWriter.writeListToFile(resultList);


    Assertions.assertEquals(-1, Files.mismatch(fileWrite, fileCheck));
  }

  @Test
  void testTime() throws IOException, InterruptedException {
    Path fileRead = Path.of("src/test/resources/multithreading/read1000000.txt");
    List<Double> dataList = new DataReader(fileRead).readFile();
    int test = 1;
    TanCalculate test1 = new TanCalculate(1);
    TanCalculate test9 =  new TanCalculate(9);

    Instant startOneThread = Instant.now();
    test1.calculateTan(dataList);
    Instant finishOneThread = Instant.now();

    Instant startNineThread = Instant.now();
    test9.calculateTan(dataList);
    Instant finishNineThread = Instant.now();

    System.out.println("One thread: " + Duration.between(startOneThread, finishOneThread).toMillis());
    System.out.println("Nine thread: " + Duration.between(startNineThread, finishNineThread).toMillis());
    Assertions.assertEquals(1, test);

  }
}
