document.addEventListener('DOMContentLoaded', function() {
    // Add event listener to all elements with class "delete-link"
    document.querySelectorAll('.delete-link').forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault(); // Prevent default link behavior

            var id = this.parentNode.parentNode.querySelector('td:first-child').innerText;


            // Send DELETE request to server
            fetch(`api/v1/account/${id}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (!response.ok) {
                        return response.json().then(error => {
                            throw new Error(error.message);
                        });
                    }
                })
                .then(() => {
                    // Handle success, e.g., remove the deleted user from the UI
                    this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode); // Remove the row from the table
                })
                .catch(error => {
                    console.error('Error:', error);
                    // Handle errors
                });
        });
    });

    document.querySelectorAll('.give-role').forEach(function(link) {
        link.addEventListener('click', function (event) {
            event.preventDefault(); // Prevent default link behavior

            var id = this.parentNode.parentNode.querySelector('td:first-child').innerText;
            // Send UPDATE ROLE request to server
            fetch(`api/v1/account/${id}/update-role`, {
                method: 'PATCH',
                contentType: 'application/json',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    role: 'ADMIN'
                })
            })
                .then(response => {
                    if (!response.ok) {
                        return response.json().then(error => {
                            throw new Error(error.message);
                        });
                    }
                })
                .then(data => {
                    console.log('User updated:', data);

                })
                .catch(error => {
                    console.error('Error:', error);
                    // Handle errors
                });
        });
    });

});

