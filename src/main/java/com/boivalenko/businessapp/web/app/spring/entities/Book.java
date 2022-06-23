package com.boivalenko.businessapp.web.app.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "book")
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Cacheable(value = true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image", nullable = true)
    @Lob
    @JsonIgnore
    private byte[] image;

    @Transient
    private StreamedContent streamedContentImage;

    @Transient
    private String imageAsString;

    public void setImageAsStreamedContent() {
        this.streamedContentImage = new DefaultStreamedContent(
                new ByteArrayInputStream(image),
                "image/jpeg",
                name);
    }

    public void setImageAsString() {
        this.imageAsString = Base64.encodeBase64String(this.image);
    }

    public Book(Long id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    // Hier wird das Field "content" nicht ausgefüllt.
    // Denn das kann System belasten
    public Book(Long id, byte[] image, String name, Integer pageCount, String isbn, Integer publishYear, Integer avgRating, Long totalVoteCount, Long totalRating, Long viewCount, String descr, Genre genre, Author author, Publisher publisher) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.avgRating = avgRating;
        this.totalVoteCount = totalVoteCount;
        this.totalRating = totalRating;
        this.viewCount = viewCount;
        this.descr = descr;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content", nullable = true)
    @Lob
    @JsonIgnore
    private byte[] content;

    @Basic
    @Column(name = "page_count", nullable = false)
    private Integer pageCount;

    @Basic
    @Column(name = "isbn", nullable = false, length = 100)
    private String isbn;

    @Basic
    @Column(name = "publish_year", nullable = false)
    private Integer publishYear;

    @Basic
    @Column(name = "avg_rating", nullable = true)
    private Integer avgRating;

    @Basic
    @Column(name = "total_vote_count", nullable = true)
    private Long totalVoteCount;

    @Basic
    @Column(name = "total_rating", nullable = true)
    private Long totalRating;

    @Basic
    @Column(name = "view_count", nullable = true)
    private Long viewCount;

    @Basic
    @Column(name = "descr", nullable = true, length = 500)
    private String descr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    @JsonIgnore
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    @JsonIgnore
    private Publisher publisher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return this.name;
    }

}
