package org.tpokora.storms.model;

public class Warning {

//    <xsd:all>
//<xsd:element name="od_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="do_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="mroz" type="xsd:int" nillable="true"/>
//<xsd:element name="mroz_od_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="mroz_do_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="upal" type="xsd:int" nillable="true"/>
//<xsd:element name="upal_od_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="upal_do_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="wiatr" type="xsd:int" nillable="true"/>
//<xsd:element name="wiatr_od_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="wiatr_do_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="opad" type="xsd:int" nillable="true"/>
//<xsd:element name="opad_od_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="opad_do_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="burza" type="xsd:int" nillable="true"/>
//<xsd:element name="burza_od_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="burza_do_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="traba" type="xsd:int" nillable="true"/>
//<xsd:element name="traba_od_dnia" type="xsd:string" nillable="true"/>
//<xsd:element name="traba_do_dnia" type="xsd:string" nillable="true"/>
//</xsd:all>

    private String fromDay;
    private String toDay;
    private int cold;
    private String coldFromDay;
    private String coldToDay;
    private int hot;
    private String hotFromDay;
    private String hotToDay;
    private int wind;
    private String windFromDay;
    private String windToDay;
    private int rain;
    private String rainFromDay;
    private String rainToDay;
    private int storm;
    private String stormFromDay;
    private String stormToDay;
    private int whirlwind;
    private String whirlwindFromDay;
    private String whirlwindToDay;

    public Warning() {

    }

    public String getFromDay() {
        return fromDay;
    }

    public void setFromDay(String fromDay) {
        this.fromDay = fromDay;
    }

    public String getToDay() {
        return toDay;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
    }

    public int getCold() {
        return cold;
    }

    public void setCold(int cold) {
        this.cold = cold;
    }

    public String getColdFromDay() {
        return coldFromDay;
    }

    public void setColdFromDay(String coldFromDay) {
        this.coldFromDay = coldFromDay;
    }

    public String getColdToDay() {
        return coldToDay;
    }

    public void setColdToDay(String coldToDay) {
        this.coldToDay = coldToDay;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getHotFromDay() {
        return hotFromDay;
    }

    public void setHotFromDay(String hotFromDay) {
        this.hotFromDay = hotFromDay;
    }

    public String getHotToDay() {
        return hotToDay;
    }

    public void setHotToDay(String hotToDay) {
        this.hotToDay = hotToDay;
    }

    public int getWind() {
        return wind;
    }

    public void setWind(int wind) {
        this.wind = wind;
    }

    public String getWindFromDay() {
        return windFromDay;
    }

    public void setWindFromDay(String windFromDay) {
        this.windFromDay = windFromDay;
    }

    public String getWindToDay() {
        return windToDay;
    }

    public void setWindToDay(String windToDay) {
        this.windToDay = windToDay;
    }

    public int getRain() {
        return rain;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }

    public String getRainFromDay() {
        return rainFromDay;
    }

    public void setRainFromDay(String rainFromDay) {
        this.rainFromDay = rainFromDay;
    }

    public String getRainToDay() {
        return rainToDay;
    }

    public void setRainToDay(String rainToDay) {
        this.rainToDay = rainToDay;
    }

    public int getStorm() {
        return storm;
    }

    public void setStorm(int storm) {
        this.storm = storm;
    }

    public String getStormFromDay() {
        return stormFromDay;
    }

    public void setStormFromDay(String stormFromDay) {
        this.stormFromDay = stormFromDay;
    }

    public String getStormToDay() {
        return stormToDay;
    }

    public void setStormToDay(String stormToDay) {
        this.stormToDay = stormToDay;
    }

    public int getWhirlwind() {
        return whirlwind;
    }

    public void setWhirlwind(int whirlwind) {
        this.whirlwind = whirlwind;
    }

    public String getWhirlwindFromDay() {
        return whirlwindFromDay;
    }

    public void setWhirlwindFromDay(String whirlwindFromDay) {
        this.whirlwindFromDay = whirlwindFromDay;
    }

    public String getWhirlwindToDay() {
        return whirlwindToDay;
    }

    public void setWhirlwindToDay(String whirlwindToDay) {
        this.whirlwindToDay = whirlwindToDay;
    }
}
