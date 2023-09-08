package com.zisheng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String message;
    private Object data;
    public static Result success(Object data)
    {
        return new Result(1,"success",data);
    }
    public static Result success()
    {
        return new Result(1,"success",null);
    }
    public static Result error()
    {
        return new Result(0,"failure",null);
    }
    public static Result error(Object data)
    {
        return new Result(0, (String) data,null);
    }
}
