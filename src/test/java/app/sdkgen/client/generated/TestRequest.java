/**
 * TestRequest automatically generated by SDKgen please do not edit this file manually
 * @see https://sdkgen.app
 */

package app.sdkgen.client.generated;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
public class TestRequest<TestObject> {
    private Integer _int;
    private Double _float;
    private String string;
    private Boolean bool;
    private java.time.LocalDate dateString;
    private java.time.LocalDateTime dateTimeString;
    private java.time.LocalTime timeString;
    private java.util.List<String> arrayScalar;
    private java.util.List<TestObject> arrayObject;
    private TestMapScalar mapScalar;
    private TestMapObject mapObject;
    private TestObject object;
    @JsonSetter("int")
    public void setInt(Integer _int) {
        this._int = _int;
    }
    @JsonGetter("int")
    public Integer getInt() {
        return this._int;
    }
    @JsonSetter("float")
    public void setFloat(Double _float) {
        this._float = _float;
    }
    @JsonGetter("float")
    public Double getFloat() {
        return this._float;
    }
    @JsonSetter("string")
    public void setString(String string) {
        this.string = string;
    }
    @JsonGetter("string")
    public String getString() {
        return this.string;
    }
    @JsonSetter("bool")
    public void setBool(Boolean bool) {
        this.bool = bool;
    }
    @JsonGetter("bool")
    public Boolean getBool() {
        return this.bool;
    }
    @JsonSetter("dateString")
    public void setDateString(java.time.LocalDate dateString) {
        this.dateString = dateString;
    }
    @JsonGetter("dateString")
    public java.time.LocalDate getDateString() {
        return this.dateString;
    }
    @JsonSetter("dateTimeString")
    public void setDateTimeString(java.time.LocalDateTime dateTimeString) {
        this.dateTimeString = dateTimeString;
    }
    @JsonGetter("dateTimeString")
    public java.time.LocalDateTime getDateTimeString() {
        return this.dateTimeString;
    }
    @JsonSetter("timeString")
    public void setTimeString(java.time.LocalTime timeString) {
        this.timeString = timeString;
    }
    @JsonGetter("timeString")
    public java.time.LocalTime getTimeString() {
        return this.timeString;
    }
    @JsonSetter("arrayScalar")
    public void setArrayScalar(java.util.List<String> arrayScalar) {
        this.arrayScalar = arrayScalar;
    }
    @JsonGetter("arrayScalar")
    public java.util.List<String> getArrayScalar() {
        return this.arrayScalar;
    }
    @JsonSetter("arrayObject")
    public void setArrayObject(java.util.List<TestObject> arrayObject) {
        this.arrayObject = arrayObject;
    }
    @JsonGetter("arrayObject")
    public java.util.List<TestObject> getArrayObject() {
        return this.arrayObject;
    }
    @JsonSetter("mapScalar")
    public void setMapScalar(TestMapScalar mapScalar) {
        this.mapScalar = mapScalar;
    }
    @JsonGetter("mapScalar")
    public TestMapScalar getMapScalar() {
        return this.mapScalar;
    }
    @JsonSetter("mapObject")
    public void setMapObject(TestMapObject mapObject) {
        this.mapObject = mapObject;
    }
    @JsonGetter("mapObject")
    public TestMapObject getMapObject() {
        return this.mapObject;
    }
    @JsonSetter("object")
    public void setObject(TestObject object) {
        this.object = object;
    }
    @JsonGetter("object")
    public TestObject getObject() {
        return this.object;
    }
}
