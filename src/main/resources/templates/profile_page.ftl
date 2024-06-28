<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
    <link rel="stylesheet" href="/css/components_styles/menu.css">
    <link rel="stylesheet" href="/css/profile.css">
</head>
<body>
<#include "components/menu.ftl">
<div class="container">
    <div class="base-container">
        <div class="profile-section">
            <div id="photo-preview" class="photo-preview">
                <img id="user-photo" src="/api/v1/image" alt="Uploaded Photo">
                <#--            /img/${avatar}-->
            </div>
            <div class="profile-info">
                <h1>${username}</h1>
                <form class="upload-form">
                    <input type="file" id="file-input"/>
                    <button type="button" onclick="uploadPhoto()">Upload Photo</button>
                </form>
            </div>
        </div>

        <div class="top-up-section">
            <h2>Введите сумму и выберите валюту:</h2>
            <form name="topUpForm" id="top-up-form">
                <input type="text" name="amount" id="amount" placeholder="Сумма">
                <select name="currency" id="currency">
                    <option value="USD">Доллар США (USD)</option>
                    <option value="EUR">Евро (EUR)</option>
                    <option value="RUB">Рубль (RUB)</option>
                </select>
                <input type="submit" value="Пополнить">
            </form>
        </div>
    </div>


    <div class="rewards-container">
        <div class="rewards-section">
            <h2>Награды пользователя</h2>
            <div id="rewards-list" class="rewards-list">
                <#list rewards as reward>
                <div class="reward-item">
                    <div class="reward-details">
                        <h3>${reward.lot().name()}</h3>
                        <p>${reward.lot().description()}</p>
                        <p>Ставка: ${reward.bid().amount()}</p>
                    </div>
                </div>
                </#list>
            </div>
        </div>
    </div>
</div>


<script src="/js/storage.js"></script>
<script src="/js/top_up_script.js"></script>
</body>
</html>