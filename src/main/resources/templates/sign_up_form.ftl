<html lang="en">

<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="/css/sign-up.css">
    <link rel="stylesheet" href="/css/components_styles/menu.css">
</head>

<body>
<#include "components/menu.ftl"/>
<div class="container">
    <h1>SIGN UP</h1>
    <#if error?? && error=='true'>
        <div class="error">Аккаунт с этим именем уже существует!</div>
    </#if>
    <form method="post">
        <input type="text" name="username" placeholder="username" required minlength="3" maxlength="20">
        <input type="password" name="password" placeholder="password" required minlength="6" maxlength="20">
        <input type="submit" value="SUBMIT">
    </form>

    <div class="signup-link">Already have an account? <a href="/auth/sign-in">SIGN IN</a></div>
</div>
</body>