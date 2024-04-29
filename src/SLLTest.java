import org.junit.*;

public class SLLTest {

    // Define data that will be used to test the SLL class
    public static final String[] abc = { "A", "B", "C" };
    public static final String[] a = { "A" };
    public static final String[] empty = {};
    public static final String[] ab = { "A", "B" };
    public static final String[] b = { "B" };
    public static final String[] cba = { "C", "B", "A" };
    public static final String[] ba = { "B", "A" };
    public static final String[] bac = { "B", "A", "C" };
    public static final String[] abdc = { "A", "B", "D", "C" };
    public static final String[] dac = { "D", "A", "C" };
    public static final String[] eb = { "E", "B" };
    public static final String[] dbac = { "D", "B", "A", "C" };
    public static final String[] ac = { "A", "C" };
    public static final String[] e = { "E" };
    public static final String[] debac = { "D", "E", "B", "A", "C" };
    public static final String[] fg = { "F", "G" };
    public static final String[] debacfg = { "D", "E", "B", "A", "C", "F", "G" };
    public static final String[] hi = { "H", "I" };
    public static final String[] hidebacfg = { "H", "I", "D", "E", "B", "A", "C", "F", "G" };
    public static final String[] dbc = { "D", "B", "C" };
    public static final String[] dec = { "D", "E", "C" };
    public static final String[] def = { "D", "E", "F" };

    /* Method to create an SLL, returns SLL*/
    public static <T> SLL<T> makeSLL(T[] arr) {
        SLL<T> list = new SLL<T>();
        int i = arr.length;
        while (i > 0) {
            i--;
            list.addFirst(arr[i]);
        }
        return list;
    }

    /* Method to check an SLL, returns String of SLL elements */
    public static <T> String verifySLL(SLL<T> list, T[] values) {
        String result = "";
        NodeSL<T> here = list.getHead();
        for (int i = 0; i < values.length; i++) {
            if (!((here != null) && (values[i] == here.getData()))) {
                result += "element " + i + "; ";
            }
            if (here == null) {
                break; // list unexpectedly ended early
            }
            here = here.getNext();
        }
        if (here != null) {
            result += "tail";
        }
        if (!result.equals("")) {
            result = " problems at " + result;
        }
        return result;
    }


    @Test
    public void check_methods() {
        SLL<String> list = new SLL<>();
        Assert.assertTrue("empty head is null", null == list.getHead());
        Assert.assertTrue("empty tail is null", null == list.getTail());
        Assert.assertTrue("empty list is empty", list.isEmpty());
    }

    @Test
    public void test_addFirst_1() {
        SLL<String> list = new SLL<>();
        list.addFirst("A");
        
        Assert.assertTrue("list of 1 isn't empty", !list.isEmpty());
        Assert.assertTrue("same head and tail of singleton", list.getHead() == list.getTail());
        Assert.assertTrue("data correct", list.getHead().getData().equals("A"));
        Assert.assertTrue("tail's next is null", null == list.getTail().getNext());
    }

    @Test
    public void test_addFirst_2() {
        SLL<String> list = new SLL<>();

        list.addFirst("A");
        list.addFirst("B");
        Assert.assertTrue("list of 2 isn't empty", !list.isEmpty());
        Assert.assertTrue("second node is tail", list.getHead().getNext() == list.getTail());
        Assert.assertTrue("first element is B", list.getHead().getData().equals("B"));
        Assert.assertTrue("second element is A", list.getHead().getNext().getData().equals("A"));
        Assert.assertTrue("tail's next is null", null == list.getTail().getNext());
    }

    @Test
    public void test_addFirst_3() {
        SLL<String> list = new SLL<>();

        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        Assert.assertTrue("list of 3 isn't empty", !list.isEmpty());
        Assert.assertTrue("third node is tail", list.getHead().getNext().getNext() == list.getTail());
        Assert.assertTrue("first element is C", list.getHead().getData().equals("C"));
        Assert.assertTrue("second element is B", list.getHead().getNext().getData().equals("B"));
        Assert.assertTrue("third element is A", list.getHead().getNext().getNext().getData().equals("A"));
        Assert.assertTrue("tail's next is null", null == list.getTail().getNext());

        String s = verifySLL(list, cba);
        Assert.assertTrue("3-list contents " + s, s.equals(""));
    }

