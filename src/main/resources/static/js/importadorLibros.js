/* */
function esperar(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

window.addEventListener("DOMContentLoaded", async function () {
    let max = 100; //Numero del maximo de libros q guardare
    let guardados = 0; //Libros q ya se me guardaron
    let pagina = 1; //Pagina de esta API empieza en 1 (lo intente en cero y me dio error)

    while (guardados < max) { //se repite hasta q llegue a 100
        //Hago la peticion a la API para la pag actual
        const res = await fetch(`https://gutendex.com/books/?languages=es&page=${pagina}`);
        const data = await res.json();
        const libros = data.results;
        //Bucle q recorre todos los libros hasta q llegue al max
        for (let i = 0; i < libros.length && guardados < max; i++) {
            const libro = libros[i];
            //Se rellenan los datos (titulo, autor...)
            const titulo = (libro.title || "Sin título");
            const autor = (libro.authors?.[0]?.name || "Desconocido").slice(0, 255);
            const portada = libro.formats?.["image/jpeg"] || "";
            const lectura = libro.formats?.["text/html"] || null;
            const genero = (libro.subjects?.[0] || "Desconocido");
            await esperar(1000);
            //Cojo la descripcion de otra API ya que en esta no hay asiq la cojo en googlebook
            //a parte lo relaciono con el tuitulo para q en la otra api sepa q titulo uso
            let descripcion = await enriquecerDescripcion(titulo) || "Sin descripción disponible";
            descripcion = descripcion.slice(0, 1000);

            //El libro con todos los datos
            const libroParaEnviar = {
                titulo,
                descripcion,
                portada,
                enlaceLectura: lectura,
                autor,
                genero,
                valoracionMedia: null
            };
            //Se pasa al controlador en /api/libros
            const respuesta = await fetch("/api/libros", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(libroParaEnviar)
            });
            //si se guarda aumenta el guardados
            if (respuesta.ok) {
                guardados++;
            }
        }
        //siguiente pagina
        pagina++;
    }
});

async function enriquecerDescripcion(titulo) {
    const consulta = encodeURIComponent(titulo);
    const url = `https://www.googleapis.com/books/v1/volumes?q=${consulta}&langRestrict=es&maxResults=1`;

    try {
        const res = await fetch(url); //petición API de Google Books
        const jsonRecibido = await res.json(); //esto viene en un formato JSON y lo traduzco a js
        const contenido = jsonRecibido.items; //lista de libros q google me devuelve
        if (!contenido || contenido.length === 0) return null; // Si no hay contenido, devuelvo null
        const descripcion = contenido[0].volumeInfo?.description || null; // Pillo la descripción del primer resultado si existe
        return descripcion && descripcion.length > 1000 // Si es muy larga, la recorto y añado "..."
            ? descripcion.slice(0, 997) + "..."
            : descripcion;

    } catch {
        // error de formato, conx... devuelvo null
        return null;
    }
}
