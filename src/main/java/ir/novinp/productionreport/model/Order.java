package ir.novinp.productionreport.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;


    private String name;
    private String picture;
    private int windowCount;
    private double windowMeter;
    private int glassCount;
    private double glassMeter;

    private int windowProductionStatus;
    private boolean windowProductionStepDone;

    private int glassProductionStatus;
    private boolean glassProductionStepDone;

    @CreationTimestamp
    private LocalDate creationDate;

    @UpdateTimestamp
    private LocalDate lastModificationDate;

    private LocalDate completeDate;

    private LocalDate outDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_app_id")
    private AppUser appUser;

    @OneToMany(cascade = CascadeType.REMOVE,
            mappedBy = "order",
            fetch = FetchType.LAZY)
    private List<WindowOrderLog> winLogList;

    @OneToMany(cascade = CascadeType.REMOVE,
            mappedBy = "order",
            fetch = FetchType.LAZY)
    private List<GlassOrderLog> glassLogList;
}
