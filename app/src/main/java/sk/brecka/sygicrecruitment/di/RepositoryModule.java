package sk.brecka.sygicrecruitment.di;

import dagger.Module;
import dagger.Provides;
import sk.brecka.sygicrecruitment.model.Repository;
import sk.brecka.sygicrecruitment.model.api.RestApi;

@Module(includes = {NetworkModule.class, PersistenceModule.class})
public class RepositoryModule {

    @Provides
    static Repository provideRepository(RestApi restApi) {
        return new Repository(restApi);
    }

}
