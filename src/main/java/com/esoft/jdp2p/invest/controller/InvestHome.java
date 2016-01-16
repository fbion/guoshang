package com.esoft.jdp2p.invest.controller;

import java.io.Serializable;

import javax.annotation.Resource;

import com.esoft.archer.common.exception.NoMatchingObjectsException;
import com.esoft.archer.user.exception.UserNotFoundException;
import com.esoft.jdp2p.loan.service.LoanCalculator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esoft.archer.common.controller.EntityHome;
import com.esoft.archer.coupon.exception.ExceedDeadlineException;
import com.esoft.archer.coupon.exception.UnreachedMoneyLimitException;
import com.esoft.archer.system.controller.LoginUserInfo;
import com.esoft.archer.user.exception.InsufficientBalance;
import com.esoft.archer.user.model.User;
import com.esoft.archer.user.service.UserService;
import com.esoft.core.annotations.ScopeType;
import com.esoft.core.jsf.util.FacesUtil;
import com.esoft.jdp2p.invest.InvestConstants;
import com.esoft.jdp2p.invest.exception.ExceedInvestTransferMoney;
import com.esoft.jdp2p.invest.exception.ExceedMaxAcceptableRate;
import com.esoft.jdp2p.invest.exception.ExceedMoneyNeedRaised;
import com.esoft.jdp2p.invest.exception.IllegalLoanStatusException;
import com.esoft.jdp2p.invest.model.Invest;
import com.esoft.jdp2p.invest.model.TransferApply;
import com.esoft.jdp2p.invest.service.InvestService;
import com.esoft.jdp2p.invest.service.TransferService;
import com.esoft.jdp2p.loan.LoanConstants.LoanActivityType;
import com.esoft.jdp2p.loan.model.Loan;
import com.esoft.jdp2p.repay.service.RepayService;

/**
 * Filename: InvestHome.java Description: Copyright: Copyright (c)2013 Company:
 * jdp2p
 *
 * @author: yinjunlu
 * @version: 1.0 Create at: 2014-1-11 下午4:26:10
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2014-1-11 yinjunlu 1.0 1.0 Version
 */
@Component
@Scope(ScopeType.VIEW)
public class InvestHome extends EntityHome<Invest> implements Serializable {

    @Resource
    private InvestService investService;

    @Resource
    private LoginUserInfo loginUserInfo;

    @Resource
    private RepayService repayService;

    @Resource
    private TransferService transferService;

    @Resource
    private UserService userService;

    @Resource
    private LoanCalculator loanCalculator;

    private double shouYi;

    public double getShouYi() {
        return shouYi;
    }

    public void setShouYi(double shouYi) {
        this.shouYi = shouYi;
    }

    @Override
    protected Invest createInstance() {
        Invest invest = new Invest();
        TransferApply ta = new TransferApply();
        Loan loan = new Loan();
        loan.setUser(new User());
        invest.setLoan(loan);
        invest.setTransferApply(ta);
        return invest;
    }

