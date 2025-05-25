/*Validacion de q las contraseñas conciden */
const form = document.querySelector('form');
const clave = document.getElementById('clave');
const confirmarClave = document.getElementById('confirmarClave');
const errorDiv = document.getElementById('errorConfirmarClave');

form.addEventListener('submit', function(e) {
  const contraseña = clave.value;
  const confirmacion = confirmarClave.value;
  const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

  if (contraseña !== confirmacion) {
    e.preventDefault();
    errorDiv.style.display = 'block';
    errorDiv.textContent = 'Las contraseñas no coinciden';
    confirmarClave.classList.add('inputError');
  } else if (!regex.test(contraseña)) {
    e.preventDefault();
    errorDiv.style.display = 'block';
    errorDiv.textContent = 'Debe tener 8 caracteres, una mayúscula, una minúscula y un número';
    clave.classList.add('inputError');
  } else {
    errorDiv.style.display = 'none';
    clave.classList.remove('inputError');
    confirmarClave.classList.remove('inputError');
  }
});