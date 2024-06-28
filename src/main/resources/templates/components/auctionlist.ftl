<div class="auction-list">
    <div id="own-auction-list">
        <div class="header">
            <h1>My auctions</h1>
            <button id="openModalBtn" class="btn">Create Auction</button>
        </div>
        <table id="auctionListTable">
            <thead>
            <tr>
                <th>Auction ID</th>
                <th>Name</th>
                <th>Owner</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list ownAuctionList as auction>
                <tr>
                    <td>${auction.id()}</td>
                    <td>${auction.name()}</td>
                    <td>${auction.account().username()}</td>
                    <td class="actions">
                        <a href="admin/auction/${auction.id()}">Перейти</a>
<#--                        <a href="auction/delete/${auction.id()}">Удалить</a>-->
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <div id="available-auction-list">
        <div class="header">
            <h1>Available auctions</h1>
            <div class="container">
                <input class="search-input" id="search-input" type="text" name="search" placeholder="Search auctions"/>
            </div>
        </div>
        <table id="auctionListTable">
            <thead>
            <tr>
                <th>Auction ID</th>
                <th>Name</th>
                <th>Owner</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#list auctionList as auction>
                <tr>
                    <td>${auction.id()}</td>
                    <td>${auction.name()}</td>
                    <td>${auction.account().username()}</td>
                    <td class="actions">
                        <a href="auction/${auction.id()}">Участвовать</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>