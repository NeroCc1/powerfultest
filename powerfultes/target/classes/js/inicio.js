var btnIniciar = document.getElementById("login-button");
btnIniciar.addEventListener("click", () => {
    axios.post("http://localhost:4567/validarUsuario", {
        correo: document.getElementById("correo").value,
        contraseña: document.getElementById("contraseña").value
    })
    .then(function (res) {
        if(res.data == 1){
            window.location.replace("/ProyectoSW/menum.html");
            alert("Alumno");
            
        }else if(res.data == 0){
            alert("NO");
        }else if(res.data == -1){
            alert("Usuario o contraseñas invalidos");
        }
    })
    .catch(function (error) {
        console.log(error)
    })
});