package cn.fenqing168.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        //System.out.println(TestDll.TEST.SysInit(1, 1));
        System.out.println(TestDll.TEST.IP_StrToInt("192.168.0.1"));
        System.out.println(TestDll.TEST.IP_IntToStr("192.168.0.1", TestDll.TEST.IP_StrToInt("192.168.0.1")));
    }
}
