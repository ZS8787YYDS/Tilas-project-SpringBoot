package com.zisheng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用于封装返回的结果，一个代表返回的记录数，一个代表返回的数据记录，为一个集合。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    private Long total;//用于记录查询的结果数目
    private List<Emp> rows;//用于存储返回的数据
}
