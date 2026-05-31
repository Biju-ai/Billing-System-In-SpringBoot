package inventroy.Billing.system.Billing.System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.Template;

import java.time.LocalDateTime;
@Entity
@Table(name = "supplier_msg")
@Getter
@Setter
public class SupplierEmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_msg_id")
    private Long supplierMsgId;

    @ManyToOne
    @JoinColumn(name = "suppliers_id", nullable = false)
    private Suppliers suppliers;

    @Column(name = "remarks", nullable = false, length = 500)
    private String remarks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "template")
    private String template;
}
