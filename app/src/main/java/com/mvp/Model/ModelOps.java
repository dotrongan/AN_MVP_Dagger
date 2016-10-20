package com.mvp.Model;

import java.util.List;

/**
 * Created by ANDT on 9/23/2016.
 */

public interface ModelOps {
    void getData(List<String> domains);
    void createParseData(String domain);
    void setData(List<Data> lstData);
    List<Data> getDataAll();
}
