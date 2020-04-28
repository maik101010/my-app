package hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CommonHooks {
    @Before
    public static void beforeScenarios(){
        System.out.println("********Me ejecuto antes del escenarios y andes delbackground********");
    }

//    @Before("(Signup or @anything) and (not @foo)") //or, and
//    public static void cleanDatabase(){
//        System.out.println("********Me ejecuto antes del escenarios y andes delbackground********");
//    }
//    @After
//    public static void afterHook(){
//        System.out.println("Me ejecuto después del escenarios");
//    }
}
