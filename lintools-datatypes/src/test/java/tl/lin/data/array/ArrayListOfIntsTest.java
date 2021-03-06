/*
 * Lintools: tools by @lintool
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package tl.lin.data.array;

import junit.framework.JUnit4TestAdapter;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayListOfIntsTest {
  int neg_one = -1, zero = 0, one = 1, two = 2, three = 3, four = 4, five = 5, six = 6, seven = 7,
      nine = 9;

  @Test
  public void testInitialSize() {
    ArrayListOfInts a = new ArrayListOfInts();
    assertEquals(0, a.size());
  }

  @Test
  public void testSetBounds() {
    ArrayListOfInts a = new ArrayListOfInts(10);
    assertEquals(0, a.size());

    a.set(0, 1);
    assertEquals(1, a.size());

    a.set(5, 1);
    assertEquals(6, a.size());

    a.set(1, 1);
    assertEquals(6, a.size());

    a.set(9, 1);
    assertEquals(10, a.size());

    a.set(99, 1);
    assertEquals(100, a.size());

    a.set(0, 2);
    assertEquals(100, a.size());
  }

  @Test
  public void testRemoveWithinBounds() {
    ArrayListOfInts a = new ArrayListOfInts();
    a.add(one).add(three).add(five).add(seven);

    assertTrue(one == a.remove(0));

    assertTrue(three == a.get(0));
    assertTrue(five == a.get(1));

    assertTrue(five == a.remove(1));
    assertTrue(seven == a.get(2));
  }

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testRemoveOutOfBounds() {
    ArrayListOfInts a = new ArrayListOfInts();
    a.add(one).add(three).add(five).add(seven);

    a.remove(4);
  }

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testRemoveOutOfBounds2() {
    ArrayListOfInts a = new ArrayListOfInts();
    a.add(neg_one);
    a.remove(-1);
  }

  @Test
  public void testBasic1() {
    int size = 100000;
    Random r = new Random();
    int[] ints = new int[size];

    ArrayListOfInts list = new ArrayListOfInts();
    for (int i = 0; i < size; i++) {
      int k = r.nextInt(size);
      list.add(k);
      ints[i] = k;
    }

    for (int i = 0; i < size; i++) {
      int v = list.get(i);

      assertEquals(ints[i], v);
    }
  }

  @Test
  public void testArrayConstructor() {
    int[] arr = new int[] { 1, 2, 3, 4, 5 };
    assertEquals(5, arr.length);

    ArrayListOfInts list = new ArrayListOfInts(arr);
    list.remove(2);

    // Make sure the original array remains untouched.
    assertEquals(1, arr[0]);
    assertEquals(2, arr[1]);
    assertEquals(3, arr[2]);
    assertEquals(4, arr[3]);
    assertEquals(5, arr[4]);
  }

  @Test
  public void testRemove() {
    ArrayListOfInts list = new ArrayListOfInts();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }

    list.remove(list.indexOf(5));
    assertEquals(9, list.size());
    assertEquals(0, list.get(0));
    assertEquals(1, list.get(1));
    assertEquals(2, list.get(2));
    assertEquals(3, list.get(3));
    assertEquals(4, list.get(4));
    assertEquals(6, list.get(5));
    assertEquals(7, list.get(6));
    assertEquals(8, list.get(7));
    assertEquals(9, list.get(8));

    list.remove(list.indexOf(9));
    assertEquals(8, list.size());
    assertEquals(0, list.get(0));
    assertEquals(1, list.get(1));
    assertEquals(2, list.get(2));
    assertEquals(3, list.get(3));
    assertEquals(4, list.get(4));
    assertEquals(6, list.get(5));
    assertEquals(7, list.get(6));
    assertEquals(8, list.get(7));
  }

  @Test
  public void testUpdate() {
    int size = 100000;
    Random r = new Random();
    int[] ints = new int[size];

    ArrayListOfInts list = new ArrayListOfInts();
    for (int i = 0; i < size; i++) {
      int k = r.nextInt(size);
      list.add(k);
      ints[i] = k;
    }

    assertEquals(size, list.size());

    for (int i = 0; i < size; i++) {
      list.set(i, ints[i] + 1);
    }

    assertEquals(size, list.size());

    for (int i = 0; i < size; i++) {
      int v = list.get(i);

      assertEquals(ints[i] + 1, v);
    }

  }

  @Test
  public void testTrim1() {
    int size = 89;
    Random r = new Random();
    int[] ints = new int[size];

    ArrayListOfInts list = new ArrayListOfInts();
    for (int i = 0; i < size; i++) {
      int k = r.nextInt(size);
      list.add(k);
      ints[i] = k;
    }

    for (int i = 0; i < size; i++) {
      int v = list.get(i);

      assertEquals(ints[i], v);
    }

    int[] rawArray = list.getArray();
    int lenBefore = rawArray.length;

    list.trimToSize();
    int[] rawArrayAfter = list.getArray();
    int lenAfter = rawArrayAfter.length;

    assertEquals(89, lenAfter);
    assertTrue(lenBefore > lenAfter);
  }

  @Test
  public void testClone() {
    int size = 100000;
    Random r = new Random();
    int[] ints = new int[size];

    ArrayListOfInts list1 = new ArrayListOfInts();
    for (int i = 0; i < size; i++) {
      int k = r.nextInt(size);
      list1.add(k);
      ints[i] = k;
    }

    ArrayListOfInts list2 = list1.clone();

    assertEquals(size, list1.size());
    assertEquals(size, list2.size());

    for (int i = 0; i < size; i++) {
      list2.set(i, ints[i] + 1);
    }

    // values in old list should not have changed
    assertEquals(size, list1.size());
    for (int i = 0; i < size; i++) {
      assertEquals(ints[i], list1.get(i));
    }

    // however, values in new list should have changed
    assertEquals(size, list1.size());
    for (int i = 0; i < size; i++) {
      assertEquals(ints[i] + 1, list2.get(i));
    }
  }

  @Test
  public void testToString1() {
    assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", new ArrayListOfInts(1, 11).toString());
    assertEquals("[1, 2, 3, 4, 5 ... (5 more) ]", new ArrayListOfInts(1, 11).toString(5));

    assertEquals("[1, 2, 3, 4, 5]", new ArrayListOfInts(1, 6).toString());
    assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]", new ArrayListOfInts(1, 12).toString(11));

    assertEquals("[]", new ArrayListOfInts().toString());
  }

  @Test
  public void testToString2() {
    int size = 10;
    Random r = new Random();

    ArrayListOfInts list = new ArrayListOfInts();
    while (list.size < size) {
      int next = r.nextInt(1000000);
      if (!list.contains(next)) {
        list.add(next);
      }
    }

    String out = list.toString();
    for (int i = 0; i < size; i++) {
      int v = list.get(i);

      // Make sure the first 10 elements are printed out.
      assertTrue(out.indexOf(new Integer(v).toString()) != -1);
    }

    while (list.size < size * 2) {
      int next = r.nextInt(1000000);
      if (!list.contains(next)) {
        list.add(next);
      }
    }

    out = list.toString();
    for (int i = size; i < size + size; i++) {
      int v = list.get(i);

      // Make sure these elements are not printed out.
      assertTrue(out.indexOf(new Integer(v).toString()) == -1);
    }

    assertTrue(out.indexOf(size + " more") != -1);
  }

  @Test
  public void testIterable() {
    int size = 1000;
    Random r = new Random();
    int[] ints = new int[size];

    ArrayListOfInts list = new ArrayListOfInts();
    for (int i = 0; i < size; i++) {
      int k = r.nextInt(size);
      list.add(k);
      ints[i] = k;
    }

    int i = 0;
    for (Integer v : list) {
      assertEquals(ints[i++], (int) v);
    }

  }

  @Test
  public void testSetSize() {
    ArrayListOfInts list = new ArrayListOfInts();

    list.add(5);
    assertEquals(1, list.size);
    assertEquals(5, list.get(0));

    list.setSize(5);
    assertEquals(5, list.size);
    assertEquals(0, list.get(1));
    assertEquals(0, list.get(2));
    assertEquals(0, list.get(3));
    assertEquals(0, list.get(4));

    list.add(12);
    assertEquals(6, list.size);
    assertEquals(12, list.get(5));
  }

  @Test
  public void testSort() {
    ArrayListOfInts a = new ArrayListOfInts();
    assertEquals(0, a.size());

    a.add(5).add(6).add(1).add(4);
    assertEquals(4, a.size());

    a.sort();
    assertEquals(4, a.size());

    assertEquals(1, a.get(0));
    assertEquals(4, a.get(1));
    assertEquals(5, a.get(2));
    assertEquals(6, a.get(3));
  }

  @Test
  public void testIntersection1() {
    ArrayListOfInts a = new ArrayListOfInts();
    a.add(5).add(3).add(1);

    a.sort();

    ArrayListOfInts b = new ArrayListOfInts();
    b.add(0).add(1).add(2).add(3);

    ArrayListOfInts c = a.intersection(b);

    assertEquals(1, c.get(0));
    assertEquals(3, c.get(1));
    assertEquals(2, c.size());
  }

  @Test
  public void testIntersection2() {
    ArrayListOfInts a = new ArrayListOfInts();
    a.add(5);

    ArrayListOfInts b = new ArrayListOfInts();
    b.add(0).add(1).add(2).add(3);

    ArrayListOfInts c = a.intersection(b);
    assertTrue(c.size() == 0);
  }

  @Test
  public void testIntersection3() {
    ArrayListOfInts a = new ArrayListOfInts();
    a.add(3).add(5).add(7).add(8).add(9);

    ArrayListOfInts b = new ArrayListOfInts();
    b.add(0).add(1).add(2).add(3);

    ArrayListOfInts c = a.intersection(b);

    assertEquals(3, c.get(0));
    assertEquals(1, c.size());
  }

  @Test
  public void testMerge1() {
    // CASE: interleaved

    ArrayListOfInts a = new ArrayListOfInts();
    a.add(3);
    a.add(7);
    a.add(10);

    ArrayListOfInts b = new ArrayListOfInts();
    b.add(0);
    b.add(4);
    b.add(9);

    ArrayListOfInts c = a.merge(b);

    assertEquals(6, c.size());
    assertEquals(0, c.get(0));
    assertEquals(3, c.get(1));
    assertEquals(4, c.get(2));
    assertEquals(7, c.get(3));
    assertEquals(9, c.get(4));
    assertEquals(10, c.get(5));

    // c should be same as c2
    ArrayListOfInts c2 = b.merge(a);
    assertEquals(c, c2);
  }

  @Test
  public void testMerge2() {
    // CASE: append

    ArrayListOfInts a = new ArrayListOfInts();
    a.add(3);
    a.add(7);
    a.add(10);

    ArrayListOfInts b = new ArrayListOfInts();
    b.add(11);
    b.add(19);
    b.add(21);

    ArrayListOfInts c = a.merge(b);

    assertEquals(6, c.size());
    assertEquals(3, c.get(0));
    assertEquals(7, c.get(1));
    assertEquals(10, c.get(2));
    assertEquals(11, c.get(3));
    assertEquals(19, c.get(4));
    assertEquals(21, c.get(5));

    ArrayListOfInts c2 = b.merge(a);
    assertEquals(c, c2);
  }

  @Test
  public void testMerge3() {
    // CASE: one of the lists are empty

    ArrayListOfInts a = new ArrayListOfInts();
    a.add(3);
    a.add(7);
    a.add(10);

    ArrayListOfInts b = new ArrayListOfInts();

    ArrayListOfInts c = a.merge(b);
    assertEquals(c, a);

    ArrayListOfInts c2 = b.merge(a);
    assertEquals(c, c2);
  }

  @Test
  public void testMerge4() {
    // CASE: both lists have the same object

    ArrayListOfInts a = new ArrayListOfInts();
    a.add(3);
    a.add(7);
    a.add(10);

    ArrayListOfInts b = new ArrayListOfInts();
    b.add(7);
    b.add(8);

    ArrayListOfInts c = a.merge(b);
    assertEquals(c.size(), 5);
    assertEquals(c.get(0), 3);
    assertEquals(c.get(1), 7);
    assertEquals(c.get(2), 7);
    assertEquals(c.get(3), 8);
    assertEquals(c.get(4), 10);

    ArrayListOfInts cNoDups = a.mergeNoDuplicates(b);
    assertEquals(cNoDups.size(), 4);
    assertEquals(cNoDups.get(0), 3);
    assertEquals(cNoDups.get(1), 7);
    assertEquals(cNoDups.get(2), 8);
    assertEquals(cNoDups.get(3), 10);
  }

  @Test
  public void testMerge5() {
    // CASE: both lists have the same object

    ArrayListOfInts a = new ArrayListOfInts();
    a.add(3);
    a.add(7);
    a.add(10);

    ArrayListOfInts b = new ArrayListOfInts();
    b.add(7);

    ArrayListOfInts c = a.merge(b);
    assertEquals(c.size(), 4);
    assertEquals(c.get(0), 3);
    assertEquals(c.get(1), 7);
    assertEquals(c.get(2), 7);
    assertEquals(c.get(3), 10);

    ArrayListOfInts cNoDups = a.mergeNoDuplicates(b);
    assertEquals(cNoDups.size(), 3);
    assertEquals(cNoDups.get(0), 3);
    assertEquals(cNoDups.get(1), 7);
    assertEquals(cNoDups.get(2), 10);
  }

  @Test
  public void testSubList() {
    ArrayListOfInts a = new ArrayListOfInts(new int[] { 1, 2, 3, 4, 5, 6, 7 });
    ArrayListOfInts b = a.subList(1, 5);
    assertEquals(5, b.size());
    assertEquals(2, b.get(0));
    assertEquals(3, b.get(1));
    assertEquals(4, b.get(2));
    assertEquals(5, b.get(3));
    assertEquals(6, b.get(4));

    a.clear();
    // Make sure b is a new object.
    assertEquals(5, b.size());
    assertEquals(2, b.get(0));
    assertEquals(3, b.get(1));
    assertEquals(4, b.get(2));
    assertEquals(5, b.get(3));
    assertEquals(6, b.get(4));
  }

  @Test
  public void testAddUnique() {
    ArrayListOfInts a = new ArrayListOfInts(new int[] { 1, 2, 3, 4, 5, 6, 7 });
    a.addUnique(new int[] { 8, 0, 2, 5, -1, 11, 9 });
    assertEquals(12, a.size());
    assertEquals(0, a.get(8));
    assertEquals(-1, a.get(9));
    assertEquals(11, a.get(10));
    assertEquals(9, a.get(11));
  }

  @Test
  public void testShift() {
    int size = 100;
    int shift = 10;

    ArrayListOfInts list = new ArrayListOfInts();
    for (int i = 0; i < size; i++)
      list.add(i);
    list.shiftLastNToTop(shift);

    for (int i = 0; i < list.size(); i++) {
      assertEquals(size - shift + i, list.get(i));
    }
    list.add(size);
    assertEquals(shift + 1, list.size());
    assertEquals(size, list.get(shift));
  }

  public static junit.framework.Test suite() {
    return new JUnit4TestAdapter(ArrayListOfIntsTest.class);
  }

}