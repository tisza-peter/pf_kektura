package hu.palferi.kektura.kektura_volangeodata_downloader.enums;

public enum FeedInfoCsvHeader {
    FEED_ID("feed_id", "feedId"),
    FEED_PUBLISHER_NAME("feed_publisher_name", "feedPublisherName"),
    FEED_PUBLISHER_URL("feed_publisher_url", "feedPublisherUrl"),
    FEED_LANG("feed_lang", "feedLang"),
    FEED_START_DATE("feed_start_date", "feedStartDate"),
    FEED_END_DATE("feed_end_date", "feedEndDate"),
    FEED_VERSION("feed_version", "feedVersion"),
    FEED_INCLUDES_PREFIXED_IDS("feed_includes_prefixed_ids", "feedIncludesPrefixedIds"),;

    private String headerName;
    private String propertyName;



    FeedInfoCsvHeader(String headerName, String propertyName) {
        this.headerName = headerName;
        this.propertyName = propertyName;
    };
    
    public String getHeaderName() {
        return headerName;
    }

    public String getPropertyName() {
        return propertyName;
    }

}