package com.alphabeta.controller;

import com.alphabeta.common.ErrorCode;
import com.alphabeta.domain.BaseResult;
import com.alphabeta.util.RSAUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rest/api")
public class RestController extends BaseController {

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsrxY7zwnU8/u9a5KClEjEHDiGyMXYBJUr+il5Pw9KsVqFuhydjV7EiMBTykQ4XuZdaBWqlA7KMOio9xsL8nuMZOSu3fsmuSvMjt6gX/7kd3TQO2Tbjs8sFokJ3LPYqrhWhHCxFe52USMiKEanKbSZch1uPF1+pQHfV4zo8Mu6FQIDAQAB";
    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKyvFjvPCdTz+71rkoKUSMQcOIbIxdgElSv6KXk/D0qxWoW6HJ2NXsSIwFPKRDhe5l1oFaqUDsow6Kj3Gwvye4xk5K7d+ya5K8yO3qBf/uR3dNA7ZNuOzywWiQncs9iquFaEcLEV7nZRIyIoRqcptJlyHW48XX6lAd9XjOjwy7oVAgMBAAECgYEAk2mb506kq//j5R3RolsHizI0Jwt5qSCwXyxc/z4PxcmE5yerievG/Kto056VgjGxIgfahxWBUqVR1/uqQRas1A2j5/de8Y+LcpNrEuwF8YgOWmK3EAty0pgHQ1ezYSaxJ2AMBF427UrzMpGrB77UEzGE07GxbbC/sK/u66h0A/kCQQD89Q3OmWV8Gxie8XkWHeiUhseo3kZ9AYy7tRpsEkTkkWZAK2znphdHl35yDk0Cqu4uCe3usz6TfRlWu+3WK5k3AkEArsLXtUUt1IVeM0Z0Oxz8AWMb4v1lJiS4BhotZs7fyZ6DnMd+LIdfqQCLl9j3hCzdxEqIqmcuL2uGy1OYdfz9EwJAF+lGM9hWOoQJMMUcsBWFrbyL1Q+l1B04Y2n8JGkZsA16f+ha9A7ENpVAc6Gcb/seZqWzoxO4f5KcuZEsK0mVwwJAIp4qCJhZib2ZeWK9Z3BIYyX0wjQbs0CWy26oC7NzFQc3XvkNf1iZlGqtPDkYXrBchaOWCttBhNcx7ljy3HxuzQJAQxcxqCOUmLJah+Mtjb+aJQ2L6Lg3mBA62WNGxXDzpX2pAcJVZ7bNcsBq41rOpQEtQ8bEyj/Nfxxsxy/F57xuCQ==";

    private static final String PUBLIC_KEY_PEM = "-----BEGIN PUBLIC KEY-----\n" +
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsrxY7zwnU8/u9a5KClEjEHDiG\n" +
        "yMXYBJUr+il5Pw9KsVqFuhydjV7EiMBTykQ4XuZdaBWqlA7KMOio9xsL8nuMZOSu\n" +
        "3fsmuSvMjt6gX/7kd3TQO2Tbjs8sFokJ3LPYqrhWhHCxFe52USMiKEanKbSZch1u\n" +
        "PF1+pQHfV4zo8Mu6FQIDAQAB\n" +
        "-----END PUBLIC KEY-----";
    private static final String PRIVATE_KEY_PEM = "-----BEGIN PRIVATE KEY-----\n" +
        "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKyvFjvPCdTz+71r\n" +
        "koKUSMQcOIbIxdgElSv6KXk/D0qxWoW6HJ2NXsSIwFPKRDhe5l1oFaqUDsow6Kj3\n" +
        "Gwvye4xk5K7d+ya5K8yO3qBf/uR3dNA7ZNuOzywWiQncs9iquFaEcLEV7nZRIyIo\n" +
        "RqcptJlyHW48XX6lAd9XjOjwy7oVAgMBAAECgYEAk2mb506kq//j5R3RolsHizI0\n" +
        "Jwt5qSCwXyxc/z4PxcmE5yerievG/Kto056VgjGxIgfahxWBUqVR1/uqQRas1A2j\n" +
        "5/de8Y+LcpNrEuwF8YgOWmK3EAty0pgHQ1ezYSaxJ2AMBF427UrzMpGrB77UEzGE\n" +
        "07GxbbC/sK/u66h0A/kCQQD89Q3OmWV8Gxie8XkWHeiUhseo3kZ9AYy7tRpsEkTk\n" +
        "kWZAK2znphdHl35yDk0Cqu4uCe3usz6TfRlWu+3WK5k3AkEArsLXtUUt1IVeM0Z0\n" +
        "Oxz8AWMb4v1lJiS4BhotZs7fyZ6DnMd+LIdfqQCLl9j3hCzdxEqIqmcuL2uGy1OY\n" +
        "dfz9EwJAF+lGM9hWOoQJMMUcsBWFrbyL1Q+l1B04Y2n8JGkZsA16f+ha9A7ENpVA\n" +
        "c6Gcb/seZqWzoxO4f5KcuZEsK0mVwwJAIp4qCJhZib2ZeWK9Z3BIYyX0wjQbs0CW\n" +
        "y26oC7NzFQc3XvkNf1iZlGqtPDkYXrBchaOWCttBhNcx7ljy3HxuzQJAQxcxqCOU\n" +
        "mLJah+Mtjb+aJQ2L6Lg3mBA62WNGxXDzpX2pAcJVZ7bNcsBq41rOpQEtQ8bEyj/N\n" +
        "fxxsxy/F57xuCQ==\n" +
        "-----END PRIVATE KEY-----";

