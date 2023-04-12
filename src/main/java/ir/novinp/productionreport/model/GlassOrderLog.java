package ir.novinp.productionreport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GlassOrderLog extends OrderLog {
    private int status;
}
