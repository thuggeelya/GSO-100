import com.example.task1.MyServlet;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private File outputFile;

    @Before
    public void setVars() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        outputFile = new File("src/test/resources/output.html");
        outputFile.deleteOnExit();
    }

    private boolean getResultByName(String name, String expectedMessage) {
        try (PrintWriter writer = new PrintWriter(outputFile);
             BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {

            when(response.getWriter()).thenReturn(writer);
            when(request.getParameter("name")).thenReturn(name);
            new MyServlet().doPost(request, response);

            reader.readLine();
            return reader.readLine().contains(expectedMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void setNameThenGreeting() {
        String name = "test";
        assertTrue(getResultByName(name, "Hello, " + name));
    }

    @Test
    public void setNullThenError() {
        assertTrue(getResultByName(null, "Error"));
    }
}