
package hu.palferi.kektura.kektura_volangeodata_downloader.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "calendar", schema = "gtfs_staging")
public class CalendarEntity extends BaseEntity {
    private String serviceId;
    private Integer monday;
    private Integer tuesday;
    private Integer wednesday;
    private Integer thursday;
    private Integer friday;
    private Integer saturday;
    private Integer sunday;
    private Date startDate;
    private Date endDate;
    private String serviceDesc;
}
