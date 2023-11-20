// Menu

const btn = document.getElementById('btn-menu');
const menu = document.getElementById('menu');

btn.addEventListener("click", () => {
    menu.classList.toggle("hidden");
});


// Nivel
/*let level;
let xp = 100;

function aumentarNivel() {
    level = Math.floor(xp / 100);
    xp = xp - (level * 100);
    updateProgressBar();
    updateLevelText();
}

function updateProgressBar() {
    const progressBar = document.getElementById("progress-bar");
    progressBar.style.width = xp + "%";
}

function updateLevelText() {
    const levelText = document.getElementById("level");
    levelText.textContent = level;
}

aumentarNivel();*/

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
