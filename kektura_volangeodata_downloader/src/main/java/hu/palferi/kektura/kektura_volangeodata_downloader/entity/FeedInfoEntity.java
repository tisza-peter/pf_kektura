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
@Table(name = "feed_info", schema = "gtfs_staging")
public class FeedInfoEntity extends BaseEntity {
    private String feedId;
    private String feedPublisherName;
    private String feedPublisherUrl;
    private String feedLang;
    private Date feedStartDate;
    private Date feedEndDate;
    private String feedVersion;
    private Boolean feedIncludesPrefixedIds;
}
