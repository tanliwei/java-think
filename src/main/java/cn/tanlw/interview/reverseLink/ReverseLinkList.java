package cn.tanlw.interview.reverseLink;

/**
 * 反转链表
 */
public class ReverseLinkList {
    public static void main(String[] args) {
        Node head = init();
        printLinkList(head);
        int m = 2;
        int n = 4;
        reverseLinkList(head, m, n);
        printLinkList(head);
    }

    private static void reverseLinkList(Node head, int m, int n) {

        Node subHead = findById(head, m - 1);
        Node subTail = findById(head, n + 1);
        doReverse(subHead, subTail, n-m);
    }

    private static void doReverse(Node subHead, Node subTail, int steps) {
        Node current = subHead.next;
        Node previous = subTail;
        int count = 0;
        int times = steps + 1;
        while(current != null && count < times){
            Node temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
            count++;
        }
        subHead.next = previous;
    }

    private static Node findById(Node head, int i) {
        int count = 0;
        Node item = head;
        while (count < i && item != null) {
            item = item.next;
            count++;
        }
        return item;
    }

    private static Node init() {
        Node n5 = new Node(5, null);
        Node n4 = new Node(4, n5);
        Node n3 = new Node(3, n4);
        Node n2 = new Node(2, n3);
        Node n1 = new Node(1, n2);
        return new Node(0, n1);
    }

    private static void printLinkList(Node head) {
        if (head == null || head.next == null)
            return;
        Node current = head.next;
        while (current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        }
        System.out.println(current);
    }

}


class Node {
    public int value;
    public Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}