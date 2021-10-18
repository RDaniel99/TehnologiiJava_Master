package decorators;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseDecorator extends HttpServletResponseWrapper {

    private final StringWriter output;
    public ResponseDecorator(HttpServletResponse response) {
        super(response);
        output = new StringWriter();
    }

    @Override
    public PrintWriter getWriter() {
        // Hide the original writer
        return new PrintWriter(output);
    }
    @Override
    public String toString() {
        return output.toString();
    }

}
