package liner_table.cn.fenqing168.study.code.definite;

import liner_table.cn.fenqing168.study.code.exception.MyArrayIndexOutOfBoundsException;
import liner_table.cn.fenqing168.study.code.interfaces.List;

import java.util.Arrays;

/**
 * 顺序列表实现方式
 * 采用数组实现
 */
public class ArrayList implements List {

    private Object[] elementData;

    private int size;

    /**
     * 默认长度为4
     */
    public ArrayList(){
        this(4);
    }

    /**
     * 初始化指定长度
     * @param initialCapacity  指定长度
     */
    public ArrayList(int initialCapacity){
        //初始化指定长度
        elementData = new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        if(i < 0 || i >= size){
            throw new MyArrayIndexOutOfBoundsException("数组下标越界：" + i);
        }
        return elementData[i];
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
        if(i < 0 || i >= ++size){
            throw new MyArrayIndexOutOfBoundsException("数组下标越界：" + i);
        }
        dilatation();
        System.arraycopy(elementData, i, elementData, i + 1, size - i);
        elementData[i] = e;
    }

    @Override
    public void add(Object e) {
        add(size, e);
    }

    private void dilatation(){
        if(size == elementData.length){
            elementData = Arrays.copyOf(elementData, elementData.length + (elementData.length >> 1));
        }
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
        return null;
    }

    @Override
    public boolean remove(Object e) {
        return false;
    }

    @Override
    public Object replace(int i, Object e) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < size; i++){
            sb.append(elementData[i]);
            if(i < size - 1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
