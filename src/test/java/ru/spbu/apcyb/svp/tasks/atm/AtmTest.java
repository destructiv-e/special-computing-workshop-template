package ru.spbu.apcyb.svp.tasks.atm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AtmTest {

  @Test
  void testStrings() {
    InputStream input = new ByteArrayInputStream(("1+2 3+2\n100").getBytes());
    Assertions.assertThrows(InputMismatchException.class, () -> Main.scannerStream(input));
  }

  @Test
  void testEmpty() {
    InputStream input = new ByteArrayInputStream(("\n").getBytes());
    Assertions.assertThrows(NoSuchElementException.class, () -> Main.scannerStream(input));
  }

  @Test
  void testInput() {
    InputStream in = new ByteArrayInputStream(("3\n 1 2 3\n 5").getBytes());
    long targetSum = 5;
    int[] nominals = {1, 2, 3};
    Atm atm = new Atm(nominals, targetSum);
    Main.scannerStream(in);
    Assertions.assertEquals(5, atm.getNumberOfCombinations());
  }

  @Test
  void testInputString() {
    InputStream in = new ByteArrayInputStream(("3\n 1 a 3\n 5").getBytes());
    Assertions.assertThrows(InputMismatchException.class, () -> Main.scannerStream(in));
  }

  @Test
  void testInputSum() {
    InputStream in = new ByteArrayInputStream(("3\n 1 1+1 3\n 5").getBytes());
    Assertions.assertThrows(InputMismatchException.class, () -> Main.scannerStream(in));
  }

  @Test
  void testInputNegativeNominal() {
    InputStream in = new ByteArrayInputStream(("3\n 1 -2 3\n 5").getBytes());
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.scannerStream(in));
  }

  @Test
  void testSimple1() {
    long targetSum = 5;
    int[] nominals = {1, 2, 3};
    Atm atmTest = new Atm(nominals, targetSum);
    List<List<Integer>> actualCombination = atmTest.getResultCombination();
    long actualNumberOfCombination = atmTest.getNumberOfCombinations();

    List<List<Integer>> expectedCombination = new ArrayList<>();
    int[] arr1 = {2, 3};
    int[] arr2 = {1, 2, 2};
    int[] arr3 = {1, 1, 3};
    int[] arr4 = {1, 1, 1, 2};
    int[] arr5 = {1, 1, 1, 1, 1};
    expectedCombination.add(List.of(ArrayUtils.toObject(arr1)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr2)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr3)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr4)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr5)));
    long expectedNumberOfCombination = 5;

    Assertions.assertEquals(targetSum, atmTest.getNumberOfCombinations());
    Assertions.assertEquals(List.of(ArrayUtils.toObject(nominals)), atmTest.getNominals());
    Assertions.assertEquals(expectedCombination, actualCombination);
    Assertions.assertEquals(expectedNumberOfCombination, actualNumberOfCombination);
  }

  @Test
  void testSimple2() {
    long targetSum = 6;
    int[] nominals = {1, 2, 3};
    Atm atmTest = new Atm(nominals, targetSum);
    List<List<Integer>> actualCombination = atmTest.getResultCombination();
    long actualNumberOfCombination = atmTest.getNumberOfCombinations();

    List<List<Integer>> expectedCombination = new ArrayList<>();
    int[] arr1 = {3, 3};
    int[] arr2 = {2, 2, 2};
    int[] arr3 = {1, 2, 3};
    int[] arr4 = {1, 1, 2, 2};
    int[] arr5 = {1, 1, 1, 3};
    int[] arr6 = {1, 1, 1, 1, 2};
    int[] arr7 = {1, 1, 1, 1, 1, 1};
    expectedCombination.add(List.of(ArrayUtils.toObject(arr1)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr2)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr3)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr4)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr5)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr6)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr7)));
    long expectedNumberOfCombination = 7;

    Assertions.assertEquals(expectedNumberOfCombination, atmTest.getNumberOfCombinations());
    Assertions.assertEquals(List.of(ArrayUtils.toObject(nominals)), atmTest.getNominals());
    Assertions.assertEquals(expectedCombination, actualCombination);
    Assertions.assertEquals(expectedNumberOfCombination, actualNumberOfCombination);
  }

  @Test
  void testSimple2reverse() {
    long targetSum = 6;
    int[] nominals = {3, 2, 1};
    List<Integer> listNominals = Arrays.stream(nominals).sorted().distinct().boxed().toList();
    Atm atmTest = new Atm(nominals, targetSum);
    List<List<Integer>> actualCombination = atmTest.getResultCombination();
    long actualNumberOfCombination = atmTest.getNumberOfCombinations();
    atmTest.nominalsCheck(nominals);
    List<Integer> actualNominals = atmTest.getNominals();
    actualNominals = actualNominals.stream().sorted().toList();

    List<List<Integer>> expectedCombination = new ArrayList<>();
    int[] arr1 = {3, 3};
    int[] arr2 = {2, 2, 2};
    int[] arr3 = {3, 2, 1};
    int[] arr4 = {2, 2, 1, 1};
    int[] arr5 = {3, 1, 1, 1};
    int[] arr6 = {2, 1, 1, 1, 1};
    int[] arr7 = {1, 1, 1, 1, 1, 1};
    expectedCombination.add(List.of(ArrayUtils.toObject(arr7)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr6)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr4)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr2)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr5)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr3)));
    expectedCombination.add(List.of(ArrayUtils.toObject(arr1)));
    long expectedNumberOfCombination = 7;

    Assertions.assertEquals(expectedNumberOfCombination, atmTest.getNumberOfCombinations());
    Assertions.assertEquals(listNominals, actualNominals);
    Assertions.assertEquals(expectedCombination, actualCombination);
    Assertions.assertEquals(expectedNumberOfCombination, actualNumberOfCombination);
  }

  @Test
  void testSimple3() {
    long targetSum = 1000;
    int[] nominals = {1};
    Atm atmTest = new Atm(nominals, targetSum);
    List<List<Integer>> actualCombination = atmTest.getResultCombination();
    long actualNumberOfCombination = atmTest.getNumberOfCombinations();

    List<List<Integer>> expectedCombination = new ArrayList<>();
    expectedCombination.add(new ArrayList<>());
    for (int i = 0; i < 1000; i++) {
      expectedCombination.get(0).add(1);
    }

    long expectedNumberOfCombination = 1;

    Assertions.assertEquals(List.of(ArrayUtils.toObject(nominals)), atmTest.getNominals());
    Assertions.assertEquals(expectedCombination, actualCombination);
    Assertions.assertEquals(expectedNumberOfCombination, actualNumberOfCombination);
  }
}