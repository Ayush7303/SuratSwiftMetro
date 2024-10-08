const hamBurger = document.querySelector(".toggle-btn");

hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});


const currentPage = window.location.pathname;

document.querySelectorAll('.sidebar-link').forEach(link => {
    const linkHref = link.getAttribute('href');
    
    if (currentPage.includes(linkHref)) {
        link.classList.add('active');
    }
});