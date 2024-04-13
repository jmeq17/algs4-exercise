package exercise.ch1.topic3;

/*
Give homework.a code fragment that removes the last node in homework.a linked list whose first node is first.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import static java.lang.Integer.parseInt;

public class E10319AND20<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
        // Or: N == 0.
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }

    public Item removeLast() {
        Node current = first;
        for (Node x = first; x.next != null; x = x.next) {
            current = x;
        }
        assert current.next != null;
        Item item = current.next.item;
        current.next = null;
        return item;
    }

    public void delete(int k) {
        Node current = first;
        int count = 1;
        for (Node x = first; count != k; x = x.next) {
            if (x.next == null) {
                StdOut.println("超出量程");
                return;
            }
            current = x;
            count++;
        }
        current.next = current.next.next;
        N--;
    }


    // 1.3.21 通过迭代器实现
//    public static boolean find(E10319AND20<String> linked, String key) {
////        for (Object s : linked) {
////            if (s.equals(key)) {
////                return true;
////            }
////        }
//        //楼下这些被注释的也行,方法2
//        Iterator<String> iterator = lls.iterator();
//        while (iterator.hasNext()){
//            String next = iterator.next();
//            if(next.equals(key)){
//                return true;
//            }
//        }
//        return false;
//    }

    // 1.3.21 通过 public 实现，书中的要求只能用静态方法实现，以下的代码不能用静态方法，因此给出两种实现
    public boolean find(String key) {
        for (Node x = first; x.next != null; x = x.next) {
            if (x.item.equals(key)) {
                return true;
            }
        }
        return false;
    }

    // 法2
//    public boolean contains(Item item)
//    {
//        Node curr = first;
//        while (curr != null && !curr.item.equals(item))
//            curr = curr.next;
//        return curr != null;
//    }


    // 1.3.22 写一个 removeAfter() 方法，接受一个节点未参数输出他后边的节点
    // Node 是私有属性，需要先获取 Node，以下的方法需要将 Node 改为静态
    public Node getNode(Item item) {
        Node current = first;
        while (current.next != null) {
            if (current.item.equals(item)) {
                break;
            }
            current = current.next;
        }
        return current;
    }

    // E10324
//    public void removeAfter(Node node) {
//        if (node == null || node.next == null) {
//            return;
//        }
//        node.next = null;
//    }

    // 另一种做法，不以 Node 作为参数
    public void removeAfter(Item item) {
        Node current = first;
        while (!current.item.equals(item)) {
            if (current == null || current.next == null) {
                return;
            }
            current = current.next;
        }
        current.next = null;

        // 重新计数
        int count = 0;
        Node a = first;
        while (a != null) {
            count++;
            a = a.next;
        }
        N = count;
    }

    // 1.3.25 insertAfter()
    public void insertAfter(Item item1, Item item2) {
        if (item1 == null && item2 == null) {
            return;
        }
        Node current = first;
        Node after = new Node();
        after.item = item2;
        while (!current.item.equals(item1)) {
            if (current.next == null) {
                return;
            }
            current = current.next;
        }
        after.next = current.next;
        current.next = after;

        // 重新计数
        int count = 0;
        Node a = first;
        while (a != null) {
            count++;
            a = a.next;
        }
        N = count;
    }

    // 1.3.26
    public void remove(Item key) {
//        Node current = first;
//        while (current.next != null) {
        for (Node x = first; x != null; x = x.next) {
            if (x == first && x.item.equals(key)) {
                first = first.next;
                N--;
            } else if (x.next.item.equals(key)) {
                x.next = x.next.next;
                N--;
            }
//                current = x;
        }
//        }
    }

    // 1.3.27
    // 获取第一个节点不方便，不要参数了吧
    public int max() {
        // 常归法
        Node current = first;
        if (first == null) {
            return 0;
        }
        for (Node x = first; x != null; x = x.next) {
            if (parseInt(current.item.toString()) < parseInt(x.item.toString())) {
                current = x;
            }
        }
        return parseInt(current.item.toString());


        // 递归法
//        Node current = first;
//        if (first == null) {
//            return 0;
//        }
//        int n1 = parseInt(first.item.toString());
//        Node oldfirst = first.next;
//        first = oldfirst;
//        int n2 =
//
//
//        return 0;
    }

    // 1.3.30
//    public Item reverse() {
//
//        Node current = new Node();
//
//        for (Node x = first; x != null; x = x.next) {
//            Node oldcurr = current;
//            current = new Node();
//            current.item = x.item;
//            current.next = oldcurr;
//        }
//        return current.item;
//    }
//    public Node reverse() {
//        first = reverse(first);
//        return first;
//    }
//
//    public Node reverse(Node node) {
//        Node srcFirst = node;
//        Node destFirst = null;
//        while (srcFirst != null) {
//            Node next = srcFirst.next;
//            srcFirst.next = destFirst;
//            destFirst = srcFirst;
//            srcFirst = next;
//        }
//        return destFirst;
//    }

    // 另一种方法
    public Node reverseRec() {
        first = reverseRec(first);
        return first;
    }

    private Node reverseRec(Node node) {
        return reverseRec(node, null);
    }

    private Node reverseRec(Node srcFirst, Node destFirst) {
        if (srcFirst == null)
            return destFirst;
        else {
            Node next = srcFirst.next;
            srcFirst.next = destFirst;
            return reverseRec(next, srcFirst);
        }
    }


    /* ***  main  *** */
    public static void main(String[] args) {
        E10319AND20<String> a = new E10319AND20<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-")) {
                a.enqueue(s);
            } else if (!a.isEmpty()) {
                StdOut.print(a.dequeue() + " ");
            }
        }
        System.out.println();
        System.out.println("执行结束");
        a.delete(4);

//        boolean bool = homework.a.find("is");
//        StdOut.println(bool);

        // 下面的做法需要将 Node 改成静态的，改成后有两种方法获取 Node
        // 法1
        // Node b = homework.a.getNode("d");
        // 法2
        // Node c=new Node();
        // c.item="c";
        // 然后执行方法
        // homework.a.removeAfter(b);
        // homework.a.removeAfter(c);

        // 不以 Node 作为参数
//        homework.a.removeAfter("d");

        // 1.3.25
//        homework.a.insertAfter("d", "insert");
//
//        homework.a.remove("s");

        StdOut.println(a.reverseRec());


//        E10319AND20<Integer> homework.a = new E10319AND20<>();
//        while (!StdIn.isEmpty()) {
//            int n = StdIn.readInt();
//                homework.a.enqueue(n);
//        }
//        StdOut.println(homework.a.max());
    }
}