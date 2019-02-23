package cn.fenqing168.study.code.definite;

import cn.fenqing168.study.code.exception.MyArrayIndexOutOfBoundsException;
import cn.fenqing168.study.code.interfaces.List;

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

    }

    @Override
    public void add(Object e) {
        elementData[size++] = e;
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
}
