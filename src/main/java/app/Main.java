package app;


import java.util.concurrent.*;

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
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        ExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) scheduledExecutor;
        // monitoring service
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("From scheduled executor!");
            System.out.println("Active tasks: " + threadPoolExecutor.getActiveCount());
            System.out.println("Queue size: " + threadPoolExecutor.getQueue().size());
        }, 1, 1, TimeUnit.SECONDS);
        // first task
        Future<String> future = executorService.submit(() -> {
           Thread.sleep(3000);
            return "The first callable is done!";
        });
        // second task
        Future<String> future1 = executorService.submit(() -> {
            Thread.sleep(3000);
            return "The second callable is done!";
        });
        // third task
        Future<String> future2 = executorService.submit(() -> {
            Thread.sleep(5000);
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
