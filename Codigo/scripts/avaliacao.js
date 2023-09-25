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

// Avaliações cards
const urlParams = new URLSearchParams(window.location.search);
const idParam = urlParams.get('id');

fetch('../json/lugares.json')
.then(response => response.json())
.then(data => {
 
  const lugar = data.lugares.find(item => item.id === parseInt(idParam));
  
  lugar.avaliacoes.forEach(data => {
    let rate = colocarEstrelas(data.avaliacao);

    document.querySelector('#avaliacoes').innerHTML +=`
    <div id="card" class="max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow-2xl">
            <i class="fa-solid fa-quote-left"></i>
            <div class="flex flex-col justify-between gap-3">
                <h2 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 ">${data.usuario}</h2>
                <div id="rate" class="flex items-center mb-2">
                    <div id="stars" class="flex items-center text-yellow-300 mr-5">
                        ${rate}
                    </div>
                    <div class="bg-blue-100 text-blue-800 text-xs font-semibold mr-2 px-2.5 py-0.5 rounded">
                        ${data.avaliacao}</div>
                </div>
                <div class="card-body">
                    <p class="mb-3 mt-3 font-normal text-gray-700 ">${data.comentario}</p>
                </div>
            </div>
        </div>
    `;
  });
  
})
.catch(error => console.error('Erro:', error));