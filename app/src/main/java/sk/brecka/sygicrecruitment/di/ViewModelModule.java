package sk.brecka.sygicrecruitment.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import sk.brecka.sygicrecruitment.viewmodel.EmployeeDetailViewModel;
import sk.brecka.sygicrecruitment.viewmodel.MainViewModel;
import sk.brecka.sygicrecruitment.viewmodel.ViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeDetailViewModel.class)
    abstract ViewModel bindEmployeeDetailViewModel(EmployeeDetailViewModel employeeDetailViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
