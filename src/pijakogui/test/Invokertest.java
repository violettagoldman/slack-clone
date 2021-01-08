package pijakogui.test;

import org.junit.Test;
import pijakogui.invoker.Invoker;

public class Invokertest {
    @Test
    public void InvokerTest(){
        Invoker.getInstance().invoke("users/signup",null);
    }
}
