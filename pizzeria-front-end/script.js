/* COSTANTI*/
const apiUrl = "http://localhost:8080/api/v1/pizzas";
const root = document.getElementById("root");

/* FUNZIONI */

// funzione che renderizza gli ingredienti della pizza
const renderIngredients = (ingredients) => {
  console.log(ingredients);
  let content;
  if (ingredients.length === 0) {
    content = "Nessun ingrediente presente.";
  } else {
    content = '<ul class="list-unstyled">';
    ingredients.forEach((ingr) => {
      content += `<li>${ingr.name}</li>`;
    });
    content += "</ul>";
  }
  return content;
};

// funzione che renderizza la card della pizza
const renderPizza = (element) => {
  console.log(element);
  return `<div class="card mb-5" style="width: 18rem;">
    <img src="${
      element.imageURL
    }" class="card-img-top" alt="Pizza Image" style="height: 300px">
    <div class="card-body">
      <h5 class="card-title">${element.name}</h5>
      <p class="card-text">${element.description}</p>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Prezzo: ${element.price} €</li>
    </ul>
    <div class="card-body">
        ${renderIngredients(element.ingredients)}
    </div>
  </div>`;
};

// funzione che renderizza la gallery di card
const renderPizzaList = (data) => {
  let content;
  console.log(data);
  if (data.length > 0) {
    // creo la gallery
    content = '<div class="row">';
    // itero sull'array di pizze
    data.forEach((element) => {
      content += '<div class="col-3">';
      // chiamo il metodo che restituisce la card della pizza
      content += renderPizza(element);
      content += "</div>";
    });
    content += "</div>";
  } else {
    content = '<div class="alert alert-info">The list is empty</div>';
  }
  // sostituisco il contenuto di root con il mio content
  root.innerHTML = content;
};

// funzione che chiama l'api e ottiene il json con l'array di pizze
const getPizzas = async () => {
  try {
    // ottengo la response dell'api
    const response = await axios.get(apiUrl);
    // passo i dati alla funzione che li renderizza
    renderPizzaList(response.data);
  } catch (error) {
    console.log(error);
  }
};

/* codice globale che viene eseguito al load dello script */
getPizzas();
