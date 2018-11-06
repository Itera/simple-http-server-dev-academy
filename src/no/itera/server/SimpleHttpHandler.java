package no.itera.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import static java.lang.String.format;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.Optional.ofNullable;

public class SimpleHttpHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        handleResponse(exchange);
    }

    private static void handleResponse(HttpExchange exchange) throws IOException {
        String response = getResponse(exchange.getRemoteAddress().toString());
        exchange.sendResponseHeaders(HTTP_OK, response.length());

        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private static String getResponse(String queryString) {
        String query = ofNullable(queryString).orElse("empty");
        return format("Response to query = ['%s']", query);
    }
}