    @RequestMapping(value = "/v1/init", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult init(HttpServletRequest request) {

        BaseResult result = new BaseResult();

        try {
            PublicKey publicKey = RSAUtil.getPublicRSAKey(PUBLIC_KEY);
            PrivateKey privateKey = RSAUtil.getPrivateRSAKey(PRIVATE_KEY);

            String privateKeyStr = RSAUtil.toBase64(privateKey);
            String publicKeyStr = RSAUtil.toBase64(publicKey);

            Map<String, String> rsaKeyPair = new HashMap<String, String>();

            rsaKeyPair.put("sPub", publicKeyStr);
            rsaKeyPair.put("sPriv", privateKeyStr);

            HttpSession session = request.getSession();

            session.setAttribute("sPub", publicKeyStr);
            session.setAttribute("sPriv", privateKeyStr);

            result.setResult(rsaKeyPair);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }


    @RequestMapping(value = "/v1/generate", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult generate(HttpServletRequest request) {

        BaseResult result = new BaseResult();

        try {
            KeyPair kp = RSAUtil.generateKeyPair();
            PrivateKey privateKey = kp.getPrivate();
            PublicKey publicKey = kp.getPublic();

            String privateKeyStr = RSAUtil.toBase64(privateKey);
            String publicKeyStr = RSAUtil.toBase64(publicKey);

            Map<String, String> rsaKeyPair = new HashMap<String, String>();

            rsaKeyPair.put("sPub", publicKeyStr);
            rsaKeyPair.put("sPriv", privateKeyStr);

            HttpSession session = request.getSession();

            session.setAttribute("sPub", publicKeyStr);
            session.setAttribute("sPriv", privateKeyStr);

            result.setResult(rsaKeyPair);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    @RequestMapping(value = "/v1/send", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult receiveMessage(HttpServletRequest request, @RequestParam(name = "message") String message, @RequestParam(name = "cPub") String cPub, @RequestParam(name = "cSign", required = false) String cSign) {

        BaseResult result = new BaseResult();

        HttpSession session = request.getSession();

        try {

            PublicKey clientPublicKey = RSAUtil.getPublicRSAKey(cPub);

            // 验签
            if(cSign != null) {
                boolean isPass = RSAUtil.verify(message, cSign, clientPublicKey);
                if(!isPass) {
                    result.setSuccess(false);
                    result.setErrorCode(ErrorCode.ERROR_SIGNATURE_NOT_PASS.getCodeString());
                    return result;
                }
            }

            String sPriv = (String) session.getAttribute("sPriv");
            PrivateKey privateKey = RSAUtil.getPrivateRSAKey(sPriv);
            String decryptedText = RSAUtil.decryptToString(message, privateKey);

            String messageText = "服务端处理成功，客户端发送过来的内容为：" + decryptedText;
            String messageResponse = RSAUtil.encryptToString(messageText, clientPublicKey);

            Map<String, String> messageResult = new HashMap<String, String>();

            messageResult.put("message", messageResponse);

            // 签名处理
            String sign = RSAUtil.sign(messageResponse, privateKey);

            messageResult.put("sign", sign);
            result.setResult(messageResult);

        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }


    /**
     * 返回加密后的结果
     *
     * @param request
     * @param message
     * @param key
     * @param isPub
     * @return
     */
    @RequestMapping(value = "/v1/encrypt", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult encrypt(HttpServletRequest request, @RequestParam(name = "message") String message, @RequestParam(name = "key") String key, @RequestParam(name = "isPub", defaultValue = "false") boolean isPub) {
        BaseResult result = new BaseResult();

        try {
            String encryptedText;
            if (isPub) {
                PublicKey publicKey = RSAUtil.getPublicRSAKey(key);
                encryptedText = RSAUtil.encryptToString(message, publicKey);
            } else {
                PrivateKey privateKey = RSAUtil.getPrivateRSAKey(key);
                encryptedText = RSAUtil.decryptToString(message, privateKey);
            }

            Map<String, String> messageResult = new HashMap<String, String>();

            messageResult.put("message", encryptedText);

            result.setResult(messageResult);

        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }


    /**
     * 返回解密后的结果
     *
     * @param request
     * @param message
     * @param key
     * @param isPub
     * @return
     */
    @RequestMapping(value = "/v1/decrypt", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult decrypt(HttpServletRequest request, @RequestParam(name = "message") String message, @RequestParam(name = "key") String key, @RequestParam(name = "isPub", defaultValue = "false") boolean isPub) {
        BaseResult result = new BaseResult();

        try {
            String decryptedText;
            if (isPub) {
                PublicKey publicKey = RSAUtil.getPublicRSAKey(key);
                decryptedText = RSAUtil.decryptToString(message, publicKey);
            } else {
                PrivateKey privateKey = RSAUtil.getPrivateRSAKey(key);
                decryptedText = RSAUtil.decryptToString(message, privateKey);
            }

            Map<String, String> messageResult = new HashMap<String, String>();

            messageResult.put("message", decryptedText);

            result.setResult(messageResult);

        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }
}
