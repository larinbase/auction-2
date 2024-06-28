var modal = document.getElementById("top-up-form");

document.getElementById("top-up-form").addEventListener("submit", function (event) {
    event.preventDefault();
    let amount = document.getElementById("amount").value;
    let currency = document.getElementById("currency").value;
    var formData = new FormData(this);
    formData.append("amount", amount);
    formData.append("currency", currency);

    console.log(amount, currency);

    fetch("/api/v1/account/update-balance", {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({amount: amount, currency: currency}),
    })
        .then(response => {
            if (!(response.status === 200)) {
                return response.json().then(errorData => {
                    throw new Error(errorData.map(error => error.message).join("\n"));
                });
            }
            return response.json();
        })
        .then(data => {
            console.log("Server response:", data);
            // addBidToTable(data);
        })
        .catch(error => {
            console.error("Error:", error);
            alert(error)
        });
});


// function updateBalance(bid){
//     var newRow = document.createElement("tr");
//     newRow.innerHTML = `
//             <td>${bid.id}</td>
//             <td>${bid.amount}</td>
//         `;
//
//     document.getElementById("bidTable").getElementsByTagName('tbody')[0].appendChild(newRow);
//
// }


