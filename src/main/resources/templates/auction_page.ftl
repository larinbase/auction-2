<!DOCTYPE html>
<html>
<head>
    <title>Auction page</title>
    <link rel="stylesheet" href="/css/components_styles/menu.css">
    <link rel="stylesheet" href="/css/auction-page.css">
</head>
<body>
<#include "components/menu.ftl"/>
<div class="container">
    <div class="auction-info">
        <div>Auction ID: ${auction.id()}</div>
        <div>Auction Name: ${auction.name()}</div>
        <div>Status: ${auction.status()}</div>
        <div>Account ID: ${auction.account().id()}</div>
    </div>
    <table class="lot-table">
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
                <td class="actions"><a href="/lot/${lot.id()}">Перейти</a></td>
            </tr>
            </#if>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>
