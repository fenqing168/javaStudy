package liner_table.cn.fenqing168.study.code.exception;

/**
 * 数组下标越界异常
 */
public class MyArrayIndexOutOfBoundsException extends RuntimeException{

    public MyArrayIndexOutOfBoundsException() {
        super();
    }

    public MyArrayIndexOutOfBoundsException(String message) {
        super(message);
    }
}
