package src.com.domain;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "label_id")
    private Integer label_id;

    @Column(name = "label")
    private String label;

    public Label(String label) {
        this.label = label;
    }

    public Label() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
