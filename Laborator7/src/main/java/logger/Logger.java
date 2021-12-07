package logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Logger {

    @AroundInvoke
    public Object loggingInterceptor(InvocationContext ictx) {
        System.out.println("logger.Logger works");
        return null;
    }
}
