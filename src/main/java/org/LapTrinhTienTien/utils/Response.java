package org.LapTrinhTienTien.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Response {
    // tra ve thong bao
    String message;
    // kieu tra tinh dung sai cua function
    Boolean flag;
    // chua du lieu can tra ve
    Object data;
}
