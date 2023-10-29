package com.dalstackoverflow.backendserver.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * This is the Tag model class which will represent the tags table.
 * @author Sreejith Nair
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private int tagID;

    @Column(name = "q_id")
    private int questionID;

    @Column(name = "t_name")
    private String tagName;

}
