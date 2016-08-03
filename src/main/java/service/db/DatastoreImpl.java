package service.db;

public class DatastoreImpl implements Datastore {

    private Object keepit = null;

    @Override
    public void c(Object o) {
        keepit = o;
    }

    @Override
    public Object r() {
        return keepit;
    }

    @Override
    public Object u(Object o) {
        return keepit = o;
    }

    @Override
    public void d() {
        keepit = null;
    }

}
