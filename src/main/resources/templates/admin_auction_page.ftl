<!DOCTYPE html>
<html>
<head>
    <title>Auction page</title>
    <link rel="stylesheet" href="/css/components_styles/menu.css">
    <link rel="stylesheet" href="/css/auction-page.css">
    <link rel="stylesheet" href="/css/popup_styles/create_lot_form.css">
</head>
<body>
<#include "components/menu.ftl"/>
<div class="container">
    <div class="auction-info">
        <div>Auction ID:
            <div id="auctionId">${auction.id()}</div>
        </div>
        <div>Auction Name: ${auction.name()}</div>
        <div>Status: ${auction.status()}</div>
        <div>Account ID: ${auction.account().id()}</div>
    </div>
    <table class="lot-table" id="auctionTable">
        <thead>
        <tr>
            <th>Lot ID</th>
            <th>Имя лота</th>
            <th>Описание</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <#list auction.lotList() as lot>
            <#if lot.status() == 'OPEN'>
            <tr>
                <td>${lot.id()}</td>
                <td>${lot.name()}</td>
                <td>${lot.description()}</td>
                <td class="actions"><a href="/lot/${lot.id()}">Перейти</a> <a href="/lot/${lot.id()}" id="delete-link">Закрыть лот</a></td>
            </tr>
            </#if>
        </#list>
        </tbody>
    </table>
    <button id="openModalBtn">Create Lot</button>
</div>
<#include "popup_views/create_lot_form.ftl"/>
<script src="/js/popup_lot_script.js"></script>
</body>
</html>
