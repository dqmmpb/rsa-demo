<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html class="no-js" lang="">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">

  <title>rsa</title>

  <link rel="apple-touch-icon" href="apple-touch-icon.png">
  <!-- Place favicon.ico in the root directory -->
  <link type="image/x-icon" rel="shortcut icon" href="favicon.ico">
  
  <style>
    .panel {
      background: #ddd;
      padding: 10px;
    }
    .title {
      font-weight: bold;
      font-size: 16px;
    }
    label {
      color: red;
    }
    .value {
      word-break: break-word;
    }
  </style>
 
</head>
<body>
 
  <div class="page-group">
  
   <div id="encryptJs_decryptJs" class="panel">
     <div class="title">js加密，js解密</div>
     <label>加密明文</label>
     <div class="value plainText"></div>
     <label>Js加密后</label>
     <div class="value afterEncrypt"></div>
     <label>Js解密后</label>
     <div class="value afterDecrypt"></div>
   </div>
   
   <div id="encryptJava_decryptJs" class="panel">
    <div class="title">java加密，js解密</div>
    <label>加密明文</label>
    <div class="value plainText"></div>
    <label>Js加密后</label>
    <div class="value afterEncrypt"></div>
    <label>Js解密后</label>
    <div class="value afterDecrypt"></div>
  </div>
  
  <div id="encryptJs_decryptJs2" class="panel">
    <div class="title">js加密，js解密</div>
    <label>加密明文</label>
    <div class="value plainText"></div>
    <label>Js加密后</label>
    <div class="value afterEncrypt"></div>
    <label>Js解密后</label>
    <div class="value afterDecrypt"></div>
  </div>
  
  </div>

  <script src="lib/modernizr/modernizr.js"></script>

  <script src="lib/jquery/dist/jquery.js"></script>

  <script src="lib/jsencrypt/bin/jsencrypt.js"></script>

  <script src="js/rsa-demo.js"></script>

</body>
</html>
