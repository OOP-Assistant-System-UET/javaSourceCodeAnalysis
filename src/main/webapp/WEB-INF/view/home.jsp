<%--
  Created by IntelliJ IDEA.
  User: jcia
  Date: 27/06/2018
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

    <title>Java source code analysis</title>
    <style>
        .header{
            position: relative;
            left: 0;
            bottom: 0;
            width: 100%;
            background-color: white;
            color: brown;
            text-align: left;
            height: 80px;
        }
        .header img{
            background-position: right top;
            padding-top: 0px;
            float: right;
            text-align: right;
            height: 80px;
            width:800px;
            border: 0px;
        }
        img{
            /* border:1px solid Tomato;*/
            height: 200px;
            width: 600px;
        }
        .file-field.big .file-path-wrapper {
            height: 3.2rem; }
        .file-field.big .file-path-wrapper .file-path {
            height: 3rem; }
        /*body {
        background-image: url("/images/java-dev-banner.jpg");
        background-repeat: no-repeat;

        width: 100%;
        height: 500px;
    }
*/
    </style>
</head>
<body>
<div class="header">
    <img src="/images/33.png" style="padding-right: 50px">
    <h2 style="padding-left: 110px;padding-top: 10px ">Java source code analysis</h2>

</div>
<center>
    <div style="background-image: url(/images/7HHe8xr.jpg);  background-repeat: no-repeat;height: 600px; width: 85%;text-indent:10px;">
        <img src="/images/17217074-render-of-gears-and-the-text-java.jpg" style="margin-top: 100px" alt="java">
        <%--<img src="/images/UML.jpg_w3imdxeiis" style="float:right;margin-right: 250px;margin-top: 100px" alt="images">--%>
        <h1 style="font-family: fantasy; font-size: 70px; color: white; padding-top: 10px;text-align: center">Select file java source:</h1>

        <input  style="margin-top: 20px;" type="file" name="file">
        </br>
        <button type="submit" action="/diagram" class="btn btn-info" style="margin-top: 10px;background-color: chocolate; width: 20%; height: 8%; font-size: large">Convert to UML diagram</button>

    </div>
</center>
<%--
    <img src="/images/java-dev-banner.jpg" style="margin-top: 0px; width: 100%; height: 500px">
--%>

</body>
</html>
