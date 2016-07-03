package org.nelect.timestamper.internal.sms;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * Created by Michael on 2016/6/28.
 */
@Component
public class MessageSenderImpl implements MessageSender {

    private static final String ENDPOINT = "http://112.124.17.46:7001";

    private static final Map<String, String> ERR_MESSAGES = ImmutableMap.<String, String> builder()
        .put("", "内部处理失败")
        .put("1001", "用户名密码错误")
        .put("1002", "通道配置移除或没有配置通道")
        .put("1003", "IP验证错误")
        .put("1004", "签名不规范")
        .put("1005", "次数超限")
        .put("1006", "用户余额不足")
        .put("1007", "扣费异常")
        .put("1008", "数据接收失败，余额不足")
        .put("1009", "扣费失败或内容错误")
        .put("1010", "短信内容为空，或编码不正确引起")
        .put("1011", "有效号码为空")
        .put("1012", "接口参数异常，请检查参数名称")
        .put("1013", "发送内容超过500个字")
        .put("1014", "发送号码超过10000个号码")
        .put("1015", "用户名密码错误或余额不足，接口屏蔽")
        .put("1016", "定时参数不正确。格式为：yyyyMMddHHmmss")
        .build();

    @Autowired
    private RestOperations restOperations;

    @Override
    public void batchSend(String message, String mobile, String... otherMobiles) {

        String mobiles = mobile;

        if (otherMobiles.length > 0) {
            mobiles += "," + join(otherMobiles, ",");
        }

        String response = restOperations.postForObject(ENDPOINT + "/sms_token",
            buildForm(message, mobiles), String.class);

        String errorCode = parseResponse(response);
        if (errorCode != null) {
            String errorMessage = ERR_MESSAGES.get(errorCode);
            if (errorMessage == null) errorMessage = ERR_MESSAGES.get("");
            throw new MessageException(errorMessage);
        }
    }

    @Override
    @Async
    public void batchSendAsync(String message, String mobile, String... otherMobiles) {
        batchSend(message, mobile, otherMobiles);
    }

    private MultiValueMap<String, String> buildForm(String message, String mobiles) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.set("ddtkey", "zhihuixinyuan");
        form.set("secretkey", DigestUtils.md5Hex("zhihuixinyuan2016"));
        form.set("mobile", mobiles);
        form.set("content", "【存证通】" + message);
        return form;
    }

    private String parseResponse(String response) {
        String[] responseElements = response.split(",\\s*", 2);
        String[] statusElements = responseElements[0].split(":\\s*", 2);
        String statusName = statusElements[0];
        String statusCode = "";
        if (statusElements.length > 1) {
            statusCode = statusElements[1];
        }
        if ("ok".equalsIgnoreCase(statusName)) {
            return null;
        }
        return statusCode;
    }
}
