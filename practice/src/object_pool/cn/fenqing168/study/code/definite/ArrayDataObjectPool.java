package object_pool.cn.fenqing168.study.code.definite;

import object_pool.cn.fenqing168.study.code.interfaces.DataObjectPool;

public class ArrayDataObjectPool implements DataObjectPool {

    private Byte[] bytes;

    private Short[] shorts;

    private Integer[] ints;

    private Long[] longs;

    private Float[] floats;

    private Double[] doubles;

    private Character[] characters;

    private int minNum;

    private int maxNum;

    public ArrayDataObjectPool(int mixNum, int maxNum) {
        int length = maxNum - mixNum;
        bytes = new Byte[length];
        shorts = new Short[length];
        ints = new Integer[length];
        longs = new Long[length];
        floats = new Float[length];
        doubles = new Double[length];
        characters = new Character[length];
        this.minNum = minNum;
        this.maxNum = maxNum;
    }

    @Override
    public Byte pack(byte num) {
        Byte b;
        if(isScope(num)){
            b = bytes[getIndex(num)];
            if(b == null){
                b = Byte.valueOf(num);
                bytes[getIndex(num)] = b;
            }
        }else{
            b = Byte.valueOf(num);
        }
        return b;
    }

    @Override
    public Short pack(short num) {
        Short b;
        if(isScope(num)){
            b = shorts[getIndex(num)];
            if(b == null){
                b = Short.valueOf(num);
                shorts[getIndex(num)] = b;
            }
        }else{
            b = Short.valueOf(num);
        }
        return b;
    }

    @Override
    public Integer pack(int num) {
        Integer b;
        if(isScope(num)){
            b = ints[getIndex(num)];
            if(b == null){
                b = Integer.valueOf(num);
                ints[getIndex(num)] = b;
            }
        }else{
            b = Integer.valueOf(num);
        }
        return b;
    }

    @Override
    public Long pack(long num) {
        Long b;
        if(isScope((int)num)){
            b = longs[getIndex((int)num)];
            if(b == null){
                b = Long.valueOf(num);
                longs[getIndex((int)num)] = b;
            }
        }else{
            b = Long.valueOf(num);
        }
        return b;
    }

    @Override
    public Float pack(float num) {
        Float b;
        int integer = 0;
        if(isInteger(num)){
            integer = (int)num;
        }else{
            return Float.valueOf(num);
        }
        if(isScope((int)num)){
            b = floats[getIndex(integer)];
            if(b == null){
                b = Float.valueOf(num);
                floats[getIndex(integer)] = b;
            }
        }else{
            b = Float.valueOf(num);
        }
        return b;
    }

    @Override
    public Double pack(double num) {
        Double b;
        int integer = 0;
        if(isInteger(num)){
            integer = (int)num;
        }else{
            return Double.valueOf(num);
        }
        if(isScope((int)num)){
            b = doubles[getIndex(integer)];
            if(b == null){
                b = Double.valueOf(num);
                doubles[getIndex(integer)] = b;
            }
        }else{
            b = Double.valueOf(num);
        }
        return b;
    }

    @Override
    public Character pack(char c) {
        Character b;
        if(isScope(c)){
            b = characters[getIndex(c)];
            if(b == null){
                b = Character.valueOf(c);
                characters[getIndex(c)] = b;
            }
        }else{
            b = Character.valueOf(c);
        }
        return b;
    }

    private boolean isScope(int num){
        return num >= minNum && num <= maxNum;
    }

    private boolean isInteger(double d){
        return d % 1 == 0;
    }

    private int getIndex(int num){
        return (maxNum - minNum) / 2 + num;
    }
}
