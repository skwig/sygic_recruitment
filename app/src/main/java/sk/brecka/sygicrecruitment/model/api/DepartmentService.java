package sk.brecka.sygicrecruitment.model.api;

import com.serjltt.moshi.adapters.Wrapped;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sk.brecka.sygicrecruitment.model.business.Department;

public interface DepartmentService {
    @GET("departments")
    @Wrapped(path = {"Departments"})
    Call<List<Department>> loadDepartments();
}