    /**
     * 购买债权转让
     */
    public String transfer() {
        try {
            if (loginUserInfo.getLoginUserId().equals(
                    getInstance().getTransferApply().getInvest().getUser()
                            .getId())) {
                FacesUtil.addErrorMessage("您不能购买自己的债权");
                return null;
            }
            transferService.transfer(getInstance().getTransferApply().getId(),
                    loginUserInfo.getLoginUserId(), getInstance()
                            .getInvestMoney());
            FacesUtil.addInfoMessage("购买成功！");
            return "pretty:user-transfer-purchased";
        } catch (InsufficientBalance e) {
            FacesUtil.addErrorMessage("余额不足");
        } catch (ExceedInvestTransferMoney e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
        return null;
    }

    public  boolean vaiMoney(double touzi,Loan loan) {
        double money=touzi;
        double couldMoney= 0;
        try {
            couldMoney = loanCalculator.calculateMoneyNeedRaised(loan.getId());
        } catch (NoMatchingObjectsException e) {
            FacesUtil.addErrorMessage("获取可投金额失败");
            return false;
        }
        double minMoney=loan.getCardinalNumber();
        double cardinalNumber=loan.getCardinalNumber();
        //投资金额不能高于可投金额
        if(money>couldMoney){
            FacesUtil.addErrorMessage("投资金额不能高于可投金额");
            return false;
        }
        if(couldMoney-money<minMoney){
            if(couldMoney-money==0){
                return true;
            }
            FacesUtil.addErrorMessage("您投资后，剩余金额不能少于起投金额");
            return false;
        }
        if((couldMoney-money-minMoney)%cardinalNumber!=0){
            FacesUtil.addErrorMessage("您投资后，剩余可投金额减去投资起点，还需要是递增金额的倍数");
            return false;
        }
        return true;
    }


    /**
     * 计算收益
     */
    public void calculateShouYi(double invesetMoney,Loan loan){
        Integer deadline=loan.getDeadline();
        String timeUnit=loan.getType().getRepayTimeUnit();
        double fee=0D;
        if(timeUnit.equals("day")){
            fee=invesetMoney*loan.getRate()/360*deadline;
        }else if(timeUnit.equals("month")){
            fee=invesetMoney*loan.getRate()/12*deadline;
        }
        this.setShouYi(fee);
    }

    /**
     * 投资减
     */
    public boolean calculateInvestMul(String loanId) {

        if(this.getInstance().getMoney()==null){
            this.getInstance().setMoney(0D);
        }
        double money=this.getInstance().getMoney();
        Loan loan=this.getBaseService().get(Loan.class, loanId);
        double cardinalNumber=loan.getCardinalNumber();
        double touzi=money-cardinalNumber;
        if(touzi<=0){
            this.setShouYi(0D);
            FacesUtil.addErrorMessage("投资金额不能再少了");
            return false;
        }
        boolean is=this.vaiMoney(touzi,loan);
        if(is){
            this.getInstance().setMoney(touzi);
            calculateShouYi(this.getInstance().getMoney(),loan);
            return true;
        }else{
            this.setShouYi(0D);
            return false;
        }
    }






    /**
     * 投资增加
     */
    public boolean calculateInvestPlus(String loanId) {

        if(this.getInstance().getMoney()==null){
            this.getInstance().setMoney(0D);
        }
        double money=this.getInstance().getMoney();
        Loan loan=this.getBaseService().get(Loan.class, loanId);
        double cardinalNumber=loan.getCardinalNumber();
        double touzi=money+cardinalNumber;
        if(touzi<=0){
            FacesUtil.addErrorMessage("投资金额不能再少了");
            this.setShouYi(0D);
            return false;
        }
        boolean is=this.vaiMoney(touzi,loan);
        if(is){
            this.getInstance().setMoney(touzi);
            calculateShouYi(this.getInstance().getMoney(), loan);
            return true;
        }else{
            this.setShouYi(0D);
            return false;
        }

    }

    /**
     * 投资校验
     */
    public boolean calculateInvest(String loanId) {
        if(this.getInstance().getMoney()==null){
            this.getInstance().setMoney(0D);
        }
        Loan loan=this.getBaseService().get(Loan.class, loanId);
        boolean is=this.vaiMoney(this.getInstance().getMoney(),loan);
        if(is){
            calculateShouYi(this.getInstance().getMoney(), loan);
            return true;
        }else{
            this.setShouYi(0D);
            return false;
        }
    }


    /**
     * 投资
     */
    public String save() {
        try {
            Loan loan = getBaseService().get(Loan.class,
                    getInstance().getLoan().getId());
            if (loan.getUser().getId().equals(loginUserInfo.getLoginUserId())) {
                FacesUtil.addInfoMessage("你不能投自己的项目！");
                return null;
            }
            if (LoanActivityType.DX.equals(loan.getLoanActivityType())) {
                if (null == loan.getInvestPassword()) {
                    FacesUtil.addInfoMessage("投资密码不能为空,请重新输入！");
                    return null;
                }
                if (!loan.getInvestPassword().equals(this.getInstance().getUserInvestPass())) {
                    FacesUtil.addInfoMessage("投资密码输入不正确,请重试！");
                    return null;
                }
            }
            this.getInstance().setUser(
                    new User(loginUserInfo.getLoginUserId()));
            this.getInstance().setIsAutoInvest(false);
            investService.create(getInstance());
        } catch (InsufficientBalance e) {
            FacesUtil.addErrorMessage("账户余额不足，请充值！");
            return null;
        } catch (ExceedMoneyNeedRaised e) {
            FacesUtil.addErrorMessage("投资金额不能大于尚未募集的金额！");
            return null;
        } catch (ExceedMaxAcceptableRate e) {
            FacesUtil.addErrorMessage("竞标利率不能大于借款者可接受的最高利率！");
            return null;
        } catch (ExceedDeadlineException e) {
            FacesUtil.addErrorMessage("红包已过期");
            return null;
        } catch (UnreachedMoneyLimitException e) {
            FacesUtil.addErrorMessage("投资金额未到达红包使用条件");
            return null;
        } catch (IllegalLoanStatusException e) {
            FacesUtil.addErrorMessage("当前借款不可投资");
            return null;
        }
        FacesUtil.addInfoMessage("投资成功！");

        if (FacesUtil.isMobileRequest()) {
            return "pretty:mobile_user_invests";
        }
        return "pretty:user_invest_bidding";
    }

    /**
     * 获取某个用户总的投资额
     *
     * @return
     */
    public double getSumInvest(String userId) {
        Object o = getBaseService().find(
                "select sum(im.money) from Invest im where im.user.id=?",
                userId).get(0);
        if (o == null) {
            return 0;
        }
        return (Double) o;
    }

    /**
     * 获取某个用户获得的总的投资额(投资成功的) 取状态投资已完成和债权转让成功 yinxunzhi
     *
     * @return
     */
    public double getSumInvestMoney(String userId) {
        Object o = getBaseService()
                .find("select sum(im.money) from Invest im where im.user.id=? and im.status in (?,?)",
                        new String[]{userId,
                                InvestConstants.InvestStatus.COMPLETE}).get(0);
        if (o == null) {
            return 0;
        }
        return (Double) o;
    }

    /**
     * 某个用户投资的数量
     *
     * @return
     */
    public long getInvestAmount(String userId) {
        String hql = "select count(*) from Invest invest where invest.user.id=?";
        return (Long) getBaseService().iterate(hql, userId).next();
    }

    /**
     * 获取某个用户所有成功的投资数量
     *
     * @return
     */
    public long getUserInvestCount(String userId) {
        return investService.getUserInvestCount(userId);
    }

}