    @Test
    public void test_toString() {
        
        SLL<String> list = new SLL<>();
        Assert.assertTrue("empty list is []", list.toString().equals("[]"));
        list.addFirst("A");
        Assert.assertTrue("list [A]", list.toString().equals("[A]"));
        list.addFirst("B");
        Assert.assertTrue("list [B, A]", list.toString().equals("[B, A]"));
        list.addFirst("C");
        Assert.assertTrue("list [C, B, A]", list.toString().equals("[C, B, A]"));
    }

    @Test
    public void test_removeFirst() {

        SLL<String> list = makeSLL(cba);
        Assert.assertTrue("remove C", list.removeFirst().equals("C"));
        String s = verifySLL(list, ba);
        Assert.assertTrue("removeFirst --> BA " + s, s.equals(""));
        Assert.assertTrue("remove B", list.removeFirst().equals("B"));
        s = verifySLL(list, a);
        Assert.assertTrue("removeFirst --> A " + s, s.equals(""));
        Assert.assertTrue("remove A", list.removeFirst().equals("A"));
        s = verifySLL(list, empty);
        Assert.assertTrue("removeFirst -> empty " + s, s.equals(""));
    }

    @Test
    public void test_addLast() {

        SLL<String> list = new SLL<String>();
        list.addLast("A");
        String s = verifySLL(list, a);
        System.out.println("    List: " + list);
        System.out.println("    String: " + s);
        Assert.assertTrue("addLast -> A " + s, s.equals(""));
        list.addLast("B");
        s = verifySLL(list, ab);
        System.out.println("    List: " + list);
        System.out.println("    String: " + s);
        Assert.assertTrue("addLast -> AB " + s, s.equals(""));
        list.addLast("C");
        s = verifySLL(list, abc);
        Assert.assertTrue("addLast -> ABC " + s, s.equals(""));
    }

    @Test
    public void test_removeLast() {

        SLL<String> list = makeSLL(abc);
        System.out.println("    List: " + list);
        Assert.assertTrue("removeLast C", list.removeLast().equals("C"));
        String s = verifySLL(list, ab);
        //Assert.assertTrue("removeLast -> AB" + s, s.equals(""));
        System.out.println("    S: " + s);
        Assert.assertTrue("removeLast B", list.removeLast().equals("B"));
        s = verifySLL(list, a);
        Assert.assertTrue("removeLast -> A" + s, s.equals(""));
        Assert.assertTrue("removeLast A", list.removeLast().equals("A"));
        s = verifySLL(list, empty);
        Assert.assertTrue("removeLast -> empty" + s, s.equals(""));
    }

    @Test
    public void test_size() {

        SLL<String> list = new SLL<String>();
        Assert.assertTrue("size of empty", 0 == list.size());
        list = makeSLL(a);
        Assert.assertTrue("size of A", 1 == list.size());
        list = makeSLL(ba);
        Assert.assertTrue("size of BA", 2 == list.size());
        list = makeSLL(cba);
        Assert.assertTrue("size of CBA", 3 == list.size());
    }

    @Test
    public void test_addAfter() {

        SLL<String> list = makeSLL(a);
        list.addAfter(list.getHead(), "B");
        String s = verifySLL(list, ab);
        Assert.assertTrue("A.addAfter(A,B)" + s, s.equals(""));
        list.addAfter(list.getTail(), "C");
        s = verifySLL(list, abc);
        Assert.assertTrue("AB.addAfter(B,C)" + s, s.equals(""));
        list.addAfter(list.getHead().getNext(), "D");
        s = verifySLL(list, abdc);
        Assert.assertTrue("ABC.addAfter(B,D)" + s, s.equals(""));

    }

    @Test
    public void test_removeAfter() {
  
        SLL<String> list = makeSLL(abc);
        Assert.assertTrue("ABC after B", list.removeAfter(list.getHead().getNext()).equals("C"));
        String s = verifySLL(list, ab);
        Assert.assertTrue("ABC removeAfter B -> AB" + s, s.equals(""));
        Assert.assertTrue("AB after A", list.removeAfter(list.getHead()).equals("B"));
        verifySLL(list, a);
        Assert.assertTrue("AB removeAfter A -> A" + s, s.equals(""));
        Assert.assertTrue("A after null", list.removeAfter(null).equals("A"));
        s = verifySLL(list, empty);
        Assert.assertTrue("A removeAfter null -> empty" + s, s.equals(""));
    }

