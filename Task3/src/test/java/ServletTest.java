import com.example.task3.dto.Item;
import com.example.task3.servlet.ItemServlet;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@FixMethodOrder(MethodSorters.JVM)
public class ServletTest {

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private Gson gson;

    @Before
    public void setUpMocks() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        gson = new Gson();
    }

    @Test
    public void parseAdd() {
        String line;
        File outputFile = new File("src/test/resources/output.html");
        outputFile.deleteOnExit();

        try (PrintWriter writer = new PrintWriter(outputFile);
             BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            when(response.getWriter()).thenReturn(writer);
            when(request.getParameter("action")).thenReturn("add");
            when(request.getParameter("code")).thenReturn("1");
            when(request.getParameter("name")).thenReturn("testItem");
            when(request.getParameter("price")).thenReturn("1");
            new ItemServlet().doPost(request, response);
            String gsonResult = "";

            while ((line = reader.readLine()) != null) {
                gsonResult = gsonResult.concat(line + System.lineSeparator());
            }

            assertEquals(gson.toJson(new Item(1, "testItem", 1)).trim(), gsonResult.trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseEdit() {
        String line;
        File outputFile = new File("src/test/resources/output.html");
        outputFile.deleteOnExit();

        try (PrintWriter writer = new PrintWriter(outputFile);
             BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            when(response.getWriter()).thenReturn(writer);
            when(request.getParameter("action")).thenReturn("edit");
            when(request.getParameter("oldCode")).thenReturn("1");
            when(request.getParameter("code")).thenReturn("2");
            when(request.getParameter("name")).thenReturn("testItem2");
            when(request.getParameter("price")).thenReturn("2");
            new ItemServlet().doPost(request, response);
            String gsonResult = "";

            while ((line = reader.readLine()) != null) {
                gsonResult = gsonResult.concat(line + System.lineSeparator());
            }

            assertEquals(gson.toJson(new Item(2, "testItem2", 2)).trim(), gsonResult.trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseRemove() {
        String line;
        File outputFile = new File("src/test/resources/output.html");
        outputFile.deleteOnExit();

        try (PrintWriter writer = new PrintWriter(outputFile);
             BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            when(response.getWriter()).thenReturn(writer);
            when(request.getParameter("action")).thenReturn("remove");
            when(request.getParameter("code")).thenReturn("2");
            new ItemServlet().doPost(request, response);
            String gsonResult = "";

            while ((line = reader.readLine()) != null) {
                gsonResult = gsonResult.concat(line + System.lineSeparator());
            }

            assertEquals(gson.toJson(new Item(2, "testItem2", 2)).trim(), gsonResult.trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
