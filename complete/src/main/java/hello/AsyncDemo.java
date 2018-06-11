package hello;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AsyncDemo {

    private int page;
    private int perPage;
    private int total;
    private int total_pages;
    private List<DemoData> data;

    @JsonCreator
    AsyncDemo(@JsonProperty("page") int page, @JsonProperty("perPage") int perPage, @JsonProperty("total") int total, @JsonProperty("total_pages") int total_pages, @JsonProperty("data") List<DemoData> data) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<DemoData> getData() {
        return data;
    }

    public void setData(List<DemoData> data) {
        this.data = data;
    }


}
