'use strict';

var basePath = '/rsa';

$(function() {

  // var CLIENT_PUBLICKEY = "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgHh0zxAV0gVgvuSBFH3EzysoLo+7HJVWtrouvi+SAoPOOi7Z4n0Bz1a1ce9ujP/M7qvYPR7gTdxG+XazZpByjCTUMQM8UcJ/2zSqGuC1btiTo8tM852Mx8OEOrydEj6xF2Tyj2qoYQCD7TDCLeoOoXvskn2DajVvxiBSjvLAM4rXAgMBAAE=";
  // var CLIENT_PRIVATEKEY = "MIICWwIBAAKBgHh0zxAV0gVgvuSBFH3EzysoLo+7HJVWtrouvi+SAoPOOi7Z4n0Bz1a1ce9ujP/M7qvYPR7gTdxG+XazZpByjCTUMQM8UcJ/2zSqGuC1btiTo8tM852Mx8OEOrydEj6xF2Tyj2qoYQCD7TDCLeoOoXvskn2DajVvxiBSjvLAM4rXAgMBAAECgYB3HNrcxxcFZmOdxd6jd4h83qEcVCSxsCvE9A/xLmr6N2XlimOixbVnwsjFwQYAtX+VwqC99YRIvI5WyJQ1ZnAZZttXcFDp2h7CAw2HW+7hufWAewS1LHelSMdTIr0t4VTKhlXQJM3sK/u1cX1yWe+6laieCpQ7jAd34LHa917gMQJBALza9UmPx1449ZDh5y39MlpwB1nA6UAm4Rw+k26gnex9QzlapjjdMpWj9Pv/Op/5JUk1bA0ENR+L7VHrT32TQTsCQQCjSGCm72qGPoihFt3bEd9zjOu+Jj6Twueqj2UdSGCE4cIdckVWeFI1lKdp7Ld6NzeLT0ZuPnl5dsXTJi/eWoMVAkAZfELVpn11DaK15oZYAm8lHTEgv5O6gISoVxl2OF6XZ2AHAWClE9SbtQB+cTbsF6ccdgUazJKOq0dfYq3beF+5AkEAg7GKktmi9ydUl2iUA27E5LOSJ8k8y3x10TDaguWvQz26jHlvxzBATgrZhCtySFZkrC2CRsSIwRi0sXqpPROPiQJAEFJg6eucpUml44w97uVwqgpWnY/MZiUywRghPj9g9Qsg2uE+81wW2phVU8AWDf+R0fRfjVu0TEulDzoIHLqc9A==";
  // var SERVER_PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsrxY7zwnU8/u9a5KClEjEHDiGyMXYBJUr+il5Pw9KsVqFuhydjV7EiMBTykQ4XuZdaBWqlA7KMOio9xsL8nuMZOSu3fsmuSvMjt6gX/7kd3TQO2Tbjs8sFokJ3LPYqrhWhHCxFe52USMiKEanKbSZch1uPF1+pQHfV4zo8Mu6FQIDAQAB";
  // var SERVER_PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKyvFjvPCdTz+71rkoKUSMQcOIbIxdgElSv6KXk/D0qxWoW6HJ2NXsSIwFPKRDhe5l1oFaqUDsow6Kj3Gwvye4xk5K7d+ya5K8yO3qBf/uR3dNA7ZNuOzywWiQncs9iquFaEcLEV7nZRIyIoRqcptJlyHW48XX6lAd9XjOjwy7oVAgMBAAECgYEAk2mb506kq//j5R3RolsHizI0Jwt5qSCwXyxc/z4PxcmE5yerievG/Kto056VgjGxIgfahxWBUqVR1/uqQRas1A2j5/de8Y+LcpNrEuwF8YgOWmK3EAty0pgHQ1ezYSaxJ2AMBF427UrzMpGrB77UEzGE07GxbbC/sK/u66h0A/kCQQD89Q3OmWV8Gxie8XkWHeiUhseo3kZ9AYy7tRpsEkTkkWZAK2znphdHl35yDk0Cqu4uCe3usz6TfRlWu+3WK5k3AkEArsLXtUUt1IVeM0Z0Oxz8AWMb4v1lJiS4BhotZs7fyZ6DnMd+LIdfqQCLl9j3hCzdxEqIqmcuL2uGy1OYdfz9EwJAF+lGM9hWOoQJMMUcsBWFrbyL1Q+l1B04Y2n8JGkZsA16f+ha9A7ENpVAc6Gcb/seZqWzoxO4f5KcuZEsK0mVwwJAIp4qCJhZib2ZeWK9Z3BIYyX0wjQbs0CWy26oC7NzFQc3XvkNf1iZlGqtPDkYXrBchaOWCttBhNcx7ljy3HxuzQJAQxcxqCOUmLJah+Mtjb+aJQ2L6Lg3mBA62WNGxXDzpX2pAcJVZ7bNcsBq41rOpQEtQ8bEyj/Nfxxsxy/F57xuCQ==";
  //
  // $('#clientRSA .publicKey').text(CLIENT_PUBLICKEY);
  // $('#clientRSA .privateKey').text(CLIENT_PRIVATEKEY);
  // $('#serverRSA .publicKey').text(SERVER_PUBLICKEY);
  // $('#serverRSA .privateKey').text(SERVER_PRIVATEKEY);

  /**
   * 加密
   * @param text 待加密的字符串
   * @param key 加密的key
   * @param isPub 是否使用publicKey加密，默认false
   * @returns {*}
   */
  function encrypt(text, key, isPub) {
    // Encrypt with key...
    var jsEncrypt = new JSEncrypt();
    if(isPub) {
      jsEncrypt.setPublicKey(key);
    } else {
      jsEncrypt.setPrivateKey(key);
    }
    var encrypted = jsEncrypt.encrypt(text);

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
    var jsDecrypt = new JSEncrypt();
    if(isPub) {
      jsDecrypt.setPublicKey(key);
    } else {
      jsDecrypt.setPrivateKey(key);
    }
    var decrypted = jsDecrypt.decrypt(text);

    return decrypted;
  }

  function generateClientRSA() {
    var rsa = new JSEncrypt();
    var publicKey = rsa.getPublicKeyB64();
    var privateKey = rsa.getPrivateKeyB64();
    $('#clientRSA .publicKey').text(publicKey);
    $('#clientRSA .privateKey').text(privateKey);
  }

  function generateServerRSA() {
    $.ajax({
      url: basePath + "/rest/api/v1/generate",
      type: "post",
      success: function(data) {
        if(data.success) {
          var result = data.result;
          var publicKey = result.sPub;
          var privateKey = result.sPriv;
          $('#serverRSA .publicKey').text(publicKey);
          $('#serverRSA .privateKey').text(privateKey);
        }
      }
    });
  }

  function sendMessage(message) {
    var cPub = $('#clientRSA .publicKey').text();
    if(cPub) {
      var params = {
        message: message,
        cPub: cPub,
      };
      $.ajax({
        url: basePath + "/rest/api/v1/send",
        type: "post",
        data: params,
        success: function(data) {
          if(data.success) {
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
    if(sPub) {
      var plainText = $('#textareaRequestText').val();
      var encryptText = encrypt(plainText, sPub, true);
      $('#messageEncrypt .plainText').text(encryptText);
    } else {
      alert('请先生成服务端的RSA Key');
    }
  }

  function decryptMessage() {
    var sPriv = $('#serverRSA .privateKey').text();
    if(sPriv) {
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

  $('#clientRSA .generate').click(function() {
    generateClientRSA();
    clear();
  });

  $('#serverRSA .generate').click(function() {
    generateServerRSA();
    clear();
  });

  $('#encodeMessage').click(function() {
    encryptMessage();
  });

  $('#decodeMessage').click(function() {
    decryptMessage();
  });

  $('#send').click(function() {
    var message = $('#messageEncrypt .plainText').text();
    if(message) {
      sendMessage(message);
    } else {
      alert('请先点击按钮，使用sPub生成密文');
    }
  });
});
