package com.boivalenko.businessapp.web.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "book")
@AllArgsConstructor
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
    private byte[] image;

    public Book(Long id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content", nullable = true)
    @Lob
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
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
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
