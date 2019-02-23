package liner_table.cn.fenqing168.study.code.definite;

import liner_table.cn.fenqing168.study.code.bean.Node;
import liner_table.cn.fenqing168.study.code.exception.MyArrayIndexOutOfBoundsException;
import liner_table.cn.fenqing168.study.code.interfaces.List;

/**
 * 链表实现
 */
public class SingleLinkedList implements List {

    private Node head = new Node();

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        if(0 > i || size <= i){
            throw new MyArrayIndexOutOfBoundsException("下标越界：" + i);
        }
        Node node = head;
        for(int j = 0; j <= i; j++){
            node = node.getNext();
        }
        return node.getNext();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object e) {
        return false;
    }

    @Override
    public int indexOf(Object e) {
        return 0;
    }

    @Override
    public void add(int i, Object e) {
        if(0 > i || size < i){
            throw new MyArrayIndexOutOfBoundsException("下标越界：" + i);
        }
        Node temp = head;
        for(int j = 0; j < i; j++){
            temp = head.getNext();
        }
        Node node = new Node();
        node.setData(e);
        node.setNext(temp.getNext());
        temp.setNext(node);
        size++;
    }

    @Override
    public void add(Object e) {
        add(size, e);
    }

    @Override
    public boolean addBefore(Object obj, Object e) {
        return false;
    }

    @Override
    public boolean addAfter(Object obj, Object e) {
        return false;
    }

    @Override
    public Object remove(int i) {
        if(0 > i || size < i){
            throw new MyArrayIndexOutOfBoundsException("下标越界：" + i);
        }
        Node temp = head;
        for(int j = 0; j < i; j++){
            temp = temp.getNext();
        }
        Node remove = temp.getNext();
        temp.setNext(remove.getNext().getNext());
        Object o = remove.getData();
        remove.setData(null);
        remove.setNext(null);
        size--;
        return o;
    }

    @Override
    public boolean remove(Object e) {
        Node temp = head;
        boolean bool = false;
        for(int j = 0; j < size; j++){

            if(temp.getNext().getData().equals(e)){
                Node remove = temp.getNext();
                temp.setNext(remove.getNext());
                remove.setData(null);
                remove.setNext(null);
                bool = true;
                size--;
                break;
            }
            temp = temp.getNext();
        }

        return bool;
    }

    @Override
    public Object replace(int i, Object e) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node node = head;
        for(int i = 0; i < size; i++){
            node = node.getNext();
            sb.append(node.getData());
            if(i < size - 1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
