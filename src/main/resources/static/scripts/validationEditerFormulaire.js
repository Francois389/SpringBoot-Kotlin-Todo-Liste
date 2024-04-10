document.addEventListener('DOMContentLoaded', function () {
    const titre = document.getElementById('titre');
    const echeance = document.getElementById('echeance');
    const formulaire = document.getElementById('formulaire');

    function estTitreValide() {
        return titre.value !== "";
    }

    function estDateValide() {
        if (echeance.value === "") {
            return true;
        }
        let dateSaisie = new Date(echeance.value);
        let dateActuelle = new Date();

        return dateActuelle < dateSaisie;
    }

    titre.addEventListener("blur", function () {
        if (!estTitreValide()) {
            titre.classList.add('is-invalid');
        } else {
            titre.classList.remove('is-invalid');
        }
    });

    echeance.addEventListener("change", function () {
        if (!estDateValide()) {
            echeance.classList.add('is-invalid');
        } else {
            echeance.classList.remove('is-invalid');
        }
    });

    formulaire.addEventListener("submit", function (event) {
        if (!estTitreValide()) {
            titre.classList.add('is-invalid');
            event.preventDefault();
        }

        if (!estDateValide()) {
            echeance.classList.add('is-invalid');
            event.preventDefault();
        }
    });
});
