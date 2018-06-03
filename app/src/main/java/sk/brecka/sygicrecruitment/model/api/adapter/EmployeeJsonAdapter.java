package sk.brecka.sygicrecruitment.model.api.adapter;

import com.squareup.moshi.FromJson;

import sk.brecka.sygicrecruitment.model.api.model.EmployeeJson;
import sk.brecka.sygicrecruitment.model.business.Employee;

public class EmployeeJsonAdapter {

    @FromJson
    Employee employeeFromJson(EmployeeJson employeeJson) {
        return new Employee(employeeJson.getFirstName(), employeeJson.getLastName(), employeeJson.getAvatarUrl(), employeeJson.getPosition());
    }
}
