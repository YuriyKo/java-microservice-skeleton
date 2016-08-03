package service.db;

import com.google.inject.AbstractModule;

public class DatastoreModule extends AbstractModule {

    protected void configure() {
        bind(Datastore.class).to(DatastoreImpl.class).asEagerSingleton();
    }

}
