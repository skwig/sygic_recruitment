package sk.brecka.sygicrecruitment.model;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import sk.brecka.sygicrecruitment.model.api.RestApi;
import sk.brecka.sygicrecruitment.model.business.Department;

public class Repository {

    private static final String TAG = "Repository";

    private RestApi mRestApi;

    public Repository(RestApi restApi) {
        mRestApi = restApi;
    }

    public List<Department> loadDepartments() throws IOException {
        Log.d(TAG, "loadDepartments: ");
        return mRestApi.loadDepartments();
    }
}
