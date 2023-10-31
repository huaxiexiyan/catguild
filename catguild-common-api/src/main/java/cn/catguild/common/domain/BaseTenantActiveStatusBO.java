package cn.catguild.common.domain;

import cn.catguild.common.type.ActiveStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/10/30 17:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseTenantActiveStatusBO extends BaseTenantBO {

    /**
     * 资源可用状态
     */
    private ActiveStatus activeStatus;

    @JsonIgnore
    public void switchActiveStatusInactive(){
        if (this.activeStatus != ActiveStatus.INACTIVE){
            this.activeStatus = ActiveStatus.INACTIVE;
        }
    }
    @JsonIgnore
    public void switchActiveStatusActive(){
        if (this.activeStatus != ActiveStatus.ACTIVE){
            this.activeStatus = ActiveStatus.ACTIVE;
        }
    }
    @JsonIgnore
    public void switchActiveStatus(){
        if (this.activeStatus == ActiveStatus.ACTIVE){
            this.activeStatus = ActiveStatus.INACTIVE;
        }else {
            this.activeStatus = ActiveStatus.ACTIVE;
        }
    }

}
