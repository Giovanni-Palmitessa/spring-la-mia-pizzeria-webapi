const apiUrl2 = "http://localhost:8080/api/v1/pizzas";
const ingredients = [
  {
    id: 1,
    name: "mozzarella",
  },
  {
    id: 2,
    name: "pomodoro",
  },
  {
    id: 5,
    name: "funghi",
  },
  {
    id: 6,
    name: "salsiccia",
  },
  {
    id: 8,
    name: "salame",
  },
  {
    id: 9,
    name: "bufala",
  },
  {
    id: 10,
    name: "prosciutto cotto",
  },
];

document
  .getElementById("createPizzaForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const description = document.getElementById("description").value;
    const price = document.getElementById("price").value;
    const imageURL = document.getElementById("imageURL").value;
    // Ottieni gli ingredienti selezionati (esempio: ingredient1, ingredient2)
    const ingredients = Array.from(
      document.querySelectorAll("input[type=checkbox]:checked")
    ).map((checkbox) => {
      return { id: checkbox.value };
    });

    try {
      let pizza = {
        name,
        description,
        price,
        imageURL,
        ingredients,
      };
      console.log(pizza);
      await axios.post(apiUrl2, pizza);

      // Dopo aver salvato la pizza, reindirizza alla pagina principale o esegui altre azioni desiderate
      window.location.href = "index.html";
    } catch (error) {
      console.error(error);
    }
  });

// Funzione per popolare dinamicamente le checkbox degli ingredienti
const populateIngredientsCheckbox = (ingredients) => {
  const ingredientsContainer = document.getElementById("ingredientsContainer");

  const checkboxesContent = renderIngredientCheckboxes(ingredients);
  ingredientsContainer.innerHTML = checkboxesContent;
};

// Funzione per renderizzare le checkbox degli ingredienti
const renderIngredientCheckboxes = (ingredients) => {
  let content = "";

  if (ingredients.length === 0) {
    content = "Nessun ingrediente presente.";
  } else {
    ingredients.forEach((ingredient) => {
      content += `
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="ingredient${ingredient.id}" value="${ingredient.id}">
                    <label class="form-check-label" for="ingredient${ingredient.id}">${ingredient.name}</label>
                </div>
            `;
    });
  }

  return content;
};

populateIngredientsCheckbox(ingredients);
