<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Main</title>
    <link rel="stylesheet" href="/css/components_styles/menu.css">
    <link rel="stylesheet" href="/css/popup_styles/create_auction_form.css">
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<div>
    <#include "components/menu.ftl">
    <#include "components/auctionlist.ftl">
    <#include "popup_views/create_auction_form.ftl">
<#--    <div class="container">-->
<#--        <input class="search-input" id="search-input" type="text" name="search" placeholder="Search auctions"/>-->
<#--    </div>-->
    <script src="/js/search.js"></script>
    <script src="/js/popup_auction_script.js"></script>
</div>
</body>
</html>