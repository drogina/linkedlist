/**
 * This class tests all functions in LinkedList
 * Author: Deborah Rogina
 * Date: 9/3/2013
 */

public class LinkedListTest {

    private static final String OOB = "Index %s is out of bounds.";
    private LinkedList<String> listStr;
    private LinkedList<Integer> listInt;

    public static void main(String... args) {

        LinkedListTest test = new LinkedListTest();

        test.run();

    }

    public void run() {

        LinkedList<String> listStr1;
        LinkedList<Integer> listInt1;

        String[] arrStr = new String[]{"A", "B", "C", "D", "E"};
        listStr = new LinkedList<String>();
        listStr1 = new LinkedList<String>();

        Integer[] arrInt = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        listInt = new LinkedList<Integer>();
        listInt1 = new LinkedList<Integer>();

        //creates list by array
        //ouputs list contents with toString()
        createList(arrStr, listStr);
        createList(arrStr, listStr1);
        createList(arrInt, listInt);
        createList(arrInt, listInt1);

        //test equals()
        testEquals(listStr, listStr1);
        testEquals(listInt, listInt1);

        listStr1.add("O");
        listInt1.add(45);

        testEquals(listStr, listStr1);
        testEquals(listInt, listInt1);

        //test appending to end
        //outputs list contents
        testAdd("D");
        testAdd(6);

        //test adding to beginning
        //outputs list contents
        testAdd("Beginning", 0);
        testAdd(0, 0);

        //test adding to middle
        //outputs list contents
        testAdd("Middle", 4);
        testAdd(25, 5);

        //test adding to index out of bounds
        testAdd("OOB", -1);
        testAdd("OOB", listStr.size());

        //test get()
        testGet(0, listStr);
        testGet(5, listStr);
        testGet(listStr.size(), listStr);

        testGet(0, listInt);
        testGet(7, listInt);
        testGet(listInt.size(), listInt);

        //test indexOf()
        testIndexOf("D", listStr);
        testIndexOf("F", listStr);
        testIndexOf("a", listStr);

        testIndexOf(0, listInt);
        testIndexOf(6, listInt);

        //test remove()
        testRemove(0, listStr);
        testRemove(5, listStr);
        testRemove(listStr.size(), listStr);

        testRemove(0, listInt);
        testRemove(3, listInt);
        testRemove(listInt.size(), listInt);

        //test set()
        testSet(0, "Z");
        testSet(3, "P");
        testSet(listStr.size(), "Q");

        testSet(0, 100);
        testSet(7, 200);
        testSet(listInt.size(), 150);

        //test isEmpty()
        testIsEmpty(listStr);
        testIsEmpty(listInt);
        //clear lists
        listStr.clear();
        listInt.clear();
        //retest isEmpty()
        testIsEmpty(listStr);
        testIsEmpty(listInt);


    }

    //adds String array items to linked list
    public void createList(String[] array, LinkedList list) {
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }

        System.out.println(String.format("\ncreateList:\n%s\nSize: %s", listStr.toString(), listStr.size()));
    }

    //adds Integer array items to linked list
    public void createList(Integer[] array, LinkedList list) {
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }

        System.out.println(String.format("\ncreateList:\n%s\nSize: %s", listInt.toString(), listInt.size()));
    }

    //tests adding String value to end of the linked list
    public void testAdd(String value) {
        System.out.println(String.format("\ntestAdd: [%s]", listStr.toString()));

        listStr.add(value);

        System.out.println(String.format("Add %s\n%s", value, listStr.toString()));
    }

    //tests adding Integer value to end of the linked list
    public void testAdd(Integer value) {
        System.out.println(String.format("\ntestAdd: [%s]", listInt.toString()));

        listInt.add(value);

        System.out.println(String.format("Add %s\n%s", value.toString(), listInt.toString()));
    }

    //tests adding String value to specified index
    //handles IndexOutOfBoundsException
    public void testAdd(String value, int index) {
        System.out.println(String.format("\ntestAdd: [%s]\nAdd %s at index %s", listStr.toString(), value, index));
        try {
            listStr.add(index, value);

            System.out.println(listStr.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format(OOB, index));
        }
    }

    //tests adding Integer value to specified index
    //handles IndexOutOfBoundsException
    public void testAdd(Integer value, int index) {
        System.out.println(String.format("\ntestAdd: [%s]\nAdd %s at index %s", listInt.toString(), value.toString(), index));
        try {
            listInt.add(index, value);

            System.out.println(listInt.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format(OOB, index));
        }

    }

    //tests getting value at specified index in the specified linked list
    //handles IndexOutOfBoundsException
    public void testGet(int index, LinkedList list) {
        System.out.println(String.format("\ntestGet: [%s]", list.toString()));
        try {
            System.out.println(String.format("The value at index %s is %s", index, list.get(index)));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format(OOB, index));
        }
    }

    //tests retrieving the index of a specified value in the specified linked list
    public void testIndexOf(Object obj, LinkedList list) {
        System.out.println(String.format("\ntestIndexOf: [%s]\nThe index of %s is %s.", list.toString(), obj, list.indexOf(obj)));
    }

    //tests whether the specified list is empty or not
    public void testIsEmpty(LinkedList list) {
        System.out.println("\ntestIsEmpty:");
        if (list.isEmpty()) {
            System.out.println(String.format("List is empty."));
        } else {
            System.out.println(String.format("List is not empty: [%s]", list.toString()));
        }
    }

    //tests removing value from the specified list at the specified index
    //handles IndexOutOfBoundsException
    public void testRemove(int index, LinkedList list) {
        System.out.println(String.format("\ntestRemove: [%s]\nRemove element at index %s", list.toString(), index));
        try {
            list.remove(index);
            System.out.println(list.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format(OOB, index));
        }
    }

    //tests replacing an element at a specified index with the specified String value
    //handles IndexOutOfBoundsException
    public void testSet(int index, String str) {
        System.out.println(String.format("\ntestSet: [%s]\nSet element at index %s to %s", listStr.toString(), index, str));
        try {
            listStr.set(index, str);
            System.out.println(listStr.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format(OOB, index));
        }
    }

    //tests replacing an element at a specified index with the specified Integer value
    //handles IndexOutOfBoundsExecption
    public void testSet(int index, Integer i) {
        System.out.println(String.format("\ntestSet: [%s]\nSet element at index %s to %s", listInt.toString(), index, i));
        try {
            listInt.set(index, i);
            System.out.println(listInt.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format(OOB, index));
        }
    }

    //tests comparing two linked lists
    public void testEquals(LinkedList list1, LinkedList list2) {
        System.out.println("\ntestEquals:");
        if (list1.equals(list2)) {
            System.out.println(String.format("%s equals %s", list1.toString(), list2.toString()));
        } else {
            System.out.println(String.format("%s does not equal %s", list1.toString(), list2.toString()));
        }
    }

}
