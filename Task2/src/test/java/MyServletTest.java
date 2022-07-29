import com.example.task2.MyServlet;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import static org.mockito.Mockito.*;

public class MyServletTest {

    private static final String FILE_NAME = "src/test/resources/file.pdf";

    @Test
    public void isUploaded() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        Part part = mock(Part.class);

        when(part.getSubmittedFileName()).thenReturn(FILE_NAME);
        when(request.getPart("file")).thenReturn(part);
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(requestDispatcher);

        new MyServlet().doPost(request, response);

        verify(part).write(eq("/var/tmp/" + FILE_NAME));
    }
}