package sk.brecka.sygicrecruitment.model.api.model;

import com.squareup.moshi.Json;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
public class EmployeeJson {
    @Getter
    @Setter
    @Json(name = "firstName")
    String mFirstName;

    @Getter
    @Setter
    @Json(name = "lastName")
    String mLastName;

    @Getter
    @Setter
    @Json(name = "avatar")
    String mAvatarUrl;

    @Getter
    @Setter
    @Json(name = "position")
    String mPosition;
}
