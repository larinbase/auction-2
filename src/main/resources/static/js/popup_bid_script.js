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
document.getElementById("bidForm").addEventListener("submit", function (event) {
    event.preventDefault();
    // Get form data
    var formData = new FormData(this);
    formData.append("lotId", document.getElementById("lotId").innerText);
    // Send form data to the server via AJAX
    fetch("/api/v1/bid", {
        method: "POST",
        body: formData,
    })
        .then(response => {
            if (!(response.status === 201)) {
                return response.json().then(error => {
                    throw new Error(error.message);
                });
            }
            return response.json();
        })
        .then(data => {
            console.log("Server response:", data);
            modal.style.display = "none";
            // addBidToTable(data);
            location.reload();
            // Handle server response as needed
        })
        .catch(error => {
            console.error("Error:", error);
            alert(error);
        });
});

function addBidToTable(bid){
    var newRow = document.createElement("tr");
    newRow.innerHTML = `
            <td>${bid.id}</td>
            <td>${bid.amount}</td>
        `;

    document.getElementById("bidTable").getElementsByTagName('tbody')[0].appendChild(newRow);

}


