package com.bituos;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class MappingDynamicJSON {
	
 
    
    Map<String, Object> fields = new LinkedHashMap<String, Object>();
    
    @JsonAnySetter
    public void setFields(String key, Object value) {
    	fields.put(key, value);
    }
    
    @JsonAnyGetter
    public Map<String, Object> getFields() {
        return fields;
    }
}
