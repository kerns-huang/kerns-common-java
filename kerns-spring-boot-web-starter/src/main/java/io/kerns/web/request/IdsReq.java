package io.kerns.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 通用的id请求类
 * @author xiaohei
 * @create 2019-12-07 下午8:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdsReq {

    private List<Integer> ids;
}
