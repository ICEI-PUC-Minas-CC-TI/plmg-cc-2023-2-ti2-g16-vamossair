// Menu
const btn = document.getElementById('btn-menu');
const menu = document.getElementById('menu');

btn.addEventListener("click", () => {
    menu.classList.toggle("hidden");
});


// Nivel
let level = 1;
let xp = 90;

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

aumentarNivel();


//Avaliacao
const avaliacaoForm = document.getElementById('avaliacao-form');

function mostrarFormAvaliacao(isFormVisible) {
    if (isFormVisible) {
        avaliacaoForm.style.display = 'block';
        isFormVisible = true;
    } else {
        avaliacaoForm.style.display = 'none';

        // Limpar inputs
        document.getElementById("comentario").value = '';
        var radioButtons = document.querySelectorAll('input[type="radio"]');
        for (var i = 0; i < radioButtons.length; i++) {
            radioButtons[i].checked = false;
        }

    }
}


// Cards
fetch('lugares.json')
    .then(response => response.json())
    .then(data => {
        const lugares = data.lugares;

        lugares.forEach(lugar => {
            const cardHTML = criarCard(lugar);
            document.querySelector('#cards').innerHTML += cardHTML;
        });
    })
    .catch(error => console.error('Erro ao carregar JSON:', error));


function criarCard(lugar) {
    let rate = colocarEstrelas(lugar.avaliacao);

    return `
    <div id="card" class="max-w-sm p-6 bg-white border border-gray-200 rounded-lg">
    <div class="flex flex-col justify-between gap-3">
        <h2 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 ">${lugar.nome}</h2>
        <div id="rate" class="flex items-center mb-2">
            <div id="stars" class="flex items-center text-yellow-300 mr-5">
            ${rate}
            </div>
            <div class="bg-blue-100 text-blue-800 text-xs font-semibold mr-2 px-2.5 py-0.5 rounded">${lugar.avaliacao}</div>
        </div>
        <div class="card-body">
            <p class="mb-3 mt-3 font-normal text-gray-700 ">${lugar.descricao}</p>
            <p class="mb-3 mt-3 font-normal text-gray-700 "><i class="fa-solid fa-location-dot"></i> ${lugar.endereco.rua}, ${lugar.endereco.numero} - ${lugar.endereco.bairro}, ${lugar.endereco.cidade} - ${lugar.endereco.estado}</p>
        </div>
        <div class="flex justify-between">
            <button id="btn-avaliar"
                class="items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                onclick="mostrarFormAvaliacao(true)"><i class="fa-regular fa-comment"></i> Avaliar
            </button>
            <div> 
                <button class="text-2xl font-bold py-2 px-4 rounded-full" onclick="favoritarLugar(${lugar.id})">
                <i class="fa-solid fa-bookmark" style="color: #969696;" id="favoritar-${lugar.id}"></i>
                </button>
            </div>
        </div>
    </div>
        `;
}


// Estrelas
function colocarEstrelas(rating) {
    let ratingInt = Math.trunc(rating);
    let strRate = '';
    for (let x = 0; x < ratingInt; x++) {
        strRate += '<i class="fa-solid fa-star" style="color: #ffcb0c;"></i>';
    }
    if (rating - ratingInt >= 0.5) {
        strRate += '<i class="fa-solid fa-star-half" style="color: #ffcb0c;"></i>';
    }
    return strRate;
}


// Favoritar
const lugaresFavoritos = [];

function favoritarLugar(id) {
    let index = encontrarLugar(id); 

    if (index === -1) {
        lugaresFavoritos.push(id);
    } else {
        lugaresFavoritos.splice(index, 1);
    }

    atualizarInterfaceFavoritos(index,id);
}

function encontrarLugar(id) {
    let resp = -1;
    for(let i =0; i < lugaresFavoritos.length; i++){
        if(lugaresFavoritos[i] === id){
          resp = i;
        }
    }
    return resp;
}

function atualizarInterfaceFavoritos(index, id) {
    let btn = document.getElementById(`favoritar-${id}`);
    if(index === -1){
        btn.style.color = "blue";
    }else{
        btn.style.color = " #969696";
    }
    
}