package com.esoft.archer.managelog.controller;

import com.esoft.archer.common.controller.EntityQuery;
import com.esoft.archer.managelog.model.ManageLog;
import com.esoft.archer.subst.model.Subst;
import com.esoft.archer.user.model.User;
import com.esoft.core.annotations.Logger;
import com.esoft.core.annotations.ScopeType;
import org.apache.commons.logging.Log;
import org.primefaces.model.LazyDataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Administrator on 2015/12/15 0015.
 */
@Component
@Scope(ScopeType.VIEW)
public class ManageLogList extends EntityQuery<ManageLog> implements
        java.io.Serializable{


    private static final long serialVersionUID = 9057256750876812237L;


    private Date startTime ;
    private Date endTime ;

    @Logger
    private static Log log;

    public ManageLogList() {
        final String[] RESTRICTIONS = { "id like #{manageLogList.example.id}",
                "createTime >= #{manageLogList.startTime}",
                "createTime <= #{manageLogList.endTime}",
                "user.username like #{manageLogList.example.user.username}"};
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    protected void initExample() {
        super.initExample();
        User user = new User();
        getExample().setUser(user);
    }



}
