package liner_table.cn.fenqing168.study.code.bean;

/**
 * LinkedList使用的节点对象
 */
public class Node {

    private Object data;

    private Node next;

    public Object getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node() {
    }

    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }
}
