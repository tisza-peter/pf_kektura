package hu.palferi.kektura.kektura_volangeodata_downloader.businessObject;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedInfoBO {
    private String feedId;
    private String feedPublisherName;
    private String feedPublisherUrl;
    private String feedLang;
    private LocalDate feedStartDate;
    private LocalDate feedEndDate;
    private String feedVersion;
    private Boolean feedIncludesPrefixedIds;
}
