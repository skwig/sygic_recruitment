package sk.brecka.sygicrecruitment.di;

import com.serjltt.moshi.adapters.Wrapped;
import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import sk.brecka.sygicrecruitment.model.api.DepartmentService;
import sk.brecka.sygicrecruitment.model.api.RestApi;
import sk.brecka.sygicrecruitment.model.api.adapter.DepartmentJsonAdapter;
import sk.brecka.sygicrecruitment.model.api.adapter.EmployeeJsonAdapter;
import sk.brecka.sygicrecruitment.util.Constants;

@Module
public class NetworkModule {

    @Provides
    static RestApi provideRestApi(DepartmentService departmentService) {
        return new RestApi(departmentService);
    }

    @Provides
    static DepartmentService provideDepartmentService(Retrofit retrofit) {
        return retrofit.create(DepartmentService.class);
    }

    @Provides
    static Retrofit provideRetrofit(Moshi moshi) {
        return new Retrofit.Builder()
                .baseUrl(Constants.URL_API)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
    }

    @Provides
    static Moshi provideMoshi() {
        return new Moshi.Builder()
                .add(Wrapped.ADAPTER_FACTORY)
                .add(new EmployeeJsonAdapter())
                .add(new DepartmentJsonAdapter())
                .build();
    }
}
