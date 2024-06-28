
function uploadPhoto() {
    let fileInput = document.getElementById("file-input");
    let file = fileInput.files[0];
    if (!file) {
        console.error("No file selected.");
        return;
    }
    // let uuid = crypto.randomUUID()
    let formData = new FormData();
    formData.append("file", file);

    fetch(`/api/v1/image/upload`, {
        method: "POST",
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to upload photo.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}