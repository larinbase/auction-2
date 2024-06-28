<!DOCTYPE html>
<html>
<head>
    <title>Admin Page - User Management</title>
    <link rel="stylesheet" href="/css/popup_styles/create_user_form.css">
</head>
<body>
<h1>User Management</h1>

<table border="1" id="accountTable">
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <#list accountList as account>
        <tr>
            <td>${account.id()}</td>
            <td>${account.username()}</td>
            <td>
                <a href="#" class="delete-link">Edit</a>
                <a href="#" class="delete-link" onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
                <a href="#" class="give-communityRoleType">Give communityRoleType</a>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<#include "popup_views/create_user_form.ftl"/>
<script src="/js/popup_script.js"></script>
<script src="/js/admin_action_script.js"></script>

<#--<a href="/admin/addUser">Add User</a>-->
</body>
</html>
