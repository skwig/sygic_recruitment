package sk.brecka.sygicrecruitment.view.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import sk.brecka.sygicrecruitment.App;
import sk.brecka.sygicrecruitment.di.AppComponent;

public abstract class BaseActivity<VM> extends AppCompatActivity {

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    protected AppComponent getComponent() {
        return ((App) getApplication()).getComponent();
    }

    protected abstract int getLayoutRes();

}
