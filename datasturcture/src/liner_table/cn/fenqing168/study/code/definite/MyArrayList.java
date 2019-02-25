package liner_table.cn.fenqing168.study.code.definite;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private final static Object[] EMPTY_ELEMENTDATA = {};

    private Object[] elementData;

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(Object ob : elementData){
            if(ob == o){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new myIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if(a.length < size){
            return (T1[])Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if(a.length > size){
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        elementData[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for(int i = 0; i < size; i++){
            if(o == elementData[i]){
                for(int j = i; j < size - 1; j++){
                    elementData[j] = elementData[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        Object[] cs = c.toArray();
        int sum = 0, length = cs.length;
        for(int i = 0; i < size; i++){
            for(int j = sum; j < length; j++){
                if(cs[j] == elementData[i]){
                    sum++;
                    break;
                }
            }
        }
        return sum == length;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}

class myIterator<T> implements Iterator<T>{

    private int size;

    private int cursor;

    private T[] data;

    public myIterator(List list) {
        this.size = list.size();
        this.data = (T[])list.toArray();
    }

    @Override
    public boolean hasNext() {
        return cursor < size;
    }

    @Override
    public T next() {
        return data[cursor++];
    }
}
