/**
 * TestResponse automatically generated by SDKgen please do not edit this file manually
 * @see https://sdkgen.app
 */

package app.sdkgen.client.generated;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
public class TestResponse {
    private TestMapScalar args;
    private String data;
    private TestMapScalar files;
    private TestMapScalar form;
    private TestMapScalar headers;
    private TestRequest json;
    private String method;
    @JsonSetter("args")
    public void setArgs(TestMapScalar args) {
        this.args = args;
    }
    @JsonGetter("args")
    public TestMapScalar getArgs() {
        return this.args;
    }
    @JsonSetter("data")
    public void setData(String data) {
        this.data = data;
    }
    @JsonGetter("data")
    public String getData() {
        return this.data;
    }
    @JsonSetter("files")
    public void setFiles(TestMapScalar files) {
        this.files = files;
    }
    @JsonGetter("files")
    public TestMapScalar getFiles() {
        return this.files;
    }
    @JsonSetter("form")
    public void setForm(TestMapScalar form) {
        this.form = form;
    }
    @JsonGetter("form")
    public TestMapScalar getForm() {
        return this.form;
    }
    @JsonSetter("headers")
    public void setHeaders(TestMapScalar headers) {
        this.headers = headers;
    }
    @JsonGetter("headers")
    public TestMapScalar getHeaders() {
        return this.headers;
    }
    @JsonSetter("json")
    public void setJson(TestRequest json) {
        this.json = json;
    }
    @JsonGetter("json")
    public TestRequest getJson() {
        return this.json;
    }
    @JsonSetter("method")
    public void setMethod(String method) {
        this.method = method;
    }
    @JsonGetter("method")
    public String getMethod() {
        return this.method;
    }
}
