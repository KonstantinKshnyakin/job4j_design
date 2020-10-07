package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ConsoleProgress());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

    @Override
    public void run() {
        char[] circle = {'\\', '|', '/'};
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            count++;
            count = count == 3 ? 0 : count;
            System.out.print("\rLoading " + circle[count]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\r\nLoaded.");
    }
}
