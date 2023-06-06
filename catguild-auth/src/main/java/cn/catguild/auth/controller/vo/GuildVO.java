package cn.catguild.auth.controller.vo;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author lz
 * @date 2023/5/14 17:16
 */
@Data
public class GuildVO {

    private Long id;

    private String name;

    private String code;

    private String avatar;

    private String registerCountry;

    private String registerProvince;

    private String registerCity;

    private String registerAddress;

    private String status;

    private LocalDateTime registerTime;

}
