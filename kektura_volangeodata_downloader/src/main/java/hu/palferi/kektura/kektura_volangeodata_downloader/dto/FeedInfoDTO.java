package hu.palferi.kektura.kektura_volangeodata_downloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class FeedInfoDTO {
    private String feedId;
    private String feedPublisherName;
    private String feedPublisherUrl;
    private String feedLang;
    private String feedStartDate;
    private String feedEndDate;
    private String feedVersion;
    private String feedIncludesPrefixedIds;
}
