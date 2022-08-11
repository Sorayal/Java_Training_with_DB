package main_package;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(generator = "uuid-gen")
    @GenericGenerator(name="uuid-gen", strategy="uuid2")
    @Column(name = "id", length=16, updatable = false, nullable = false)
    private UUID id;

    private String title;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
