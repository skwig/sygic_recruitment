package sk.brecka.sygicrecruitment.model.business;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@ToString
public class Employee implements Parcelable {

    @Getter
    private final String mFirstName;

    @Getter
    private final String mLastName;

    @Getter
    private final String mAvatarUrl;

    @Getter
    private final String mPosition;

    public Employee(String firstName, String lastName, String avatarUrl, String position) {
        mFirstName = firstName;
        mLastName = lastName;
        mAvatarUrl = avatarUrl;
        mPosition = position;
    }

    protected Employee(Parcel in) {
        mFirstName = in.readString();
        mLastName = in.readString();
        mAvatarUrl = in.readString();
        mPosition = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeString(mAvatarUrl);
        dest.writeString(mPosition);
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}
