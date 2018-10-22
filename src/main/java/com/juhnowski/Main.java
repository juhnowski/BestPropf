package com.juhnowski;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static spark.Spark.*;
import static java.nio.charset.StandardCharsets.*;

public class Main {
//POST http://localhost/cmd?attr=Get-Service
//     http://localhost/cmd?attr=Get-Time
//     http://localhost/cmd?attr=Test-Connection 127.0.0.1
//     http://localhost/cmd?attr=Test-NetConnection
//     http://localhost/cmd?attr=Hello-World
//     http://localhost/cmd?attr=ping 127.0.0.1
//     http://localhost/cmd?attr=Resolve-DNSName sberbank.ru

    public static String process(String cmd){

        StringBuilder sb = new StringBuilder();

        try {
            sb.append("{\"Input\":\"").append(cmd).append("\"");
            String command = "powershell.exe  "+cmd;
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            powerShellProcess.getOutputStream().close();
            String line;
            sb.append("\",\"Output\":[");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream(),"cp866"));
            while ((line = stdout.readLine()) != null) {
                System.out.println(line);
                sb.append("\"").append(line).append("\",");
            }
            sb.append("]");
            stdout.close();
            sb.append(",\"Error\":[");
            BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream(),"cp866"));
            while ((line = stderr.readLine()) != null) {
                sb.append("\"").append(line).append("\",");
            }
            stderr.close();
            sb.append("]");
            sb.append(",\"Status\":\"Done\"}");
        } catch (Exception e){
            sb.append("\",\"Status\":\"").append(e.getMessage()).append("\"}");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        port(80);
        get("/cmd", (req, res) -> process(req.queryParams("attr")));
        //System.out.println(process("Get-LocalUser"));
    }
}
