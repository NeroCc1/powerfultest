var btnRegistrar = document.getElementById("btnRegistrar");
btnRegistrar.addEventListener("click", () => {
    axios.post("http://localhost:4567/usuario", {
        usuario: document.getElementById("usuario").value,
        contraseña: document.getElementById("contraseña").value,
    })
        .then(function (res) {
            alert("Usuario:" + res.data.status);
        })
        .catch(function (error) {
            console.log(error)
        })
});
