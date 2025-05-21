package hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper;

import java.sql.Date;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.FeedInfoBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.FeedInfoEntity;

public class FeedInfoEntityMapper {

    public static FeedInfoEntity fromBO(FeedInfoBO bo) {
        return FeedInfoEntity.builder()
                .feedId(bo.getFeedId())
                .feedPublisherName(bo.getFeedPublisherName())
                .feedPublisherUrl(bo.getFeedPublisherUrl())
                .feedLang(bo.getFeedLang())
                .feedStartDate(Date.valueOf(bo.getFeedStartDate()))
                .feedEndDate(Date.valueOf(bo.getFeedEndDate()))
                .feedVersion(bo.getFeedVersion())
                .feedIncludesPrefixedIds(bo.getFeedIncludesPrefixedIds())
                .build();
    }
}
