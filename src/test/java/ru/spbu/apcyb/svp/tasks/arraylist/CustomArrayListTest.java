package ru.spbu.apcyb.svp.tasks.arraylist;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.atm.Main;

class CustomArrayListTest {

  @Test
  void testCheckBoundaries() {
    CustomArrayList<Integer> list1 = new CustomArrayList<>(new Integer[]{1, 2, 3});
    Integer a = -10;
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(5));
  }

  @Test
  void testResize() {
    CustomArrayList<Integer> list1 = new CustomArrayList<>(new Integer[]{1, 2, 3});
    CustomArrayList<Integer> list2 = new CustomArrayList<>(new Integer[]{1, 2, 3, null, null, null});
    list1.add(null);
    list1.add(null);
    list1.add(null);
    Assertions.assertEquals(list1.size(), list2.size());
  }

  @Test
  void testAdd() {
    CustomArrayList<Integer> list1 = new CustomArrayList<>(new Integer[]{1, 2, 3});
    CustomArrayList<Integer> list2 = new CustomArrayList<>(new Integer[]{});
    list2.add(1);
    list2.add(2);
    list2.add(3);
    Assertions.assertEquals(list1, list2);

    list2.add(4);
    Assertions.assertNotEquals(list1, list2);
  }

  @Test
  void testRemove() {
    CustomArrayList<Integer> list1 = new CustomArrayList<>(new Integer[]{1, 2, 3});
    CustomArrayList<Integer> list2 = new CustomArrayList<>(new Integer[]{1, 3});
    list1.remove(1);
    Assertions.assertEquals(list1, list2);
    list1.remove(1);
    Assertions.assertNotEquals(list1, list2);
  }

  @Test
  void testEquals() {
    CustomArrayList<Integer> list1 = new CustomArrayList<>(new Integer[]{1, 2, 3});
    CustomArrayList<Integer> list2 = new CustomArrayList<>(new Integer[]{1, 2, 3});
    CustomArrayList<Integer> list3 = new CustomArrayList<>(new Integer[]{1, 2, 4});
    CustomArrayList<Integer> list4 = new CustomArrayList<>(new Integer[]{});
    CustomArrayList<Integer> list5 = new CustomArrayList<>();
    ArrayList<Integer> list6 = new ArrayList<>(
        IntStream.range(1, 4).boxed().toList());

    Assertions.assertEquals(list1, list2);
    Assertions.assertNotEquals(list1, list3);
    Assertions.assertNotEquals(list1, list4);
    Assertions.assertEquals(list4, list5);
    Assertions.assertNotEquals(list1, list6);

  }

  @Test
  void testAddByIndex() {
    CustomArrayList<Integer> actual = new CustomArrayList<>(new Integer[]{1, 3, 4});
    actual.add(1, 2);
    CustomArrayList<Integer> expected = new CustomArrayList<>(new Integer[]{1, 2, 3, 4});

    Assertions.assertEquals(expected, actual);
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.add(8, 10));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.add(-1, 10));
  }

  @Test
  void testContains() {
    CustomArrayList<Double> testList = new CustomArrayList<>(new Double[]{1.0, -10.1, 13.0, 0.0, 0.0});

    Assertions.assertTrue(testList.contains(-10.1));
    Assertions.assertFalse(testList.contains(-10.0));
  }

  @Test
  void testEmpty() {
    CustomArrayList<Double> notEmptyList = new CustomArrayList<>(new Double[]{3.0});
    CustomArrayList<Double> emptyList1 = new CustomArrayList<>(new Double[]{});
    CustomArrayList<Double> emptyList3 = new CustomArrayList<>(new Double[]{1.0});
    emptyList3.remove(0);

    Assertions.assertTrue(emptyList1.isEmpty());
    Assertions.assertTrue(emptyList3.isEmpty());
    Assertions.assertFalse(notEmptyList.isEmpty());
  }

  @Test
  void testClear() {
    CustomArrayList<Integer> list1 = new CustomArrayList<>(new Integer[]{1, 3, 4});
    CustomArrayList<Integer> list2 = new CustomArrayList<>(new Integer[]{null, null, null});
    list1.clear();
    Assertions.assertEquals(list2, list1);

  }

  @Test
  void testGet() {
    CustomArrayList<Integer> testList = new CustomArrayList<>(new Integer[]{0, 1, 2, 3});

    for (int i = 0; i < 4; i++) {
      Assertions.assertEquals(i, testList.get(i));
    }
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testList.get(-1));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testList.get(5));

  }

  @Test
  void testSize() {
    CustomArrayList<Integer> testList = new CustomArrayList<>();

    Assertions.assertEquals(0, testList.size());
    testList.add(1);
    testList.add(2);
    Assertions.assertEquals(2, testList.size());
    testList.remove(testList.size() - 1);
    Assertions.assertEquals(1, testList.size());
  }
}

