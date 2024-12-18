package com.idi.backend.adpters.output.response;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponseRest {

    private List<HashMap<String, String>> metadata = new ArrayList<>();

    public List<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String tipo, String codigo, String dato) {
        HashMap<String, String> map = new HashMap<>();
        map.put("tipo", tipo);
        map.put("codigo", codigo);
        map.put("dato", dato);
        metadata.clear();
        metadata.add(map);
    }
}
