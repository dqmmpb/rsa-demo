'use strict';

var basePath = '/rsa';

var BASE64 = 'base64';
var UTF8 = 'utf8';

var NodeRSA = require('node-rsa');

/**
 * 去掉pem格式，提取body
 * @param pem
 * @param pemHeader
 * @returns {*}
 */
NodeRSA.prototype.pemtoraw = function (pem, pemHeader) {
  var pemBody = pem;
  if (pemBody.indexOf("-----BEGIN ") == -1)
    throw "can't find PEM header: " + pemHeader;

  if (pemHeader !== undefined) {
    pemBody = pemBody.replace("-----BEGIN " + pemHeader + "-----", "");
    pemBody = pemBody.replace("-----END " + pemHeader + "-----", "");
  } else {
    pemBody = pemBody.replace(/-----BEGIN [^-]+-----/, '');
    pemBody = pemBody.replace(/-----END [^-]+-----/, '');
  }
  // 去掉换行
  pemBody = pemBody.trim();
  pemBody = pemBody.replace(/[\r\n]*/g, "");
  return pemBody;
};

/**
 * 生成pem格式
 * @param pem
 * @param pemHeader
 * @returns {*}
 */
NodeRSA.prototype.rawtopem = function (raw, pemHeader) {
  var pemBody = raw;
  return "-----BEGIN " + pemHeader + "-----\r\n" +
    pemBody +
    "\r\n-----END " + pemHeader + "-----\r\n";
};

/**
 * 去掉pem格式，提取body
 * @param format
 * @returns {*}
 */
NodeRSA.prototype.getKey = function (format) {
  return this.pemtoraw(this.exportKey(format));
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
    // 必须使用{encryptionScheme: 'pkcs1'}才能保证Server端能解密成功，node-rsa默认的encryptionScheme为'pkcs1_oaep'
    var encryptKey = isPub ? new NodeRSA(key, 'pkcs8-public-pem', {encryptionScheme: 'pkcs1'}) : new NodeRSA(key, 'pkcs8-pem',  {encryptionScheme: 'pkcs1'});
    var encrypted;
    if (isPub) {
      encrypted = encryptKey.encrypt(text, BASE64);
    } else {
      encrypted = encryptKey.encryptPrivate(text, BASE64);
    }

    return encrypted;
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
    // 必须使用{encryptionScheme: 'pkcs1'}才能保证Server端能解密成功，node-rsa默认的encryptionScheme为'pkcs1_oaep'
    var decryptKey = isPub ? new NodeRSA(key, 'pkcs8-public-pem', {encryptionScheme: 'pkcs1'}) : new NodeRSA(key, 'pkcs8-pem', {encryptionScheme: 'pkcs1'});
    var decrypted;
    if (isPub) {
      decrypted = decryptKey.decryptPublic(text, UTF8);
    } else {
      decrypted = decryptKey.decrypt(text, UTF8);
    }

    return decrypted;
  }

  function setClientRSA(keyPair) {
    var prvKeyObj = keyPair.prvKeyObj;
    var pubKeyObj = keyPair.pubKeyObj;
    var privateKey = prvKeyObj.getKey('pkcs8');
    var publicKey = prvKeyObj.getKey('pkcs8-public');
    console.log(prvKeyObj);
    console.log(pubKeyObj);
    $('#clientRSA .privateKey').text(privateKey);
    $('#clientRSA .publicKey').text(publicKey);
  }

  function initClientRSA() {
    var keyPair = {
      prvKeyObj: new NodeRSA(CLIENT_PRIVATEKEY, 'pkcs8-pem', {encryptionScheme: 'pkcs1'}),
      pubKeyObj: new NodeRSA(CLIENT_PUBLICKEY, 'pkcs8-public-pem', {encryptionScheme: 'pkcs1'})
    };
    setClientRSA(keyPair);
  }

  function generateClientRSA() {
    var key = new NodeRSA({b: 1024});
    var keyPair = {
      prvKeyObj: new NodeRSA(key.exportKey('pkcs8'), 'pkcs8-pem', {encryptionScheme: 'pkcs1'}),
      pubKeyObj: new NodeRSA(key.exportKey('pkcs8-public'), 'pkcs8-public-pem', {encryptionScheme: 'pkcs1'})
    };
    setClientRSA(keyPair);
  }

  function setServerRSA(keyPair) {
    var prvKeyObj = keyPair.prvKeyObj;
    var pubKeyObj = keyPair.pubKeyObj;
    var privateKey = prvKeyObj.getKey('pkcs8');
    var publicKey = pubKeyObj.getKey('pkcs8-public');
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
            prvKeyObj: new NodeRSA(privateKey, 'pkcs8-pem', {encryptionScheme: 'pkcs1'}),
            pubKeyObj: new NodeRSA(publicKey, 'pkcs8-public-pem', {encryptionScheme: 'pkcs1'})
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
            prvKeyObj: new NodeRSA(privateKey, 'pkcs8-pem', {encryptionScheme: 'pkcs1'}),
            pubKeyObj: new NodeRSA(publicKey, 'pkcs8-public-pem', {encryptionScheme: 'pkcs1'})
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
            var CLIENT_PRIV_KEY = $('#clientRSA .privateKey').text();
            var decryptText = decrypt(message, CLIENT_PRIV_KEY);
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
      var decryptText = decrypt(message, sPriv);
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
