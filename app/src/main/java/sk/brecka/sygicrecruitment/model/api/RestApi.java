package sk.brecka.sygicrecruitment.model.api;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import sk.brecka.sygicrecruitment.model.business.Department;

public class RestApi {

    private static final String TAG = "RestApi";

    private DepartmentService mDepartmentService;

    public RestApi(DepartmentService departmentService) {
        mDepartmentService = departmentService;
    }

    public List<Department> loadDepartments() throws IOException {
        Log.d(TAG, "loadDepartments: ");
        return mDepartmentService.loadDepartments().execute().body();
    }
}
