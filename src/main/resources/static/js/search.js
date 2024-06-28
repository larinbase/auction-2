let timer;

function searchAuction() {
    let input = document.getElementById("search-input").value.trim()
    let url = `api/v1/auction/${input}`;
    if (input === "") {
        url = "api/v1/auction";
    }
    fetch(url, {
        method: "GET"
    })
        .then(response => {
            if (!(response.status === 200)) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            console.log("Server response:", data);
            createTableView(data);
        })
        .catch(error => {
            console.error("Error:", error);
            // Handle errors
        });
}

function handleInput() {
    clearTimeout(timer);
    timer = setTimeout(() => {
        searchAuction()
    }, 1000)
}


function createTableView(data) {
    let tableBody = document.querySelector("#available-auction-list table tbody");
    tableBody.innerHTML = "";
    if (data.length === 0) {
        //     TODO: return all auctions
        return;
    }
    data.forEach(auction => {
        var newRow = document.createElement("tr");
        newRow.innerHTML = `
            <td>${auction.id}</td>
            <td>${auction.name}</td>
            <td>${auction.account.username}</td>
            <td><a href="auction/${auction.id}">Участвовать</a></td>
        `;
        tableBody.appendChild(newRow);
    })

}

document.getElementById("search-input").addEventListener("input", handleInput);