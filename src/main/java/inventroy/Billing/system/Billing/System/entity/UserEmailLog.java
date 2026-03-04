package inventroy.Billing.system.Billing.System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_login_email")
public class UserEmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email",nullable = false ,length=100)
    private String email;

    @Column(name="message",nullable = false )
    private String message;

    @Column(name="created_at",nullable = false )
    private LocalDateTime createdAt;

    @Column(name="is_send")
    private boolean issend;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName ="user_id")
    private Users user;

}
