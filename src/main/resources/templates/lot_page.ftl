<!DOCTYPE html>
<html>
<head>
    <title>Auction page</title>
    <link rel="stylesheet" href="/css/components_styles/menu.css">
    <link rel="stylesheet" href="/css/lot-page.css">
    <link rel="stylesheet" href="/css/popup_styles/create_bid_form.css">
</head>
<body>
<#include "components/menu.ftl"/>
<div class="container">
    <div class="lot-info">
        <div>ID лота: <div id="lotId">${lot.id()}</div> </div>
        <div>Имя лота: ${lot.name()}</div>
        <div>Описание: ${lot.description()}</div>
        <div>Статус: ${lot.status()}</div>
    </div>
    <table class="bid-table" id="bidTable">
        <thead>
        <tr>
            <th>ID ставки</th>
            <th>Имя</th>
            <th>Размер ставки</th>
        </tr>
        </thead>
        <tbody>
        <#list lot.bidList() as bid>
            <tr>
                <td>${bid.id()}</td>
                <td>${bid.account().username()}</td>
                <td>${bid.amount()}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <button id="openModalBtn">Create Bid</button>
</div>
<#include "popup_views/create_bid_form.ftl"/>
<script src="/js/popup_bid_script.js"></script>
</body>
</html>