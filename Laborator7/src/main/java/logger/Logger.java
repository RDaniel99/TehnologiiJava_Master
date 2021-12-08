package logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

public class Logger implements Serializable {

    @AroundInvoke
    public Object loggingInterceptor(InvocationContext ictx) throws Exception {
        System.out.println("logger.Logger works");
        return ictx.proceed();
    }
}
