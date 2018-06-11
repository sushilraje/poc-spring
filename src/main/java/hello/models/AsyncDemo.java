package hello.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import hello.models.nested.DemoData;

public class AsyncDemo {

    private DemoData data;

    AsyncDemo(@JsonProperty("data") DemoData data) {
        this.data = data;
    }

    public DemoData getData() {
        return data;
    }

    public void setData(DemoData data) {
        this.data = data;
    }

}

