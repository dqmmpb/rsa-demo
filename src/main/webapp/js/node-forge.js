'use strict';

var basePath = '/rsa';

var pki = forge.pki;
var util = forge.util;
var rsa = forge.rsa;
var pem = forge.pem;
var md = forge.md;

/**
 * 将PKCS#1转换为PKCS#8
 * @param prvKeyObj
 * @returns {the|*}
 */
pki.privateKeyPKCS1ToPKCS8 = function (prvKeyObj) {
  // 生成pkcs#8格式的pem
  const rsaPrivateKey = pki.privateKeyToAsn1(prvKeyObj);
  const privateKeyInfo = pki.wrapRsaPrivateKey(rsaPrivateKey);
  const privateKeyPem = pki.privateKeyInfoToPem(privateKeyInfo);
  return pki.privateKeyFromPem(privateKeyPem);
};

/**
 * 提取pem中的body
 * @param Pem
 * @returns {the|*}
 */
pki.bodyFromPem = function (Pem) {
  return util.encode64(pem.decode(Pem)[0].body);
};

/**
 * 将body转换为pem格式
 * @param pem
 * @param pemHeader
 * @returns {*}
 */
pki.bodyToPem = function (body, pemHeader) {
  var pemBody = body;
  return "-----BEGIN " + pemHeader + "-----\r\n" +
    pemBody +
    "\r\n-----END " + pemHeader + "-----\r\n";
};

/**
 * 将私钥的body转换为pkcs#8格式的pem
 * @param raw
 * @returns {*}
 */
pki.privateKeyBodyToPem = function (body) {
  return pki.bodyToPem(body, "PRIVATE KEY");
};

/**
 * 将公钥的body转换为pem格式
 * @param raw
 * @returns {*}
 */
pki.publicKeyBodyToPem = function (body) {
  return pki.bodyToPem(body, "PUBLIC KEY");
};


