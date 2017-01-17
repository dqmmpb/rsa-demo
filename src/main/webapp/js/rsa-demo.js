'use strict';

$(function() {

  var RSA_KEY = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzwYuXTPEtWV49OS/Cb5QIbX2LtZBHhCi9hiR2hrJEcWA4vUmC8GsMMOKw933VxAurjw1Llhj4QXpKZi9hfOlc6bn7GoyAZpVgl+JAzwQFuTOSJyRacgGDef0BY0zW/kQZjILI7ovqXwAcSaOGhFQgy6OWAkNDeKqGQFXeMwr9owIDAQAB';
  var PRI_KEY = 'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALPBi5dM8S1ZXj05L8JvlAhtfYu1kEeEKL2GJHaGskRxYDi9SYLwawww4rD3fdXEC6uPDUuWGPhBekpmL2F86VzpufsajIBmlWCX4kDPBAW5M5InJFpyAYN5/QFjTNb+RBmMgsjui+pfABxJo4aEVCDLo5YCQ0N4qoZAVd4zCv2jAgMBAAECgYEAjlxHMEFodFDluLkUoPl7FJ2aI05dALajCU42jIQqpNfhq64FjSTYsqP4tMydJPIJiApYLjemeN5qeoepGJ0ztuU/QM56Q59c6S4nBxDhc619dvt9jABsompCl6yrPYzi3FG1TmJa4ugBOln+KS/Pw/aSaEu4E1CUOYgYuIljT1kCQQDWo9fwnEpdH2WoW4E1ARwuV++SfsV7szJtSIBEKuhIzRDLMEB97yoEffWVYgncou+XYXdMn5Eb0dkRP4u07ZUvAkEA1mThgvhz0gyM08GxaXIT/+9JskCuCvvcDTVJVhVebeK/y0rZGBsvIMuPux+6wy4S2qqj+BwjSfP+71JLrPopzQJAFx2aGe2bDKBfAFyqc5zk/hC2Wl6QwhuwaJiQR8cfMQf0sQ1HRMjHC6jNFAN08HATwYfbo0LkC8zzxanET/3uPQJBAKYaPJmzElC/xm/dVi2C47nbU3aWJAGAhkl5alsWbTWngr7nO3Ewxn+bFr18ZL8JODRQFn+IlVKbhn02fkkC/FUCQDD7cPsWeab+iJlam4gooS/suDryNqWQFFsuZDAuseDzQKpa7Qvd/5TH5eiiWpUoKCySjQapO+JXHjIb1PQm4Rw=';

  // Encrypt with the public key...
  var encrypt = new JSEncrypt();
  encrypt.setPublicKey(RSA_KEY);
  // Decrypt with the private key...
  var decrypt = new JSEncrypt();
  decrypt.setPrivateKey(PRI_KEY);

  // js加密 js解密
  var plainText = 'Javascript中文';
  var encrypted = encrypt.encrypt(plainText);
  var decrypted = decrypt.decrypt(encrypted);
  console.log('加密明文：' + plainText);
  console.log('Js加密后：' + encrypted);
  console.log('Js解密后：' + decrypted);
  
  $('#encryptJs_decryptJs').find('.plainText').text(plainText);
  $('#encryptJs_decryptJs').find('.afterEncrypt').text(encrypted);
  $('#encryptJs_decryptJs').find('.afterDecrypt').text(decrypted);

  // java加密 js解密
  plainText = 'Java中文2';
  encrypted = 'Hj3MnvCmFw6o+ggqLadnoMca2AJCedicY5CSoF0Q1CPZn6EIidqjDuBD8++2IXEFD+RTWutvhiSSce8Ot09n7JifwWFc3g5hcorHDM/e9MsC0PMxG32AMyZnqMxX+1T0xXnlHI8lr+0q4rNtGZ1f0uaE/ZO3MDzqClmamQO5I/0=';
  decrypted = decrypt.decrypt(encrypted);
  console.log('加密明文：' + plainText);
  console.log('Java加密后：' + encrypted);
  console.log('Js解密后：' + decrypted);
  
  $('#encryptJava_decryptJs').find('.plainText').text(plainText);
  $('#encryptJava_decryptJs').find('.afterEncrypt').text(encrypted);
  $('#encryptJava_decryptJs').find('.afterDecrypt').text(decrypted);

/*  // js加密 js解密
  plainText = 'Javascript中文';
  encrypted = 'LpsvBoKDHlpRsnAsC5lqU198lr8G9t2P0mHI1DAY7oNk6mJ+qd8JYq2X3yyutZL7K2dERBJSATqmYaRgnQgWVlMjlOi+j/Ob5kcaqrPVXLFJpgtqLYgJ2Qq7ecw3LrPTAoHNqsReZ7ZzYkWeMkqQzaPuAAT9UmJI1KG7iyOBNFY=';
  decrypted = decrypt.decrypt(encrypted);
  console.log('加密明文  ：' + plainText);
  console.log('Js加密后  ：' + encrypted);
  console.log('Js解密后  ：' + decrypted);

  $('#encryptJs_decryptJs2').find('.plainText').text(plainText);
  $('#encryptJs_decryptJs2').find('.afterEncrypt').text(encrypted);
  $('#encryptJs_decryptJs2').find('.afterDecrypt').text(decrypted);
  */
/*  var rsa = new JSEncrypt();
  console.log(rsa);
  var privateKey = rsa.getPrivateKeyB64();
  console.log(privateKey);
  var publicKey = rsa.getPublicKeyB64();
  console.log(publicKey);*/

  function reEncrypt() {
    
    var NEW_RSA_KEY = $('#publicKey').text();
    var NEW_PRI_KEY = $('#privateKey').text();
    // Encrypt with the public key...
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(NEW_RSA_KEY);
    // Decrypt with the private key...
    var decrypt = new JSEncrypt();
    decrypt.setPrivateKey(NEW_PRI_KEY);

    // js加密 js解密
    var plainText = 'Javascript中文';
    var encrypted = encrypt.encrypt(plainText);
    var decrypted = decrypt.decrypt(encrypted);
    console.log('加密明文：' + plainText);
    console.log('Js加密后：' + encrypted);
    console.log('Js解密后：' + decrypted);
    
    $('#encryptJs_decryptJs').find('.plainText').text(plainText);
    $('#encryptJs_decryptJs').find('.afterEncrypt').text(encrypted);
    $('#encryptJs_decryptJs').find('.afterDecrypt').text(decrypted);
  }
  
  $('#generate').click(function() {
    var rsa = new JSEncrypt();
    var publicKey = rsa.getPublicKeyB64();
    var privateKey = rsa.getPrivateKeyB64();
    $('#publicKey').text(publicKey);
    $('#privateKey').text(privateKey);
    reEncrypt();
  });
});
