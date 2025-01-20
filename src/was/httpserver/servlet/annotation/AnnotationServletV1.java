package was.httpserver.servlet.annotation;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFountException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class AnnotationServletV1 implements HttpServlet {

    private final List<Object> controllers;

    public AnnotationServletV1(List<Object> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();

        for (Object controller : controllers) {
            Method[] declaredMethods = controller.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (method.isAnnotationPresent(Mapping.class)) {
                    Mapping annotation = method.getAnnotation(Mapping.class);
                    String value = annotation.value();
                    if (value.equals(path)) {
                        invoke(controller, method, response, request);
                        return;
                    }
                }
            }
        }

        throw new PageNotFountException("Page Not Found");
    }

    private static void invoke(Object controller, Method method, HttpResponse response, HttpRequest request) {
        try {
            method.invoke(controller, request, response);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