   @Test
    public void test_copy_constructor() {
        SLL<String> list = makeSLL(bac);
        String s = verifySLL(list, bac);
        Assert.assertTrue("copy BAC" + s, s.equals(""));
        s = verifySLL(new SLL<String>(), empty);
        Assert.assertTrue("copy empty" + s, s.equals(""));
    }

    @Test
    public void test_subseqByCopy() {

        SLL<String> list = makeSLL(bac);
        SLL<String> list2 = list.subseqByCopy(list.getHead(), 2);
        System.out.println(list2);
        String s = verifySLL(list2, ba);
        System.out.println(list2);
        Assert.assertTrue("BAC.subseqByCopy(B,2) -> BA" + s, s.equals(""));
        s = verifySLL(list, bac);
        Assert.assertTrue("BAC.subseqByCopy(B,2): BAC same" + s, s.equals(""));
        Assert.assertTrue("Not shallow BA", list.getHead().getNext() != list2.getTail());
        list2 = list.subseqByCopy(list.getHead().getNext(), 2);
        s = verifySLL(list2, ac);
        Assert.assertTrue("BAC.subseqByCopy(A,2) -> AC" + s, s.equals(""));
        s = verifySLL(list, bac);
        Assert.assertTrue("BAC.subseqByCopy(A,2): BAC same" + s, s.equals(""));
        Assert.assertTrue("Not shallow AC", list.getTail() != list2.getTail());
    }

    @Test
    public void test_spliceByCopy() {
          
        SLL<String> list = makeSLL(dac);
        SLL<String> list2 = makeSLL(eb);
        list.spliceByCopy(list2, list.getHead());
        String s = verifySLL(list, debac);
        Assert.assertTrue("DAC.spliceByCopy(EB,D) -> DEBAC" + s, s.equals(""));
        s = verifySLL(list2, eb);
        Assert.assertTrue("DAC.spliceByCopy(EB,D): EB same" + s, s.equals(""));
        Assert.assertTrue("Not shallow EB", list.getHead().getNext().getNext() != list2.getTail());
        list = makeSLL(debac);
        list2 = makeSLL(fg);
        list.spliceByCopy(list2, list.getTail());
        s = verifySLL(list, debacfg);
        Assert.assertTrue("DEBAC.spliceByCopy(FG,C) -> DEBACFG" + s, s.equals(""));
        s = verifySLL(list2, fg);
        Assert.assertTrue("DEBAC.spliceByCopy(FG,C): FG same" + s, s.equals(""));
        Assert.assertTrue("Not shallow FG", list.getTail() != list2.getTail());
        list = makeSLL(debacfg);
        list2 = makeSLL(hi);
        list.spliceByCopy(list2, null);
        s = verifySLL(list, hidebacfg);
        Assert.assertTrue("DEBACFG.spliceByCopy(HI,null) -> HIDEBACFG" + s, s.equals(""));
        s = verifySLL(list2, hi);
        Assert.assertTrue("DEBACFG.spliceByCopy(HI,null): HI same" + s, s.equals(""));
        Assert.assertTrue("Not shallow HI", list.getHead().getNext() != list2.getTail());
        list = makeSLL(abc);
        list2 = makeSLL(empty);
        list.spliceByCopy(list2, list.getHead().getNext());
        s = verifySLL(list, abc);
        Assert.assertTrue("ABC.spliceByCopy(empty,B) -> ABC" + s, s.equals(""));

    }

