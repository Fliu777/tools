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

package tl.lin.data.cfd;

import static org.junit.Assert.assertEquals;

public class Object2IntConditionalFrequencyDistributionTestBase {
  protected void test1Common(Object2IntConditionalFrequencyDistribution<String> cfd) {
    cfd.set("a", "a", 2);
    cfd.check();

    assertEquals(2, cfd.get("a", "a"));
    assertEquals(2, cfd.getSumOfAllCounts());

    cfd.set("b", "a", 3);
    cfd.check();

    assertEquals(2, cfd.get("a", "a"));
    assertEquals(3, cfd.get("b", "a"));
    assertEquals(5, cfd.getSumOfAllCounts());

    cfd.set("c", "a", 10);
    cfd.check();

    assertEquals(2, cfd.get("a", "a"));
    assertEquals(3, cfd.get("b", "a"));
    assertEquals(10, cfd.get("c", "a"));
    assertEquals(15, cfd.getSumOfAllCounts());

    cfd.set("x", "b", 1);
    cfd.check();

    assertEquals(2, cfd.get("a", "a"));
    assertEquals(3, cfd.get("b", "a"));
    assertEquals(10, cfd.get("c", "a"));
    assertEquals(1, cfd.get("x", "b"));
    assertEquals(16, cfd.getSumOfAllCounts());

    cfd.set("a", "a", 5);
    cfd.check();

    assertEquals(5, cfd.get("a", "a"));
    assertEquals(3, cfd.get("b", "a"));
    assertEquals(10, cfd.get("c", "a"));
    assertEquals(1, cfd.get("x", "b"));
    assertEquals(19, cfd.getSumOfAllCounts());
  }

  protected void test2Common(Object2IntConditionalFrequencyDistribution<String> cfd) {
    cfd.set("a", "a", 2);
    cfd.check();

    assertEquals(2, cfd.get("a", "a"));
    assertEquals(2, cfd.getSumOfAllCounts());

    cfd.increment("a", "a");
    cfd.check();
    assertEquals(3, cfd.get("a", "a"));
    assertEquals(3, cfd.getSumOfAllCounts());

    cfd.increment("a", "a", 2);
    cfd.check();
    assertEquals(5, cfd.get("a", "a"));
    assertEquals(5, cfd.getSumOfAllCounts());

    cfd.increment("b", "a");
    cfd.check();
    assertEquals(5, cfd.get("a", "a"));
    assertEquals(1, cfd.get("b", "a"));
    assertEquals(6, cfd.getSumOfAllCounts());

    cfd.increment("a", "b", 10);
    cfd.check();
    assertEquals(5, cfd.get("a", "a"));
    assertEquals(1, cfd.get("b", "a"));
    assertEquals(10, cfd.get("a", "b"));
    assertEquals(16, cfd.getSumOfAllCounts());
  }

  protected void test3Common(Object2IntConditionalFrequencyDistribution<String> cfd) {
    cfd.set("a", "a", 2);
    cfd.set("a", "b", 5);
    cfd.set("a", "c", 6);
    cfd.set("a", "d", 4);
    cfd.set("b", "a", 3);
    cfd.set("c", "a", 7);
    cfd.check();

    assertEquals(17, cfd.getMarginalCount("a"));
    assertEquals(27, cfd.getSumOfAllCounts());

    cfd.increment("a", "a", 2);
    cfd.increment("b", "a");

    assertEquals(19, cfd.getMarginalCount("a"));
    assertEquals(4, cfd.getMarginalCount("b"));
    assertEquals(30, cfd.getSumOfAllCounts());
  }

  protected void testLargeMarginalCommon(Object2IntConditionalFrequencyDistribution<String> cfd) {
    cfd.set("1", "2", 2000000000);
    cfd.set("1", "3", 2000000000);
    cfd.set("1", "5", 2000000000);
    cfd.set("1", "1", 2000000000);

    assertEquals(8000000000L, cfd.getMarginalCount("1"));
  }
}
