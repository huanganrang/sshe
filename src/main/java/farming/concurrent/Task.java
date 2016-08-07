package farming.concurrent;

/**
 * Created by john on 16/8/7.
 */

import java.util.concurrent.Callable;

public abstract class Task<D, V> implements Callable<V> {
    private D d;

    Task() {

    }

    Task(D d) {
        this.d = d;
    }


    public void handle(V v) {
        if (d != null)
            set(d, v);
    }

    protected void set(D d, V v) {

    }

}
