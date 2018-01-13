package src.com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "newsobject")
public class NewsObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min = 1, message = "Invalid author name length")
    @Column(name = "author")
    private String author;

    @Column(name = "text")
    private String text;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "newsobject_labels", joinColumns = {
            @JoinColumn(name = "newsobject_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "label_id",
                    nullable = false, updatable = false) })
    private Set<Label> labels;

    @NotNull
    @Column(name = "publish_date")
    private Date publishDate;

    public NewsObject(String author, String text, Date publishDate) {
        this.author = author;
        this.text = text;
        this.publishDate = publishDate;
    }

    public NewsObject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
