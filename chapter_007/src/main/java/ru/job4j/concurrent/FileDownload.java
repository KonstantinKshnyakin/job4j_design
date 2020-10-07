package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class FileDownload {
    public static void main(String[] args) throws Exception {
        args = new String[10];
        args[0] = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        args[1] = "200";
        double maxSpeed = Double.parseDouble(args[1]);
        LocalTime start = LocalTime.now();
        try (BufferedInputStream in = new BufferedInputStream(new URL(args[0]).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            double countBytes = 0;
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                countBytes += bytesRead;
                LocalTime now = LocalTime.now();
                long millisActual = start.until(now, ChronoUnit.MILLIS);
                long millisExpected = (long) (countBytes / maxSpeed) * 1000;
                if (millisExpected > millisActual) {
                    int timeSleep = (int) (millisExpected - millisActual);
                    Thread.sleep(timeSleep);
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}