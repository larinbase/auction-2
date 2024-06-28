<html lang="en">

<head>
    <title>Sign in</title>
    <link rel="stylesheet" href="/css/sign-in.css">
    <link rel="stylesheet" href="/css/components_styles/menu.css">
</head>

<body>
<#include "components/menu.ftl"/>
<div class="container">
    <h1>SIGN IN</h1>
    <#if error?? && error=='true'>
        <div class="error">Неверное имя или пароль </div>
    </#if>
    <form method="post">
<#--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
        <input type="text" name="username" placeholder="username" required>
        <input type="password" name="password" placeholder="password" required>
        <input type="submit" value="SUBMIT">
    </form>

    <div class="signup-link">Don't have an account? <a href="/auth/sign-up">SIGN UP</a></div>

</div>
</body>