package com.he.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    public String ea;   // ct a
    public String eb;   // ct b
    public String eab;  // ct ab
    public String ab;   // ab
    public String time;
}
