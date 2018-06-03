package sk.brecka.sygicrecruitment.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.List;

import sk.brecka.sygicrecruitment.R;
import sk.brecka.sygicrecruitment.databinding.ActivityMainBinding;
import sk.brecka.sygicrecruitment.model.business.Department;
import sk.brecka.sygicrecruitment.model.business.Employee;
import sk.brecka.sygicrecruitment.util.UiUtils;
import sk.brecka.sygicrecruitment.util.ViewUtils;
import sk.brecka.sygicrecruitment.view.ListItemMarginDecoration;
import sk.brecka.sygicrecruitment.view.adapter.DepartmentSpinnerAdapter;
import sk.brecka.sygicrecruitment.view.adapter.EmployeeRecyclerAdapter;
import sk.brecka.sygicrecruitment.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<MainViewModel> {

    private static final String TAG = "MainActivity";

    private EmployeeRecyclerAdapter mEmployeeRecyclerAdapter;
    private DepartmentSpinnerAdapter mDepartmentSpinnerAdapter;

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getComponent().inject(this);

        initViewModel();
        initBinding();

        initToolbar();
        initEmployeeRecycler();
        initDepartmentSpinner();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    //
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);

        mViewModel.getDepartment().observe(this, new Observer<Department>() {
            @Override
            public void onChanged(@Nullable Department department) {
                if (department == null) {
                    mEmployeeRecyclerAdapter.clear();
                } else {
                    mEmployeeRecyclerAdapter.setEmployees(department.getEmployees());
                }
            }
        });

        mViewModel.getDepartments().observe(this, new Observer<List<Department>>() {
            @Override
            public void onChanged(@Nullable List<Department> departments) {
                mDepartmentSpinnerAdapter.clear();

                if (departments != null) {
                    mDepartmentSpinnerAdapter.addAll(departments);
                }
            }
        });

        mViewModel.getStatus().observe(this, new Observer<MainViewModel.State>() {
            @Override
            public void onChanged(@Nullable MainViewModel.State state) {

                if (state == null) {
                    return;
                }

                switch (state) {
                    case ERROR:
                        onError();
                        break;
                    case DISPLAYING:
                        onDisplaying();
                        break;
                    case LOADING:
                        onLoading();
                        break;
                }
            }
        });
    }

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setLifecycleOwner(this);
        mBinding.setViewModel(mViewModel);
    }

    private void initToolbar() {
        mBinding.toolbar.setTitle(R.string.title_department);
        setSupportActionBar(mBinding.toolbar);
    }

    private void initEmployeeRecycler() {
        mEmployeeRecyclerAdapter = new EmployeeRecyclerAdapter(this, new EmployeeRecyclerAdapter.EmployeeCallback() {
            @Override
            public void imageDownload(Employee employee, ImageView imageView) {
                mViewModel.imageDownload(employee, imageView, ViewUtils.getEmployeeAvatarPlaceholder(MainActivity.this));
            }

            @Override
            public void onEmployeeClick(Employee employee, ImageView imageView) {
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this,
                        imageView,
                        ViewCompat.getTransitionName(imageView)
                );

                Intent intent = new Intent(MainActivity.this, EmployeeDetailActivity.class)
                        .putExtra(EmployeeDetailActivity.ARG_EMPLOYEE, employee)
                        .putExtra(EmployeeDetailActivity.ARG_DEPARTMENT, mViewModel.getDepartment().getValue());

                startActivity(intent, options.toBundle());
            }
        });

        Resources res = getResources();

        mBinding.employeeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.employeeRecyclerView.addItemDecoration(new ListItemMarginDecoration(UiUtils.dpToPixels(res, 8F)));
        mBinding.employeeRecyclerView.setAdapter(mEmployeeRecyclerAdapter);

    }

    private void initDepartmentSpinner() {
        mDepartmentSpinnerAdapter = new DepartmentSpinnerAdapter(this, R.layout.spinner_item);
        mDepartmentSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mBinding.departmentSpinner.setAdapter(mDepartmentSpinnerAdapter);
        mBinding.departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mViewModel.onDepartmentSelected(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });
    }

    //
    public void onClickRetry(View view) {
        mViewModel.loadDepartments();
    }

    private void hideAllLayers() {
        // hides all overlaid layers in order to show a single layer
        mBinding.employeeRecyclerView.setVisibility(View.GONE);
        mBinding.departmentError.setVisibility(View.GONE);
        mBinding.departmentLoadingProgressbar.setVisibility(View.GONE);
    }

    public void onLoading() {
        hideAllLayers();
        mBinding.departmentLoadingProgressbar.setVisibility(View.VISIBLE);
    }

    public void onError() {
        hideAllLayers();
        mBinding.departmentError.setVisibility(View.VISIBLE);
    }

    public void onDisplaying() {
        hideAllLayers();
        mBinding.employeeRecyclerView.setVisibility(View.VISIBLE);
    }
}
