package binay_tree.cn.fenqing168.study.code.bean;

/**
 * 二叉树的节点对象
 */
public class Node {

    /**
     * 节点的数据
     */
    private Object value;

    /**
     * 左子节点
     */
    private Node leftChilden;

    /**
     * 右子节点
     */
    private Node rightChilden;

    public Node() {
    }

    public Node(Object value, Node leftChilden, Node rightChilden) {
        this.value = value;
        this.leftChilden = leftChilden;
        this.rightChilden = rightChilden;
    }

    public Object getValue() {
        return value;
    }

    public Node getLeftChilden() {
        return leftChilden;
    }

    public Node getRightChilden() {
        return rightChilden;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", leftChilden=" + leftChilden +
                ", rightChilden=" + rightChilden +
                '}';
    }
}
