package hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper;


import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.FeedInfoBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.FeedInfoDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;

public class FeedInfoDTOMapper {

    public static FeedInfoBO fromDTO(FeedInfoDTO dto) {
        return FeedInfoBO.builder()
                .feedId(dto.getFeedId())
                .feedPublisherName(dto.getFeedPublisherName())
                .feedPublisherUrl(dto.getFeedPublisherUrl())
                .feedLang(dto.getFeedLang())
                .feedStartDate(MapperUtil.parseLocalDateOrDefault(dto.getFeedStartDate()))
                .feedEndDate(MapperUtil.parseLocalDateOrDefault(dto.getFeedEndDate()))
                .feedVersion(dto.getFeedVersion())
                .feedIncludesPrefixedIds(MapperUtil.parseBooleanOrDefault(dto.getFeedIncludesPrefixedIds()))
                .build();
    }
}
