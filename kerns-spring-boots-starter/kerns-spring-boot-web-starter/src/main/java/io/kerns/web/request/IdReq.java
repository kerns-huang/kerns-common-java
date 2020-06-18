package io.kerns.web.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author 通用的主键id请求
 */
@Data
@Builder
public class IdReq {
    private Integer id;
}
