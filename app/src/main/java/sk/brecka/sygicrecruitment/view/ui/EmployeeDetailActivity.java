package sk.brecka.sygicrecruitment.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import sk.brecka.sygicrecruitment.R;
import sk.brecka.sygicrecruitment.databinding.ActivityEmployeeDetailBinding;
import sk.brecka.sygicrecruitment.model.business.Department;
import sk.brecka.sygicrecruitment.model.business.Employee;
import sk.brecka.sygicrecruitment.viewmodel.EmployeeDetailViewModel;

public class EmployeeDetailActivity extends BaseActivity<EmployeeDetailViewModel> {

    public static final String ARG_EMPLOYEE = "arg_employee";
    public static final String ARG_DEPARTMENT = "arg_department";

    private ActivityEmployeeDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getComponent().inject(this);

        final Employee employee = getIntent().getParcelableExtra(ARG_EMPLOYEE);
        final Department department = getIntent().getParcelableExtra(ARG_DEPARTMENT);

        initViewModel(employee, department);
        initBinding();

        initToolbar();
        initEmployeeImage(employee);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_employee_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }

    private void initViewModel(Employee employee, Department department) {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EmployeeDetailViewModel.class);

        mViewModel.setEmployee(employee);
        mViewModel.setDepartment(department);
        mViewModel.getEmployee().observe(this, new Observer<Employee>() {
            @Override
            public void onChanged(@Nullable Employee employee) {

                if (employee == null) {
                    return;
                }

                // set the employee's full name as the title
                getSupportActionBar().setTitle(employee.getFirstName() + " " + employee.getLastName());
            }
        });
    }

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_employee_detail);
        mBinding.setLifecycleOwner(this);
        mBinding.setViewModel(mViewModel);
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initEmployeeImage(Employee employee) {
        mViewModel.imageDownload(employee, mBinding.employeeImage);
    }
}
