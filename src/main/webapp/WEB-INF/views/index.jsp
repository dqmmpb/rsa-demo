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
      <li class="nav-item active">
        <a class="nav-link" href="<%=path%>/web/v1/index">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=path%>/web/v1/jsencrypt">jsencrypt</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=path%>/web/v1/jsrsasign">jsrsasign</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=path%>/web/v1/node-rsa">node-rsa</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=path%>/web/v1/node-forge">node-forge</a>
      </li>
    </ul>
  </div>
</nav>
<div class="container-fluid">
  <div class="row">
    <div class="col-sm">
      分别采用了4个前端的rsa插件，实现rsa密钥的生成，参考如上链接。
    </div>

    <div>
      <img src="<%=path%>/images/rsa-table.png"/>
    </div>
  </div>
</div>


<script src="https://cdn.bootcss.com/modernizr/2.8.3/modernizr.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>
