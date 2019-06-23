package org.tpokora.blog.model;

import org.hibernate.annotations.Type;
import org.tpokora.common.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Entry extends AbstractEntity {

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "title")
    private String title;

    @Lob
    @Size(min = 0, max = 9000)
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "CONTENT")
    private String content;

//    private User user;

    public Entry(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
