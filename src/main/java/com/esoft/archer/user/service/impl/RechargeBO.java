package com.esoft.archer.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.esoft.archer.user.model.UserBill;
import com.esoft.archer.user.service.UserService;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esoft.archer.risk.service.SystemBillService;
import com.esoft.archer.user.UserBillConstants.OperatorInfo;
import com.esoft.archer.user.UserConstants;
import com.esoft.archer.user.model.Recharge;
import com.esoft.archer.user.service.UserBillService;

@Service
public class RechargeBO {
	
	@Resource
	HibernateTemplate ht;

	@Resource
	private UserBillService ibs;

	@Resource
	private SystemBillService sbs;

	@Resource
	private UserService userService;
	
	/**
	 * 充值支付成功
	 * @param recharge
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void rechargeSuccess(Recharge recharge) {
		recharge.setStatus(UserConstants.RechargeStatus.SUCCESS);
		recharge.setSuccessTime(new Date());
		ht.merge(recharge);
		// 往InvestorBill中插入值并计算余额
		ibs.transferIntoBalance(recharge.getUser().getId(),
				recharge.getActualMoney(), OperatorInfo.RECHARGE_SUCCESS,
				"充值编号：" + recharge.getId());
		sbs.transferInto(
				recharge.getFee(),
				OperatorInfo.RECHARGE_SUCCESS,
				"充值手续费, 用户ID：" + recharge.getUser().getId() + "充值ID"
						+ recharge.getId());
		//充值成功 短信提醒
		List list=ht.find(
				"from UserBill ub where ub.user.id=? and ub.detail=?", recharge.getUser().getId(), "充值编号：" + recharge.getId());
		if(list.size()==1){
			UserBill ub= (UserBill) list.get(0);
			userService.sendSuccessRechargeByYtxSMS(recharge.getUser().getUsername(),recharge.getUser().getMobileNumber(),ub.getMoney(),ub.getBalance());
		}
	}
}
