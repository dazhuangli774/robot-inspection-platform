package com.wust30405.ricp.user.controller;

import com.alipay.easysdk.factory.Factory;
import com.wust30405.ricp.user.service.IAlipayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/alipay")
public class AlipayController {

    private final IAlipayService alipayService;

    public AlipayController(IAlipayService alipayService) {
        this.alipayService = alipayService;
    }

    /**
     * 支付宝电脑网页支付
     * @param subject: 订单名称
     * @param total:   金额
     * @return java.lang.String
     */
    @PostMapping("/page")
    public String page(String subject, String total) {
        subject = "测试支付";
        total = "1000";

        return alipayService.page(subject, total);
    }

    /**
     * 支付宝手机网页支付
     * @param subject: 订单名称
     * @param total:   金额
     * @return java.lang.String
     */
    @PostMapping("/wap")
    public String wap(String subject, String total) {
        subject = "测试支付";
        total = "1000";

        return alipayService.wap(subject, total);
    }

    /**
     * @param request: 请求
     * @return java.lang.String
     * @description: 支付宝异步回调
     * @date: 2020/11/3
     */
    @PostMapping("/notify_url")
    public String notify_url(HttpServletRequest request) throws Exception {

        if (!request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            return "success";
        }
        System.out.println("=========支付宝异步回调========");

        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            params.put(name, request.getParameter(name));
            // System.out.println(name + " = " + request.getParameter(name));
        }

        // 支付宝验签
        if (Factory.Payment.Common().verifyNotify(params)) {
            // 验签通过
            System.out.println("交易名称: " + params.get("subject"));
            System.out.println("交易状态: " + params.get("trade_status"));
            System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
            System.out.println("商户订单号: " + params.get("out_trade_no"));
            System.out.println("交易金额: " + params.get("total_amount"));
            System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
            System.out.println("买家付款时间: " + params.get("gmt_payment"));
            System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
        }

        return "success";
    }

    /**
     * @param outTradeNo:   商家订单号
     * @param refundAmount: 退款金额(不能大于交易金额)
     * @return java.lang.String
     * @description: 支付宝退款
     * @date: 2020/11/3
     */
    @PostMapping("/refund")
    public String refund(String outTradeNo, String refundAmount) {
        return alipayService.refund(outTradeNo, refundAmount);
    }

}