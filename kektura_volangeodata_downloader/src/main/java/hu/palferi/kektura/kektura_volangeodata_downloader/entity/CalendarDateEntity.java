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
@Table(name = "calendar_dates", schema = "gtfs_staging")
public class CalendarDateEntity extends BaseEntity {
    private String serviceId;
    private Date date;
    private Integer exceptionType;
}
