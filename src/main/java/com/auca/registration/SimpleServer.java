package com.auca.registration;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        // Only start the embedded server when explicitly requested.
        // This prevents port conflicts when deploying to Tomcat.
        if (!"true".equals(System.getProperty("embedded.server"))) {
            System.out.println("Embedded server is disabled. Use -Dembedded.server=true to run it.");
            return;
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/login.html", new LoginPageHandler());
        server.createContext("/login", new LoginHandler());
        server.createContext("/redirect.html", new RedirectPageHandler());
        server.createContext("/redirect", new RedirectHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("Server started on http://localhost:8080");
        System.out.println("Login page: http://localhost:8080/login.html");
        System.out.println("Redirect page: http://localhost:8080/redirect.html");
    }
    
    static class LoginPageHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<!DOCTYPE html><html><head><title>Login</title></head><body>" +
                "<h2>Login</h2><form action='/login' method='post'>" +
                "Username: <input type='text' name='username' required><br><br>" +
                "Password: <input type='password' name='password' required><br><br>" +
                "<input type='submit' value='Login'></form></body></html>";
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        }
    }
    
    static class LoginHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                Map<String, String> params = parseForm(exchange);
                String username = params.get("username");
                String password = params.get("password");
                
                String response;
                if (password.length() < 8) {
                    response = "Hello " + username + ", your password is weak. Try a strong one.";
                } else {
                    response = "Welcome " + username;
                }
                
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            }
        }
    }
    
    static class RedirectPageHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<!DOCTYPE html><html><head><title>Google Search</title></head><body>" +
                "<h2>Search on Google</h2><form action='/redirect' method='post'>" +
                "Search Query: <input type='text' name='query' required><br><br>" +
                "<input type='submit' value='Fetch'></form></body></html>";
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        }
    }
    
    static class RedirectHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                Map<String, String> params = parseForm(exchange);
                String query = params.get("query");
                String googleUrl = "https://www.google.com/search?q=" + query;
                
                exchange.getResponseHeaders().add("Location", googleUrl);
                exchange.sendResponseHeaders(302, -1);
                exchange.getResponseBody().close();
            }
        }
    }
    
    private static Map<String, String> parseForm(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
        BufferedReader br = new BufferedReader(isr);
        String formData = br.readLine();
        
        Map<String, String> params = new HashMap<>();
        if (formData != null) {
            String[] pairs = formData.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    params.put(URLDecoder.decode(keyValue[0], "UTF-8"), 
                              URLDecoder.decode(keyValue[1], "UTF-8"));
                }
            }
        }
        return params;
    }
}