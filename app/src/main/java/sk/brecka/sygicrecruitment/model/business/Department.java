package sk.brecka.sygicrecruitment.model.business;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@ToString
public class Department implements Parcelable {

    @Getter
    private final String mName;

    @Getter
    private final List<Employee> mEmployees;

    public Department(String name, List<Employee> employees) {
        mName = name;
        mEmployees = employees;
    }

    protected Department(Parcel in) {
        mName = in.readString();
        mEmployees = in.readArrayList(Department.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeList(mEmployees);
    }

    public static final Creator<Department> CREATOR = new Creator<Department>() {
        public Department createFromParcel(Parcel source) {
            return new Department(source);
        }

        public Department[] newArray(int size) {
            return new Department[size];
        }
    };
}
