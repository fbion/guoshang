package com.esoft.yeepay.withdraw.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esoft.archer.config.controller.ConfigHome;
import com.esoft.archer.system.controller.LoginUserInfo;
import com.esoft.archer.user.UserConstants;
import com.esoft.archer.user.controller.WithdrawHome;
import com.esoft.archer.user.exception.InsufficientBalance;
import com.esoft.archer.user.model.User;
import com.esoft.archer.user.model.WithdrawCash;
import com.esoft.archer.user.service.WithdrawCashService;
import com.esoft.archer.user.service.impl.UserBillBO;
import com.esoft.core.annotations.ScopeType;
import com.esoft.core.jsf.util.FacesUtil;
import com.esoft.yeepay.withdraw.service.impl.YeePayWithdrawNofreezeOperation;
import com.esoft.yeepay.withdraw.service.impl.YeePayWithdrawOperation;

@Component
@Scope(ScopeType.VIEW)
public class YeePayWithdrawHome extends WithdrawHome {
    @Resource
    WithdrawCashService wcs;

    @Resource
    UserBillBO userBillBO;

    @Resource
    LoginUserInfo loginUserInfo;

    @Resource
    YeePayWithdrawOperation yeePayWithdrawOperation;
    @Resource
    YeePayWithdrawNofreezeOperation nofreezeOperation;
    @Resource
    ConfigHome configHome;

    /**
     * 提现
     */
    @Override
    public String withdraw() {


        double temp=userBillBO.getBalance(this.getInstance().getUser().getId());
        try {
            WithdrawCash wc = this.getInstance();
            if(wc.getMoney()<=2){
                FacesUtil.addErrorMessage("提款金额不能少于或等于手续费2元！");
                return null;
            }
            String userId = loginUserInfo.getLoginUserId();
            wc.setUser(new User(userId));
            if ("0".equals(configHome.getConfigValue("freeze_money"))) {
                double fee = wcs.calculateFee(wc.getMoney());
                wc.setMoney(wc.getMoney() - fee);
                if (fee + wc.getMoney()>temp) {
                    throw new InsufficientBalance();
                }
                wc.setFee(fee);
                wc.setCashFine(0d);
                wc.setStatus(UserConstants.WithdrawStatus.WAIT_VERIFY);
                nofreezeOperation.createOperation(wc, FacesUtil.getCurrentInstance());
            } else {
                wcs.applyWithdrawCash(wc);
                yeePayWithdrawOperation.createOperation(wc, FacesContext.getCurrentInstance());
            }
        } catch (InsufficientBalance e) {
            this.getInstance().setMoney(0D);
            FacesUtil.addErrorMessage("余额不足,您的最大提现金额为："+temp+"元");
            return null;
        } catch (IOException e) {
            FacesUtil.addErrorMessage("提现出错！");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Class<WithdrawCash> getEntityClass() {
        return WithdrawCash.class;
    }
}
