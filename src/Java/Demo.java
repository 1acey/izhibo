import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Demo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        try {
            PrintWriter writer=response.getWriter();
            writer.println("<h1>" + "hello first servlet!<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
