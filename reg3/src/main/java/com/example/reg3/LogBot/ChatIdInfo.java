package com.example.reg3.LogBot;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="ChatIdInfo")
public class ChatIdInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "chat", nullable = false)
    private Long chatId;

    @Column(name = "userName")
    private String userName;
}
