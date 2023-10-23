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