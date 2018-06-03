package sk.brecka.sygicrecruitment.di;

import javax.inject.Singleton;

import dagger.Component;
import sk.brecka.sygicrecruitment.model.Repository;
import sk.brecka.sygicrecruitment.view.ui.EmployeeDetailActivity;
import sk.brecka.sygicrecruitment.view.ui.MainActivity;

@Singleton
@Component(modules = {AndroidModule.class, ViewModelModule.class, RepositoryModule.class, MediaModule.class})
public interface AppComponent {

    //
    void inject(MainActivity mainActivity);

    void inject(EmployeeDetailActivity employeeDetailActivity);

    //
    Repository getRepository();
}
