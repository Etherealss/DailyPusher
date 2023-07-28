package cn.seven.dailypusher.weather.dto.constant;

public enum WeatherConstant {

    BEI_JING("北京", "101010100"),
    WU_HAN("武汉", "101200101"),
    SHI_YAN("十堰", "101201101"),
    ;


    private String cityName;
    private String code;

    WeatherConstant(String cityName, String code) {
        this.cityName = cityName;
        this.code = code;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCode() {
        return code;
    }
}
