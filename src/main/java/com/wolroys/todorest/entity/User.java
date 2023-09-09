package com.wolroys.todorest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User extends AuditingEntity<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "user's id")
    private int id;

    @Schema(description = "user's name")
    private String username;

    @Schema(description = "user's email")
    private String email;

    @Schema(description = "user's password")
    private String password;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user")
    @Schema(description = "user's notes")
    private List<Note> notes = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user")
    @Schema(description = "user's tasks")
    private List<Task> tasks = new ArrayList<>();
}
