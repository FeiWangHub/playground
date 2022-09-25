package com.fei.playground.algorithm.company.jerryai;

/**
 * A simple unit test with SDK assert, for demo purpose
 * use "java -ea RangeListTest" to run with assertion enabled
 */
public class RangeListTest {

    public static void main(String[] args) {
        RangeListTest test = new RangeListTest();
        test.testAddRemoveAndPrint();
        //test.testIsValidInput();
        //test.testPrintWhenListEmpty();
    }

    public void testIsValidInput() {
        RangeList r = new RangeList();

        assert r.isValidInput(new int[]{1, 2});
        assert r.isValidInput(new int[]{-10, 2});
        assert !r.isValidInput(new int[]{2, 2});
        assert !r.isValidInput(new int[]{3, 2});
    }

    public void testPrintWhenListEmpty() {
        RangeList r = new RangeList();
        assert "".equals(r.print());
    }

    public void testAddRemoveAndPrint() {
        RangeList r = new RangeList();

        r.add(new int[]{1, 5});
        assert r.print().equals("[1,5)") : "Result should be: [1,5)";

        r.add(new int[]{10, 20});
        assert r.print().equals("[1,5) [10,20)") : "Result should be: [1,5) [10,20)";

        r.add(new int[]{20, 20});
        assert r.print().equals("[1,5) [10,20)") : "Result should be: [1,5) [10,20)";

        r.add(new int[]{20, 21});
        assert r.print().equals("[1,5) [10,21)") : "Result should be: [1,5) [10,21)";

        r.add(new int[]{2, 4});
        assert r.print().equals("[1,5) [10,21)") : "Result should be: [1,5) [10,21)";

        r.add(new int[]{3, 8});
        assert r.print().equals("[1,8) [10,21)") : "Result should be: [1,8) [10,21)";

        r.remove(new int[]{10, 10});
        assert r.print().equals("[1,8) [10,21)") : "Result should be: [1,8) [10,21)";

        r.remove(new int[]{10, 11});
        assert r.print().equals("[1,8) [11,21)") : "Result should be: [1,8) [11,21)";

        r.remove(new int[]{15, 17});
        assert r.print().equals("[1,8) [11,15) [17,21)") : "Result should be: [1,8) [11,15) [17,21)";

        r.remove(new int[]{3, 19});
        assert r.print().equals("[1,3) [19,21)") : "Result should be: [1,3) [19,21)";
    }
}
