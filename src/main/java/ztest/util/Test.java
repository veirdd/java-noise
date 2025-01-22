package ztest.util;

import java.util.concurrent.Callable;

public class Test {
    public Test(String title, Callable<Boolean> test_operation)
    {
        boolean pass = true;

        try
        {
            if(test_operation.call() == false)
            { throw new Exception(); }
        }
        catch(Exception _)
        { pass = false; }
        
        System.out.println("Test \"" + title + "\" " + (pass ? "passed" : "failed") + ".");
    }
}