    @Test
    public void test_subseqByTransfer() {

        SLL<String> list = makeSLL(debac);
        SLL<String> list2 = list.subseqByTransfer(list.getHead(), list.getHead().getNext().getNext());
        String s = verifySLL(list2, eb);
        Assert.assertTrue("DEBAC.subseqByTransfer(D,B) -> EB" + s, s.equals(""));
        s = verifySLL(list, dac);
        Assert.assertTrue("DEBAC.subseqByTransfer(D,B) becomes DAC" + s, s.equals(""));
        list = makeSLL(abc);
        list2 = list.subseqByTransfer(list.getHead(), list.getHead().getNext());
        s = verifySLL(list2, b);
        Assert.assertTrue("ABC.subseqByTransfer(A,B) -> B" + s, s.equals(""));
        s = verifySLL(list, ac);
        Assert.assertTrue("ABC.subseqByTransfer(A,C) becomes AC" + s, s.equals(""));
        list = makeSLL(debacfg);
        list2 = list.subseqByTransfer(list.getHead().getNext().getNext().getNext().getNext(), list.getTail());
        s = verifySLL(list2, fg);
        Assert.assertTrue("DEBACFG.subseqByTransfer(C,G) -> FG" + s, s.equals(""));
        s = verifySLL(list, debac);
        Assert.assertTrue("DEBACFG.subseqByTransfer(C,G) becomes DEBAC" + s, s.equals(""));
        list = makeSLL(hidebacfg);
        list2 = list.subseqByTransfer(null, list.getHead().getNext());
        s = verifySLL(list2, hi);
        Assert.assertTrue("HIDEBACFG.subseqByTransfer(null,I) -> HI" + s, s.equals(""));
        s = verifySLL(list, debacfg);
        Assert.assertTrue("HIDEBACFG.subseqByTransfer(null,I) becomes DEBACFG" + s, s.equals(""));

    }

    @Test
    public void test_spliceByTransfer() {

        SLL<String> list = makeSLL(dbac);
        SLL<String> list2 = makeSLL(e);
        list.spliceByTransfer(list2, list.getHead());
        String s = verifySLL(list, debac);
        Assert.assertTrue("DBAC.spliceByTransfer(E,D) -> DEBAC" + s, s.equals(""));
        s = verifySLL(list2, empty);
        Assert.assertTrue("DBAC.spliceByTransfer(E,D) empties E" + s, s.equals(""));
        list = makeSLL(debac);
        list2 = makeSLL(fg);
        list.spliceByTransfer(list2, list.getHead().getNext().getNext().getNext().getNext());
        s = verifySLL(list, debacfg);
        Assert.assertTrue("DEBAC.spliceByTransfer(E,D) -> DEBACFG" + s, s.equals(""));
        s = verifySLL(list2, empty);
        Assert.assertTrue("DEBAC.spliceByTransfer(E,D) empties FG" + s, s.equals(""));
        list = makeSLL(debacfg);
        list2 = makeSLL(hi);
        list.spliceByTransfer(list2, null);
        s = verifySLL(list, hidebacfg);
        Assert.assertTrue("DEBACFG.spliceByTransfer(HI,null) -> HIDEBACFG" + s, s.equals(""));
        s = verifySLL(list2, empty);
        Assert.assertTrue("DEBACFG.spliceByTransfer(HI,null) empties HI" + s, s.equals(""));
    }

    @Test
    public void test_MEE1() {

        SLL<String> list = new SLL<>();

        Assert.assertThrows("removeFirst from empty list",
                MissingElementException.class,
                list::removeFirst);
    }

    @Test
    public void test_MEE2() {

        SLL<String> list = new SLL<>();
        Assert.assertThrows("removeLast from empty list",
                MissingElementException.class,
                list::removeLast);
    }

    @Test
    public void test_MEE3() {
        SLL<String> list = new SLL<String>();
        Assert.assertThrows("removeAfter from empty list",
                MissingElementException.class,
                () -> {
                    list.removeAfter(null);
                });
    }

    @Test
    public void test_SIE1() {
        String[] ab = { "A", "B" };
        SLL<String> list = makeSLL(ab);

        Assert.assertThrows("self spliceByTransfer",
                SelfInsertException.class,
                () -> {
                    list.spliceByTransfer(list, list.getHead());
                });
    }

    @Test
    public void test_SIE2() {

        String[] ab = { "A", "B" };
        SLL<String> list = makeSLL(ab);

        Assert.assertThrows("self spliceByTransfer",
                SelfInsertException.class,
                () -> {
                    list.spliceByCopy(list, list.getHead());
                });
    }
}