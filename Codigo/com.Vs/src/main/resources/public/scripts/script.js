// Menu

const btn = document.getElementById('btn-menu');
const menu = document.getElementById('menu');

btn.addEventListener("click", () => {
    menu.classList.toggle("hidden");
});

// carrossel

  // JavaScript para controlar o slide automático
  const slider = document.querySelector('.slider');
  let currentIndex = 0;

  function nextSlide() {
      currentIndex = (currentIndex + 1) % slider.children.length;
      slider.style.transform = `translateX(-${currentIndex * 100}%)`;
  }

  // Iniciar o slide automático
  setInterval(nextSlide, 3000); // Altera o slide a cada 3 segundos
