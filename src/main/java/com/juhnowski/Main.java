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
            //Getting the version
            //String command = "powershell.exe  $PSVersionTable.PSVersion";
            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            sb.append("\",\"Output\":\"");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                //byte[] ptext = line.getBytes("windows-1251");
                //String value = new String(ptext, "UTF-8");
                //System.out.println(value);
                //String value = new String(line.getBytes("windows-1251"), "UTF-8");

                sb.append(line);
            }
            stdout.close();
            sb.append("\",\"Error\":\"");
            BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()));
            while ((line = stderr.readLine()) != null) {
                sb.append(line);
            }
            stderr.close();
            sb.append("\",\"Status\":\"Done\"}");
        } catch (Exception e){
            sb.append("\",\"Status\":\"").append(e.getMessage()).append("\"}");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        port(80);
        post("/cmd", (req, res) -> process(req.queryParams("attr")));
    }
}
