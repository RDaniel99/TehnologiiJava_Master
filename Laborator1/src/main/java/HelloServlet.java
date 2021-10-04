import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Scanner;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String confirmationMessage;
    private String okMessage;
    private String errorMessage;
    private String repositoryFile;

    public void init() {

        confirmationMessage = "Mesaj de confirmare!";
        okMessage = "OK!";
        errorMessage = "Error!";
        repositoryFile = "D://Projects/Java_MasterAnu1Sem1/Laborator1/src/main/resources/repoFile.txt";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // TODO: See how to add a form into index.jsp

        logRequest(request);

        response.setContentType("text/html");

        String mockParamValue = request.getParameter("mock");

        if(mockParamValue == null) {

            getMessage(response, errorMessage);
            return ;
        }

        if(mockParamValue.equals("true")) {

            getMessage(response, confirmationMessage);
            return ;
        }


        solveMockWhenIsFalse(request, response);
    }

    private void logRequest(HttpServletRequest request) {

        log(request.getRemoteAddr());
        log(request.getHeader("User-Agent"));
        log(request.getMethod());
        log(request.getParameterMap().toString());
    }

    private void getMessage(HttpServletResponse response, String message) throws IOException {

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    private void solveMockWhenIsFalse(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Optional<File> repoFile = getRepoFile();

        if(!repoFile.isPresent()) {

            getMessage(response, errorMessage);
            return ;
        }

        String valueParamValue = request.getParameter("value");
        String keyParamValue = request.getParameter("key");
        String syncParamValue = Optional.ofNullable(request.getParameter("sync")).orElse("false");

        if(valueParamValue == null || keyParamValue == null) {

            getMessage(response, errorMessage);
            return ;
        }

        boolean didWorked = false;
        if(syncParamValue.equals("false")) {

            didWorked = solveWhenSyncIsFalse(valueParamValue, keyParamValue, repoFile.get());
        }
        else {

            didWorked = solveWhenSyncIsTrue(valueParamValue, keyParamValue, repoFile.get());
        }

        if(!didWorked) {

            getMessage(response, errorMessage);
            return ;
        }

        String htmlResponse = buildHTMLStringWhenEverythingWasOk(repoFile.get());
        getMessage(response, htmlResponse);
    }

    private Optional<File> getRepoFile() {

        File repoFile = new File(repositoryFile);

        if(repoFile.exists()) {

            return Optional.of(repoFile);
        }

        return Optional.empty();
    }

    private String buildHTMLStringWhenEverythingWasOk(File file) {

        StringBuilder builder = new StringBuilder();

        try {

            Scanner reader = new Scanner(file);
            String s ;

            while(reader.hasNextLine()) {
                s = reader.nextLine();
                builder.append(s);
                builder.append("\n");
            }

        } catch (Exception e) {

            return errorMessage;
        }

        return builder.toString();
    }

    private boolean solveWhenSyncIsTrue(String value, String key, File file) {

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            PrintWriter out = new PrintWriter(bw);

            int valueAsInt = Integer.parseInt(value);

            while(valueAsInt > 0) {

                --valueAsInt;
                out.println(key + " " + getTimeStamp());
            }

            out.close();

        } catch (IOException exception) {

            return false;
        }

        return true;
    }

    private boolean solveWhenSyncIsFalse(String value, String key, File file) {

        // TODO: Create unsync appending method to file

        return true;
    }

    private String getTimeStamp() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }

    public void destroy() { }
}