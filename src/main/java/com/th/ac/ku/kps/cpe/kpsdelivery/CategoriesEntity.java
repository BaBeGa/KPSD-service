package com.th.ac.ku.kps.cpe.kpsdelivery;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "delivery")
public class CategoriesEntity {
    private int idCategory;
    private String categoryName;

    @Id
    @Column(name = "id_category")
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesEntity that = (CategoriesEntity) o;
        return idCategory == that.idCategory &&
                Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, categoryName);
    }
}
