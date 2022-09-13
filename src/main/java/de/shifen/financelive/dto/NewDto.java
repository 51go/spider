package de.shifen.financelive.dto;

import lombok.*;
import org.ansj.app.keyword.Keyword;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

/**
 * @author ms404
 */
@Table(
        indexes = {
                @Index(name="uniqe_source_external_id",columnList = "external_unique_id,source",unique = true)
        }
)
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@TypeDef(name="json",typeClass = JsonStringType.class)
public class NewDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name="source",columnDefinition = "varchar(32)")
    String source;

    @Column(name="external_unique_id",columnDefinition = "varchar(32)")
    String externalUniqueId;

    @Column(name="title",columnDefinition = "varchar(256)")
    String title;
    @Column(name="content",columnDefinition = "text")
    String content;
    @Column(name="content_text",columnDefinition = "text")
    String contentText;

    @Column(name="display_time",columnDefinition = "bigint(20)")
    long displayTime;

    @Column(name="author_name",columnDefinition = "varchar(32)")
    String authorName;

    @Column(name="author_avatar",columnDefinition = "varchar(256)")
    String authorAvatar;

    @Column(name="author_id",columnDefinition = "varchar(32)")
    String authorId;

    @Column(name="from_where",columnDefinition = "varchar(32)")
    String fromWhere;

    @Column(name="nl_handled_at",columnDefinition = "bigint(20) default 0")
    long nlpHandledAt;


}
