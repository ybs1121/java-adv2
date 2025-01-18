package was.httpserver.servlet.reflection;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFountException;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class ReflectionServlet implements HttpServlet {

    private final List<Object> controllers;

    public ReflectionServlet(List<Object> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();
        for (Object controller : controllers) {
            Class<?> aClass = controller.getClass();
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                String methodName = declaredMethod.getName();
                if (path.equals("/" + methodName)) {
                    invoke(controller, declaredMethod, request, response);
                    return;
                }
            }
        }

        throw new PageNotFountException("request : " + request);
    }

    private static void invoke(Object controller, Method declaredMethod, HttpRequest request, HttpResponse response) {
        try {
            declaredMethod.invoke(controller, request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
