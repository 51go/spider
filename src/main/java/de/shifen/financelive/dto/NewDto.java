package de.shifen.financelive.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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
}
