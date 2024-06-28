<div>
    <div class="topbar">
        <button id="toggleSidebar" class="openButton">Open Sidebar</button>
        <#if balance?exists>
            <div id="currentLink" class="currentLink">Balance: ${balance}</div>
        </#if>
        <div id="profileImageContainer" class="profileImageContainer">
            <a href="/profile">
                <img src="/img/profile-image.png" alt="Profile Image" class="profileImage">
            </a>
        </div>
    </div>

    <div class="sidebar" id="sidebar">
        <#--        <div class="logo">-->

        <#--        </div>-->
        <ul class="menu">
            <li><a href="/main">Main</a></li>
            <li><a href="/profile">Profile</a></li>
            <li><a href="/welcome">Welcome</a></li>
            <#if account?has_content>
                <li><a href="/logout">Logout</a></li>
            </#if>
        </ul>

    </div>
    <script src="/js/sidebar.js"></script>
</div>