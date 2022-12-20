package new_post.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department", schema = "new_post_lab5", catalog = "")
public class DepartmentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "number")
    private Integer number;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "region")
    private String region;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(number, that.number) && Objects.equals(city, that.city) && Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, number, city, region);
    }
}
