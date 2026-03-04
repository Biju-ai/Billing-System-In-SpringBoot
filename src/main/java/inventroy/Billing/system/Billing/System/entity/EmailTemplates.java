package inventroy.Billing.system.Billing.System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="html_templates")
public class EmailTemplates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_date",nullable = false)
    private LocalDateTime createdDate;

    @Column(name="template")
    private String template;

    @Column(name="update_date")
    private LocalDateTime updatedDate;

    @Column(name="template_name")
    private String templateName;


}
