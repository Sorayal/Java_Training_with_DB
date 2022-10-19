package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Aircraft")
public class Aircraft {

    @Id
    @Column(name = "id", length = 16, updatable = false, nullable = false)
    private int Id;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "name", length = 255)
    private String name;

    public int getId() {
        return Id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "Id=" + Id +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
