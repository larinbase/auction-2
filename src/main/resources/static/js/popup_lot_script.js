// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("openModalBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
btn.onclick = function () {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

// Prevent form submission
document.getElementById("lotForm").addEventListener("submit", function (event) {
    event.preventDefault();
    // Get form data
    var formData = new FormData(this);
    formData.append("auctionId", document.getElementById("auctionId").innerText);

    // Send form data to the server via AJAX
    fetch("/api/v1/lot", {
        method: "POST",
        body: formData,
    })
        .then(response => {
            if (!(response.status === 201)) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            console.log("Server response:", data);
            modal.style.display = "none";
            addLotToTable(data);
            // Handle server response as needed
        })
        .catch(error => {
            console.error("Error:", error);
            // Handle errors
        });
});

function addLotToTable(lot){
    var newRow = document.createElement("tr");
    newRow.innerHTML = `
            <td>${lot.id}</td>
            <td>${lot.name}</td>
            <td>${lot.description}</td>
            <td><a href="/lot/${lot.id}" class="create-link">Перейти</a> <a href="/lot/${lot.id}" id="delete-link">Закрыть лот</a></td>
        `;

    document.getElementById("auctionTable").getElementsByTagName('tbody')[0].appendChild(newRow);

}


document.getElementById("delete-link").addEventListener("click", function (event) {
    event.preventDefault();
    var href = document.getElementById("delete-link").getAttribute("href");
    var id = href.split('/').pop();
    console.log(id)
    fetch("/api/v1/lot/" + id, {
        method: "DELETE",
    })
        .then(response => {
            if (!(response.status === 200)) {
                return response.json().then(error => {
                    throw new Error(error.message);
                });
            }
        })
        .then(() => {
            // deleteLotFromTable(id);
            document.getElementById("delete-link").parentNode.parentNode.parentNode.removeChild(document.getElementById("delete-link").parentNode.parentNode);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});

function deleteLotFromTable(id){
    // TODO: Правильный query selector
    var rowToDelete = document.querySelector('td:contains("' + id + '")').parentNode;
    rowToDelete.parentNode.removeChild(rowToDelete);
}