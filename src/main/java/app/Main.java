package app;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Here we have defined only two threads
 * at ExecutorService. So first two Callables will
 * be assigned to these threads while third will be
 * placed to a queue and will be executed right after
 * first thread will be available.
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> future = executorService.submit(() -> {
           Thread.sleep(2000);
            return "The first callable is done!";
        });
        Future<String> future1 = executorService.submit(() -> {
            Thread.sleep(2000);
            return "The second callable is done!";
        });
        Future<String> future2 = executorService.submit(() -> {
            Thread.sleep(2000);
            return "The third callable is done!";
        });
        try {
            System.out.println(future.get());
            System.out.println(future1.get());
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
