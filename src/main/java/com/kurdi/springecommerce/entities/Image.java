package com.kurdi.springecommerce.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Builder
public class Image {
    private String name;
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image other = (Image) o;
        if (!name.equals(other.name)) return false;
        if (!url.equals(other.url)) return false;

        return true;
    }
/*    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }*/
}
