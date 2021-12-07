package logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Logger {

    @AroundInvoke
    public Object loggingInterceptor(InvocationContext ictx) throws Exception {
        System.out.println("logger.Logger works");
        return ictx.proceed();
    }
}
