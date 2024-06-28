const toggleButton = document.getElementById('toggleSidebar');
const sidebar = document.getElementById('sidebar');
const currentLink = document.getElementById('currentLink');

toggleButton.addEventListener('click', function() {
    sidebar.classList.toggle('active');
});

function setCurrentLink(linkName) {
    currentLink.textContent = linkName;
}