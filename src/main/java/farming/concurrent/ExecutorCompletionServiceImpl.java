package farming.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by john on 16/8/7.
 */
public class ExecutorCompletionServiceImpl<V> implements CompletionService<V> {
    private final Executor executor;
    private List<Future<V>> futureList;

    /**
     * FutureTask extension to enqueue upon completion
     */
    private class QueueingFuture extends FutureTask<Void> {
        QueueingFuture(RunnableFuture<V> task) {
            super(task, null);
            this.task = task;

        }

        private final Future<V> task;

        public Void get() throws InterruptedException, ExecutionException {
            Void v = super.get();
            if (task instanceof WrapFutureTask) {
                ((WrapFutureTask) task).getTask().handle(v);
            }
            return v;
        }
    }

    private class WrapFutureTask<Void> extends FutureTask<Void> {
        private Task<?, Void> task;

        WrapFutureTask(Task<?, Void> task) {
            super(task);
            this.task = task;
        }

        public Task<?, Void> getTask() {
            return this.task;
        }
    }


    private RunnableFuture<V> newTaskFor(Task<?, V> task) {
        return new WrapFutureTask<V>(task);
    }

    /**
     * Creates an ExecutorCompletionService using the supplied
     * executor for base task execution and a
     * {@link LinkedBlockingQueue} as a completion queue.
     *
     * @param executor the executor to use
     * @throws NullPointerException if executor is {@code null}
     */
    public ExecutorCompletionServiceImpl(Executor executor) {
        if (executor == null)
            throw new NullPointerException();
        this.executor = executor;
        this.futureList = new ArrayList<Future<V>>();
    }


    public Future<V> submit(Task<?, V> task) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<V> f = newTaskFor(task);
        FutureTask queueingFuture = new QueueingFuture(f);
        executor.execute(queueingFuture);
        futureList.add(queueingFuture);
        return f;
    }

    public void sync() {
        if (futureList != null) {
            while (futureList.size() > 0) {
                Future<V> future = futureList.get(0);
                futureList.remove(future);
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
