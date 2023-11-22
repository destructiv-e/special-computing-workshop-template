package ru.spbu.apcyb.svp.tasks.stack;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomStackTest {

  @Test
  void testPush() {
    CustomStack<Integer> stack1 = new CustomStack<>();
    CustomStack<Integer> stack2 = new CustomStack<>(new Integer[]{1});
    CustomStack<Integer> stack3 = new CustomStack<>(new Integer[]{1, null, 2});

    stack1.push(1);
    Assertions.assertEquals(stack2, stack1);

    stack1.push(null);
    stack1.push(2);
    Assertions.assertEquals(stack3, stack1);
  }

  @Test
  void testPop() {
    CustomStack<Integer> stack1 = new CustomStack<>();
    CustomStack<Integer> stack2 = new CustomStack<>(new Integer[]{1});
    Assertions.assertThrows(EmptyStackException.class, stack1::pop);

    stack1.push(1);
    stack1.push(2);
    Assertions.assertEquals(2, stack1.pop());

    Assertions.assertEquals(stack2, stack1);
  }

  @Test
  void testPeek() {
    CustomStack<Integer> testStack = new CustomStack<>();
    Assertions.assertThrows(EmptyStackException.class, testStack::peek);

    testStack.push(1);
    Assertions.assertEquals(1, testStack.peek());

    testStack.push(3);
    Assertions.assertEquals(3, testStack.peek());
    Assertions.assertEquals(3, testStack.peek());

  }

  @Test
  void testIsEmpty() {
    String a = "a";
    CustomStack<Integer> stack1 = new CustomStack<>();
    CustomStack<Integer> stack2 = new CustomStack<>(new Integer[0]);
    CustomStack<Integer> stack3 = new CustomStack<>(new Integer[]{});
    CustomStack<Integer> stack4 = new CustomStack<>(new Integer[1]);
    CustomStack<Integer> stack5 = new CustomStack<>(new Integer[]{2, 3});
    CustomStack<String> stack6 = new CustomStack<>(new String[]{a});

    Assertions.assertTrue(stack1.isEmpty());
    Assertions.assertTrue(stack2.isEmpty());
    Assertions.assertTrue(stack3.isEmpty());
    Assertions.assertFalse(stack4.isEmpty());
    Assertions.assertFalse(stack5.isEmpty());
    Assertions.assertFalse(stack6.isEmpty());
  }

  @Test
  void testEquals() {
    String a = "a";
    CustomStack<Integer> stack1 = new CustomStack<>(new Integer[]{1, 2, 3});
    CustomStack<Integer> stack2 = new CustomStack<>(new Integer[]{1, 2, 3});
    CustomStack<Integer> stack3 = new CustomStack<>(new Integer[]{1, 2, 4});
    CustomStack<Integer> stack4 = new CustomStack<>(new Integer[]{});
    CustomStack<Integer> stack5 = new CustomStack<>();
    CustomStack<Integer> stack6 = new CustomStack<>(new Integer[]{null});
    CustomStack<Integer> stack7 = new CustomStack<>(new Integer[]{null});
    CustomStack<String> stack8 = new CustomStack<>(new String[]{a});
    CustomStack<String> stack9 = new CustomStack<>(new String[]{a});

    Assertions.assertEquals(stack1, stack2);
    Assertions.assertNotEquals(stack1, stack3);
    Assertions.assertNotEquals(stack1, stack4);
    Assertions.assertEquals(stack4, stack5);
    Assertions.assertEquals(stack6, stack7);
    Assertions.assertEquals(stack8, stack9);
  }

}