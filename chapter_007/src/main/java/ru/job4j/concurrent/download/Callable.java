package ru.job4j.concurrent.download;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Callable {

    private final String url;
    private final int speed;

    public Callable(String url, String speed) {
        this.url = url;
        this.speed = Integer.parseInt(speed);
    }

    public void download() {
        LocalTime start = LocalTime.now();
        try (BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {

            byte[] dataBuffer = new byte[1024];
            double countBytes = 0;
            int bytesRead;

            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                countBytes += bytesRead;
                correctSpeed(start, countBytes);
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void correctSpeed(LocalTime startTime, double countBytes) {
        LocalTime nowTime = LocalTime.now();
        long millisActual = startTime.until(nowTime, ChronoUnit.MILLIS);
        long millisExpected = (long) (countBytes / this.speed) * 1000;
        if (millisExpected > millisActual) {
            int timeSleep = (int) (millisExpected - millisActual);
            try {
                Thread.sleep(timeSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
