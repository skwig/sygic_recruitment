package sk.brecka.sygicrecruitment.model.api.adapter;

import com.squareup.moshi.FromJson;

import sk.brecka.sygicrecruitment.model.api.model.DepartmentJson;
import sk.brecka.sygicrecruitment.model.business.Department;


public class DepartmentJsonAdapter {

    @FromJson
    Department departmentFromJson(DepartmentJson departmentJson) {
        return new Department(departmentJson.getName(), departmentJson.getEmployees());
    }
}
