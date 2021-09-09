package com.qiao.gaokaoforecast.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author QYQ
 */
@AllArgsConstructor
@NoArgsConstructor
public class School {
    private int school_id;
    private String school_desc;
    private String school_name;

    @Override
    public String toString() {
        return "Schools{" +
                "school_id=" + school_id +
                ", school_desc='" + school_desc + '\'' +
                ", school_name='" + school_name + '\'' +
                '}';
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getSchool_desc() {
        return school_desc;
    }

    public void setSchool_desc(String school_desc) {
        this.school_desc = school_desc;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }
}
