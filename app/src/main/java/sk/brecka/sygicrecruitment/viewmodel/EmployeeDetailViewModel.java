package sk.brecka.sygicrecruitment.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import lombok.Getter;
import lombok.experimental.Accessors;
import sk.brecka.sygicrecruitment.model.business.Department;
import sk.brecka.sygicrecruitment.model.business.Employee;

@Accessors(prefix = "m")
public class EmployeeDetailViewModel extends ViewModel {

    private static final String TAG = "EmployeeDetailViewModel";

    @Getter
    private MutableLiveData<Employee> mEmployee = new MutableLiveData<>();

    @Getter
    private MutableLiveData<Department> mDepartment = new MutableLiveData<>();

    //
    private Picasso mPicasso;

    @Inject
    public EmployeeDetailViewModel(Picasso picasso) {
        mPicasso = picasso;
    }

    public void setDepartment(Department department) {
        mDepartment.setValue(department);
    }

    public void setEmployee(Employee employee) {
        mEmployee.setValue(employee);
    }

    public void imageDownload(Employee employee, ImageView imageView) {
        Log.d(TAG, "imageDownload: " + employee);
        mPicasso.load(employee.getAvatarUrl())
                .into(imageView);
    }
}
