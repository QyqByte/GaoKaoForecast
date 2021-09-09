package com.qiao.gaokaoforecast.domain;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Major {
    private String major_name;
    private String major_batch;
    private int major_min_score;
    private int major_min_section;
    private int major_school_id;
    private int major_province;
    private int major_year;
    private String major_average;

    @Override
    public String toString() {
        return "Major{" +
                "major_name='" + major_name + '\'' +
                ", major_batch='" + major_batch + '\'' +
                ", major_min_score=" + major_min_score +
                ", major_min_section=" + major_min_section +
                ", major_school_id=" + major_school_id +
                ", major_province=" + major_province +
                ", major_year=" + major_year +
                ", average='" + major_average + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Major major = (Major) o;
        String[] major_temp1 = major_name.split("（");
        String[] major_temp2 = major.major_name.split("（");
        return major_school_id == major.major_school_id &&
                major_province == major.major_province &&
                major_temp1[0].equals(major_temp2[0]) &&
                major_batch.equals(major.major_batch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(major_name.split("（")[0], major_batch, major_school_id, major_province);
    }
}
