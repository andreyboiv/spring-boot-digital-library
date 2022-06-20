package com.boivalenko.businessapp.web.app.spring.entities;

import com.boivalenko.businessapp.web.app.spring.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "vote")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Cacheable(value = true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Vote extends BaseEntity {

    @Basic
    @Column(name = "value", nullable = true)
    private Integer value;

    @Basic
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Basic
    @Column(name = "username", nullable = false, length = 100)
    private String username;

}