$(function () {

  var CLIENT_PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPrkLZe1AxVnhckFoF/c5BbuW86/LQE5hSynrGq2Dho9SaGqEu8QpZfhqk+w6OQaM8cdiAbakty7sjRzJ47JlGzoxHlurYKfxvo1T/3N2gXFa4H0ZpZXlG+uetyTMl06ndFl9Ji9GvxVzWW2B/RRB5tsEEkdoET3AG4V5bh1VgrQIDAQAB";
  var CLIENT_PRIVATEKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI+uQtl7UDFWeFyQWgX9zkFu5bzr8tATmFLKesarYOGj1JoaoS7xCll+GqT7Do5Bozxx2IBtqS3LuyNHMnjsmUbOjEeW6tgp/G+jVP/c3aBcVrgfRmlleUb6563JMyXTqd0WX0mL0a/FXNZbYH9FEHm2wQSR2gRPcAbhXluHVWCtAgMBAAECgYBBfyukHk1xIDzf3UHcZ1WFiHsbwuc+KSCP5RNQy0DvuxIoaak+T8zq/MxCltuMx6kU3cTWzqaHZM7bBxKgAyLfamrTcFyh4rUrkMzcBEENFkRng7//Px7vzwcUocygA4KGh6eGkef6/33yhgF4wUofUWgW2qyDNQm24OitLti7oQJBAPjOijg1gl6ytRrwdqPq+S4t7Y3ZHFjiis+7yhgZMZoS2JFeVtaQbbPfXzQ4eMExGEH2/lbOuMrsrEkxRUUNGfUCQQCT1apsFYDZwIcGSQ6DStEj4X+ukw5h/q2VnfI8B2u4EqbBbpzZ5DL7lo4yDDH85xifmLl0P3SrhSDeOSa/9ODZAkEA155OZHXi3GRs1MLNXjK07VM6CnK7wT/aYjpw4j97H/XzHs+t29Zga8BJhjzmUS5Vwlzlf584wAspJ2j+id/XvQJARDWLYj8xqkaYhh/jIFS+1k1O+h9DvZciRCwR/fx2iQGiCxGcMTSHCWnXxeO2lLeTtt9ige5dSF4uYhoAdQTpUQJBANoP3JGufyUusSp7UiRX/kUg1K2rfkrzg5MLNpPuKljs3mI/xGopddBogFNomZCbssGpUM+VFvNwnhZQByx58O4=";
  var SERVER_PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsrxY7zwnU8/u9a5KClEjEHDiGyMXYBJUr+il5Pw9KsVqFuhydjV7EiMBTykQ4XuZdaBWqlA7KMOio9xsL8nuMZOSu3fsmuSvMjt6gX/7kd3TQO2Tbjs8sFokJ3LPYqrhWhHCxFe52USMiKEanKbSZch1uPF1+pQHfV4zo8Mu6FQIDAQAB";
  var SERVER_PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKyvFjvPCdTz+71rkoKUSMQcOIbIxdgElSv6KXk/D0qxWoW6HJ2NXsSIwFPKRDhe5l1oFaqUDsow6Kj3Gwvye4xk5K7d+ya5K8yO3qBf/uR3dNA7ZNuOzywWiQncs9iquFaEcLEV7nZRIyIoRqcptJlyHW48XX6lAd9XjOjwy7oVAgMBAAECgYEAk2mb506kq//j5R3RolsHizI0Jwt5qSCwXyxc/z4PxcmE5yerievG/Kto056VgjGxIgfahxWBUqVR1/uqQRas1A2j5/de8Y+LcpNrEuwF8YgOWmK3EAty0pgHQ1ezYSaxJ2AMBF427UrzMpGrB77UEzGE07GxbbC/sK/u66h0A/kCQQD89Q3OmWV8Gxie8XkWHeiUhseo3kZ9AYy7tRpsEkTkkWZAK2znphdHl35yDk0Cqu4uCe3usz6TfRlWu+3WK5k3AkEArsLXtUUt1IVeM0Z0Oxz8AWMb4v1lJiS4BhotZs7fyZ6DnMd+LIdfqQCLl9j3hCzdxEqIqmcuL2uGy1OYdfz9EwJAF+lGM9hWOoQJMMUcsBWFrbyL1Q+l1B04Y2n8JGkZsA16f+ha9A7ENpVAc6Gcb/seZqWzoxO4f5KcuZEsK0mVwwJAIp4qCJhZib2ZeWK9Z3BIYyX0wjQbs0CWy26oC7NzFQc3XvkNf1iZlGqtPDkYXrBchaOWCttBhNcx7ljy3HxuzQJAQxcxqCOUmLJah+Mtjb+aJQ2L6Lg3mBA62WNGxXDzpX2pAcJVZ7bNcsBq41rOpQEtQ8bEyj/Nfxxsxy/F57xuCQ==";

  /**
   * 加密
   * @param text 待加密的字符串
   * @param key 加密的key
   * @param isPub 是否使用publicKey加密，默认false
   * @returns {*}
   */
  function encrypt(text, key, isPub) {
    // Encrypt with key...
    var encryptKeyPem = isPub ? pki.publicKeyBodyToPem(key) : pki.privateKeyBodyToPem(key);
    var encryptKey = isPub ? pki.publicKeyFromPem(encryptKeyPem) : pki.privateKeyPKCS1ToPKCS8(pki.privateKeyFromPem(encryptKeyPem));
    var buffer = util.createBuffer(util.encodeUtf8(text));
    var binaryString = buffer.getBytes();
    if (isPub) {
      return util.encode64(encryptKey.encrypt(binaryString));
    } else {
      return util.encode64(encryptKey.encrypt(binaryString));
    }
  }

  /**
   * 解密
   * @param text 待解密的字符串
   * @param key 解密的key
   * @param isPub 是否使用publicKey解密，默认false
   * @returns {*}
   */
  function decrypt(text, key, isPub) {
    // Decrypt with key...
    var decryptKeyPem = isPub ? pki.publicKeyBodyToPem(key) : pki.privateKeyBodyToPem(key);
    var decryptKey = isPub ? pki.publicKeyFromPem(decryptKeyPem) : pki.privateKeyPKCS1ToPKCS8(pki.privateKeyFromPem(decryptKeyPem));
    var binaryString = util.decode64(text);
    if (isPub) {
      return util.decodeUtf8(decryptKey.decrypt(binaryString));
    } else {
      return util.decodeUtf8(decryptKey.decrypt(binaryString));
    }
  }

  /**
   * 签名
   * @param md md摘要
   * @param key 加密的key
   * @returns {*}
   */
  function sign(key, md, scheme) {
    // Sign with key...
    var signKeyPem = pki.privateKeyBodyToPem(key);
    var signKey = pki.privateKeyPKCS1ToPKCS8(pki.privateKeyFromPem(signKeyPem));
    var buffer = util.createBuffer(util.encodeUtf8(md));
    var binaryString = buffer.getBytes();
    return util.encode64(signKey.sign(binaryString, scheme));
  }

  /**
   * 验签
   * @param md md摘要
   * @param key 加密的key
   * @returns {*}
   */
  function verify(key, digest, signature, scheme) {
    // Verify with key...
    var verifyKeyPem = pki.publicKeyBodyToPem(key);
    var verifyKey = pki.publicKeyFromPem(verifyKeyPem);
    return verifyKey.verify(digest, signature, scheme);
  }

  function setClientRSA(keyPair) {
    var prvKeyObj = keyPair.prvKeyObj;
    var pubKeyObj = keyPair.pubKeyObj;
    // 生成pem
    const privateKeyPem = pki.privateKeyToPem(prvKeyObj);
    // 从pem中解析body
    const privateKey = pki.bodyFromPem(privateKeyPem);
    // 生成pem
    const publicKeyPem = pki.publicKeyToPem(pubKeyObj);
    // 从pem中解析body
    const publicKey = pki.bodyFromPem(publicKeyPem);
    console.log(prvKeyObj);
    console.log(pubKeyObj);
    $('#clientRSA .privateKey').text(privateKey);
    $('#clientRSA .publicKey').text(publicKey);
  }

  function initClientRSA() {
    var keyPair = {
      prvKeyObj: pki.privateKeyPKCS1ToPKCS8(pki.privateKeyFromPem(pki.privateKeyBodyToPem(CLIENT_PRIVATEKEY))),
      pubKeyObj: pki.publicKeyFromPem(pki.publicKeyBodyToPem(CLIENT_PUBLICKEY)),
    };

    setClientRSA(keyPair);
  }

  function generateClientRSA() {
    var keypair = rsa.generateKeyPair({bits: 1024, e: 0x10001});
    var keyPair = {
      prvKeyObj: pki.privateKeyPKCS1ToPKCS8(keypair.privateKey),
      pubKeyObj: keypair.privateKey,
    };

    setClientRSA(keyPair);
  }

  function setServerRSA(keyPair) {
    var prvKeyObj = keyPair.prvKeyObj;
    var pubKeyObj = keyPair.pubKeyObj;
    // 生成pem
    const privateKeyPem = pki.privateKeyToPem(prvKeyObj);
    // 从pem中解析body
    const privateKey = pki.bodyFromPem(privateKeyPem);
    // 生成pem
    const publicKeyPem = pki.publicKeyToPem(pubKeyObj);
    // 从pem中解析body
    const publicKey = pki.bodyFromPem(publicKeyPem);
    console.log(prvKeyObj);
    console.log(pubKeyObj);
    $('#serverRSA .privateKey').text(privateKey);
    $('#serverRSA .publicKey').text(publicKey);
  }

  function initServerRSA() {
    $.ajax({
      url: basePath + "/rest/api/v1/init",
      type: "post",
      success: function (data) {
        if (data.success) {
          var result = data.result;
          var privateKey = result.sPriv;
          var publicKey = result.sPub;
          var keyPair = {
            prvKeyObj: pki.privateKeyPKCS1ToPKCS8(pki.privateKeyFromPem(pki.privateKeyBodyToPem(privateKey))),
            pubKeyObj: pki.publicKeyFromPem(pki.publicKeyBodyToPem(publicKey)),
          };
          setServerRSA(keyPair);
        }
      }
    });
  }

  function generateServerRSA() {
    $.ajax({
      url: basePath + "/rest/api/v1/generate",
      type: "post",
      success: function (data) {
        if (data.success) {
          var result = data.result;
          var privateKey = result.sPriv;
          var publicKey = result.sPub;
          var keyPair = {
            prvKeyObj: pki.privateKeyPKCS1ToPKCS8(pki.privateKeyFromPem(pki.privateKeyBodyToPem(privateKey))),
            pubKeyObj: pki.publicKeyFromPem(pki.publicKeyBodyToPem(publicKey)),
          };
          setServerRSA(keyPair);
        }
      }
    });
  }

  function sendMessage(message) {
    var cPub = $('#clientRSA .publicKey').text();
    if (cPub) {
      var params = {
        message: message,
        cPub: cPub,
      };
      $.ajax({
        url: basePath + "/rest/api/v1/send",
        type: "post",
        data: params,
        success: function (data) {
          if (data.success) {
            var result = data.result;
            var message = result.message;
            var sign = result.sign;

            // 验签
            var mdMessage = md.sha1.create();
            mdMessage.update(util.encodeUtf8(message));
            // 摘要
            var digest = mdMessage.digest().getBytes();
            // 公钥
            var SERVER_PUB_KEY = $('#serverRSA .publicKey').text();
            var isPass = verify(SERVER_PUB_KEY, digest, util.decode64(sign));
            $('#signResponseText').text(isPass);

            // var SERVER_PRIV_KEY = $('#serverRSA .privateKey').text();
            // var prvKeyObj = pki.privateKeyPKCS1ToPKCS8(pki.privateKeyFromPem(pki.privateKeyBodyToPem(SERVER_PRIV_KEY)))
            // var signature = prvKeyObj.sign(md);
            // console.log(util.encode64(signature));

            var CLIENT_PRIV_KEY = $('#clientRSA .privateKey').text();
            var decryptText = decrypt(message, CLIENT_PRIV_KEY, false);
            $('#textareaResponseText').val(decryptText);

          }
        },
      });
    } else {
      alert('请先生成客户端的RSA Key');
    }
  }

  function encryptMessage() {
    var sPub = $('#serverRSA .publicKey').text();
    if (sPub) {
      var plainText = $('#textareaRequestText').val();
      var encryptText = encrypt(plainText, sPub, true);
      $('#messageEncrypt .plainText').text(encryptText);
    } else {
      alert('请先生成服务端的RSA Key');
    }
  }

  function decryptMessage() {
    var sPriv = $('#serverRSA .privateKey').text();
    if (sPriv) {
      var message = $('#messageEncrypt .plainText').text();
      var decryptText = decrypt(message, sPriv, false);
      $('#messageDecrypt .plainText').text(decryptText);
    } else {
      alert('请先生成服务端的RSA Key');
    }
  }

  function clear() {
    $('#textareaRequestText').val('');
    $('#textareaResponseText').val('');
    $('#messageEncrypt .plainText').text('');
    $('#messageDecrypt .plainText').text('');
  }

  $('#clientRSA .generate').click(function () {
    generateClientRSA();
    clear();
  });

  $('#serverRSA .generate').click(function () {
    generateServerRSA();
    clear();
  });

  $('#encodeMessage').click(function () {
    encryptMessage();
  });

  $('#decodeMessage').click(function () {
    decryptMessage();
  });

  $('#send').click(function () {
    var message = $('#messageEncrypt .plainText').text();
    if (message) {
      sendMessage(message);
    } else {
      alert('请先点击按钮，使用sPub生成密文');
    }
  });

  initClientRSA();
  initServerRSA();
});
