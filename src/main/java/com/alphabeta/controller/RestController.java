package com.alphabeta.controller;

import com.alibaba.fastjson.JSONObject;
import com.alphabeta.domain.BaseResult;
import com.alphabeta.util.RSAUtil;
import org.bouncycastle.util.encoders.Base64;
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

    @RequestMapping(value = "/v1/generate", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult generate(HttpServletRequest request) {

        BaseResult result = new BaseResult();

        try {
            KeyPair kp = RSAUtil.generateKeyPair();
            PrivateKey privateKey = kp.getPrivate();
            byte[] prk = privateKey.getEncoded();
            String privateKeyStr = new String(Base64.encode(prk));

            PublicKey publicKey = kp.getPublic();
            byte[] pbk = publicKey.getEncoded();
            String publicKeyStr = new String(Base64.encode(pbk));

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
    public BaseResult receiveMessage(HttpServletRequest request, @RequestParam(name = "message") String message, @RequestParam(name = "cPub") String cPub) {

        BaseResult result = new BaseResult();

        HttpSession session = request.getSession();

        try {
            String sPriv = (String)session.getAttribute("sPriv");
            PrivateKey privateKey = RSAUtil.getPrivateRSAKey(sPriv);
            String decryptedText = RSAUtil.decryptToString(message, privateKey);

            String messageText = "服务端处理成功，客户端发送过来的内容为：" + decryptedText;
            PublicKey publicKey = RSAUtil.getPublicRSAKey(cPub);
            String messageResponse = RSAUtil.encryptToString(messageText, publicKey);

            Map<String, String> messageResult = new HashMap<String, String>();

            messageResult.put("message", messageResponse);

            result.setResult(messageResult);

        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

}
