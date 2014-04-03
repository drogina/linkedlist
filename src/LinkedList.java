/**
 * This class implements a List ADT by means of a linked data structure.
 * A List (also known as a <i>sequence</i>) is an ordered collection.
 * Elements in the list can be accessed by their integer index.  The
 * index of the first element in the list is zero.
 * <p/>
 * <p/>
 * If you are getting errors from the @override statements you can just
 * delete them.
 */
public class LinkedList<E> {
    // add some comments here that show me that you understand what
    // "head" and "size" are. --mv

    // first node in the list
    private Node<E> head;
    // size of the list (1-based)
    private int size;


    /**
     * HEY!  THIS IS A SHORT, INNER CLASS FOR THE NODE OBJECT
     * A list node contains the data value and a link to the next
     * node in the linked list.
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;

        /**
         * Construct a node with the specified data value and link.
         */
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        /**
         * Construct a node with the given data value
         */
        public Node(E data) {
            this(data, null);
        }
    } // HEY! THIS IS THE END OF THE NODE INNER CLASS

    /**
     * Helper method:  Find the node at a specified index.
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        Node<E> node = head;

        for (int i = 0; i < index; ++i)
            node = node.next;

        return node;
    }


    /**
     * Constructs an empty list.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }


    /**
     * Appends the specified element to the end of the list.
     */
    public void add(E element) {
        if (isEmpty())
            head = new Node<E>(element);
        else {
            Node<E> lastNode = getNode(size - 1);
            lastNode.next = new Node<E>(element);
        }

        ++size;
    }

    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        //if empty, add node as head
        if (isEmpty()) {
            head = new Node<E>(element);

            //add to front with one or more current nodes
        } else if (index == 0) {
            Node<E> tempNode = getNode(index);
            head = new Node<E>(element);
            head.next = tempNode;

            //insert in the middle of list
        } else {
            Node<E> prevNode = getNode(index - 1);
            Node<E> nextNode = getNode(index);
            prevNode.next = new Node<E>(element);
            Node<E> newNode = prevNode.next;
            newNode.next = nextNode;
        }

        ++size;
    }


    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        while (head != null) {
            Node<E> temp = head;
            head = head.next;

            temp.data = null;
            temp.next = null;
        }

        size = 0;
    }


    /**
     * Returns the element at the specified position in this list.
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        Node<E> node = getNode(index);
        return node.data;
    }


    /**
     * Replaces the element at the specified position in this list
     * with the specified element.
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     * @returns The data value previously at index
     */
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        Node<E> temp = getNode(index);
        //get previous data value
        E oldValue = temp.data;
        //set new value
        temp.data = newValue;

        return oldValue;
    }


    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    public int indexOf(Object obj) {
        int index = 0;

        if (obj == null) {
            for (Node<E> node = head; node != null; node = node.next) {
                if (node.data == null)
                    return index;
                else
                    index++;
            }
        } else {
            for (Node<E> node = head; node != null; node = node.next) {
                if (obj.equals(node.data))
                    return index;
                else
                    index++;
            }
        }

        return -1;
    }


    /**
     * Returns <tt>true</tt> if this list contains no elements.
     */
    public boolean isEmpty() {
        return this.size <= 0;
    }


    /**
     * Removes the element at the specified position in this list.  Shifts
     * any subsequent elements to the left.
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     * @returns the element previously at the specified position
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        E oldValue;

        //set head to next node
        if (index == 0) {
            oldValue = getNode(index).data;
            head = getNode(index + 1);
        } else {
            Node<E> prevNode = getNode(index - 1);
            Node<E> currNode = getNode(index);
            if (currNode.next != null) {
                prevNode.next = currNode.next;
            } else {
                prevNode.next = null;
            }
            oldValue = currNode.data;
        }
        --size;
        return oldValue;
    }


    /**
     * Returns the number of elements in this list.
     */
    public int size() {
        return size;
    }


    /**
     * Returns a string representation of this list.  Formatting is your choice.
     */
    @Override
    public String toString() {
        Node<E> currNode;
        String list = "";

        for (int i = 0; i < size; i++) {
            if (i != 0) {
                list += ",";
            }

            currNode = getNode(i);
            list += currNode.data.toString();
        }
        return list;
    }


    /*
     * Compares the specified object with this list for equality. Returns true
     * if and only if both lists contain the same elements in the same order.
     */
    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof LinkedList))
            return false;

        // cast obj to a linked list
        LinkedList listObj = (LinkedList) obj;

        // compare elements in order
        Node<E> node1 = head;
        Node node2 = listObj.head;

        while (node1 != null && node2 != null) {
            // check to see if data values are equal
            if (node1.data == null) {
                if (node2.data != null)
                    return false;
            } else {
                if (!node1.data.equals(node2.data))
                    return false;
            }

            node1 = node1.next;
            node2 = node2.next;
        }

        return node1 == null && node2 == null;
    }


    /**
     * Returns the hash code value for this list.
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        Node<E> node = head;

        while (node != null) {
            E obj = node.data;
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
            node = node.next;
        }

        return hashCode;
    }
}
