<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html class="no-js" lang="">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <title>RSA Demo</title>

  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css">

  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    label {
      color: red;
    }

    .value {
      word-break: break-all;
    }

    textarea.form-control {
      resize: none;
    }

    .navbar {
      margin-bottom: 20px;
    }

    .card {
      margin-bottom: 20px;
    }
  </style>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">RSA Demo</a>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="<%=path%>/web/v1/index">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="<%=path%>/web/v1/jsencrypt">jsencrypt</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=path%>/web/v1/jsrsasign">jsrsasign</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=path%>/web/v1/node-rsa">node-rsa</a>
      </li>
    </ul>
  </div>
</nav>
<div class="container-fluid">
  <div class="row">
    <div class="col-sm">
      <div id="clientRSA" class="card">
        <div class="card-body">
          <h5 class="card-title">Client
            <button class="btn btn-primary generate" type="button">生成Client密钥</button>
          </h5>
          <div class="card-text">
            <label>Client公钥（简称cPub）</label>
            <div class="value publicKey"></div>
            <label>Client私钥（简称cPriv）</label>
            <div class="value privateKey"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-sm">
      <div id="serverRSA" class="card">
        <div class="card-body">
          <h5 class="card-title">Server
            <button class="btn btn-primary generate" type="button">生成Server密钥</button>
          </h5>
          <div class="card-text">
            <label>Server公钥（简称sPub）</label>
            <div class="value publicKey"></div>
            <label>Server私钥（简称sPriv）</label>
            <div class="value privateKey"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Client使用Server的公钥加密，发送给Server</h5>
          <div class="card-text">
            <form>
              <div class="form-group">
                <label for="textareaRequestText">请输入需要加密的文字</label>
                <textarea class="form-control" id="textareaRequestText" rows="3"></textarea>
              </div>
              <button id="encodeMessage" type="button" class="btn btn-primary">使用sPub生成密文</button>
              <button id="send" type="button" class="btn btn-primary">发送</button>
              <button id="decodeMessage" type="button" class="btn btn-primary">使用sPriv解密密文</button>
            </form>
          </div>
        </div>
      </div>
      <div id="messageEncrypt" class="card">
        <div class="card-body">
          <h5 class="card-title">使用sPub生成密文</h5>
          <div class="card-text">
            <div class="value plainText"></div>
          </div>
        </div>
      </div>
      <div id="messageDecrypt" class="card">
        <div class="card-body">
          <h5 class="card-title">客户端使用sPriv解密结果</h5>
          <div class="card-text">
            <div class="value plainText"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-sm">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Server使用Server的私钥解密，解密后，使用Client的公钥加密结果，再返回给Client</h5>
          <div class="card-text">
            <form>
              <div class="form-group">
                <label for="textareaResponseText">服务端返回的结果，Client使用cPriv解密</label>
                <textarea class="form-control" readonly id="textareaResponseText" rows="3"></textarea>
              </div>
            </form>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>


<script src="https://cdn.bootcss.com/modernizr/2.8.3/modernizr.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jsrsasign/8.0.5/jsrsasign-all-min.js"></script>
<script src="<%=path%>/js/jsrsasign.js"></script>

</body>
</html>
