/*Funcion para confirmar la contraseña */
document.addEventListener("DOMContentLoaded", function () {
  confirmarClave();
});

function confirmarClave() {
  const form = document.querySelector("form");
  const clave = document.getElementById("clave");
  const confirmarClave = document.getElementById("confirmarClave");
  const error = document.getElementById("errorConfirmarClave");
  const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

  form.addEventListener("submit", function (e) {
    error.style.display = "none";
    error.textContent = "";

    if (clave.value !== confirmarClave.value) {
      e.preventDefault();
      error.textContent = "Las contraseñas no coinciden";
      error.style.display = "block";
      return;
    }

    if (!regex.test(clave.value)) {
      e.preventDefault();
      error.textContent = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número";
      error.style.display = "block";
      return;
    }
  });
}

/*funcion para filtrar por autor y genero */
document.addEventListener("DOMContentLoaded", function () {
  const selectAutor = document.getElementById("filtro-autor");
  const selectGenero = document.getElementById("filtro-genero");
  const tarjetasLibro = document.querySelectorAll(".tarjetaLibro, .filtro");
  const listaAutores = []; //creo dos arrays para guardar aut y genero UNICOS
  const listaGeneros = [];
  /*Recorre todas las tarjetas de libros.el autor desde data-autor*/
  for (let i = 0; i < tarjetasLibro.length; i++) {
    const tarjeta = tarjetasLibro[i];
    const autorTarjeta = tarjeta.dataset.autor;
    const generoTarjeta = tarjeta.dataset.genero;
    // Si el autor aún no está en la lista, lo añade al array y en el select.
    if (!listaAutores.includes(autorTarjeta)) {
      listaAutores.push(autorTarjeta);
      selectAutor.innerHTML += `<option value="${autorTarjeta}">${autorTarjeta}</option>`;
    }

    if (!listaGeneros.includes(generoTarjeta)) {
      listaGeneros.push(generoTarjeta);
      selectGenero.innerHTML += `<option value="${generoTarjeta}">${generoTarjeta}</option>`;
    }
  }
  // Cuando el usuario cambia el autor o el género, se llama a la función filtrarLibros().
  selectAutor.addEventListener("change", filtrarLibros);
  selectGenero.addEventListener("change", filtrarLibros);

  function filtrarLibros() {
    let autorSeleccionado = selectAutor.value;
    let generoSeleccionado = selectGenero.value;
    //Recorre cada tarjeta de libro y obtiene su autor y género.
    for (let i = 0; i < tarjetasLibro.length; i++) {
      let tarjeta = tarjetasLibro[i];
      let autor = tarjeta.dataset.autor;
      let genero = tarjeta.dataset.genero;
      // Si el autor y el género coinciden o si no se ha seleccionado ninguno muestra la tarjeta. 
      //sino se oculta la tarjeta
      if ((autorSeleccionado === "" || autor === autorSeleccionado) &&
        (generoSeleccionado === "" || genero === generoSeleccionado)) {
        tarjeta.style.display = "block";
      } else {
        tarjeta.style.display = "none";
      }
    }
  }
});

/*VALIDACION ESTRELLAS */
document.addEventListener("DOMContentLoaded", function () {
  const inputValor = document.getElementById("valor");
  const error = document.getElementById("errorValoracion");
  const formulario = document.querySelector("form");
  const estrellas = document.getElementsByClassName("estrella");

  // Clic en estrella → guardar valor y cambiar estilos
  for (let i = 0; i < estrellas.length; i++) {
    estrellas[i].onclick = function () {
      const valorSeleccionado = i + 1;
      inputValor.value = valorSeleccionado;
      // Pintar las estrellas
      for (let j = 0; j < estrellas.length; j++) {
        estrellas[j].style.color = j < valorSeleccionado ? "gold" : "";
      }
      error.style.display = "none";
    };
  }
  formulario.onsubmit = function (e) {
    if (inputValor.value === "") {
      e.preventDefault();
      error.textContent = "Selecciona una puntuación.";
      error.style.display = "block";
    }
  };
});

/* */