package sk.brecka.sygicrecruitment.model.api.model;

import com.squareup.moshi.Json;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import sk.brecka.sygicrecruitment.model.business.Employee;

@Accessors(prefix = "m")
public class DepartmentJson {

    @Getter
    @Setter
    @Json(name = "Name")
    private String mName;

    @Getter
    @Setter
    @Json(name = "employees")
    private List<Employee> mEmployees;

}
