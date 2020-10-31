<template>
  <div class="recipe">
    <div class="container">
      <div class="recipe" v-if="!loading">
        <h1 class="title">{{ recipe.title }}</h1>
        <div class="recipe-image" :style="{
            'background': 'url(' + recipe.imageUrl + ') center',
            'background-size': 'cover'
          }"></div>
        <div class="detail-icons">
          <div class="icon" v-if="recipe.readyInMinutes">
            <img src="../assets/clock.png">
            <span class="text">{{ recipe.readyInMinutes }} min.</span>
          </div>
          <div class="icon" v-if="recipe.glutenFree">
            <img src="../assets/gluten.png">
            <span class="text">Gluten free</span>
          </div>
          <div class="icon" v-if="recipe.dairyFree">
            <img src="../assets/dairy.png">
            <span class="text">Dairy free</span>
          </div>
          <div class="icon" v-if="recipe.vegetarian">
            <img src="../assets/vegetarian.png">
            <span class="text">Vegetarian</span>
          </div>
          <div class="icon" v-if="recipe.vegan">
            <img src="../assets/herb.png">
            <span class="text">Vegan</span>
          </div>
        </div>
        <div class="ingredients">
          <h3>Lebensmittel</h3>
          <div class="ingredient" v-for="ingredient in recipe.extendedIngredients">
            <img class="image" :src="ingredient|ingredientImageUrl">
            <div class="details">
              <span class="name">{{ ingredient.originalName }}</span>
              <span class="quantity">{{ ingredient.amount }} x {{ ingredient.unit }}</span>
            </div>
          </div>
        </div>
        <div class="instruction-steps">
          <h3>Arbeitsschritte</h3>
          <div class="instruction-step" v-for="instructionStep in recipe.analyzedInstructions[0].steps">
            <span class="text">{{ instructionStep.number }}. {{ instructionStep.step }}</span>
          </div>
        </div>
      </div>
    </div>
    <LoadingIndicator v-if="loading"/>
  </div>
</template>

<script>
import LoadingIndicator from "../components/LoadingIndicator";
import apiClient from "../apiClient";

export default {
  name: 'Recipe',
  components: {
    LoadingIndicator,
  },
  async mounted() {
    await this.loadRecipe();
  },
  methods: {
    async loadRecipe() {
      const recipeId = this.$route.params.recipeId
      apiClient.post(`/api/recipe-search/${recipeId}`, {
        apiKey: process.env.VUE_APP_API_KEY
      }).then(response => {
        this.recipe = response.data.recipeList.shift();
        this.loading = false;
      });
    },
    addToShoppingList(ingredientId, amount, unit) {
      apiClient.post('/api/shopping-list', {
        ingredientId,
        amount,
        unit
      }).then(response => {
        console.log(response);
        if(response.data.success) {

        }
      });
    }
  },
  filters: {
    ingredientImageUrl(ingredient) {
      if (ingredient.image) {
        return 'https://spoonacular.com/cdn/ingredients_100x100/' + ingredient.image
      }

      return require('../assets/no-image.png')
    }
  },
  data() {
    return {
      recipe: null,
      loading: true
    }
  }
}
</script>

<style lang="less">
.recipe {
  .container {
    .recipe {
      .recipe-image {
        height: 10rem;
        border-radius: .5rem;
      }

      display: flex;
      flex-direction: column;

      .title {
        font-weight: 600;
        font-size: 1.2rem;
        color: darken(#a2a2a2, 60);
        margin: 1rem 0;
      }

      .detail-icons {
        display: flex;
        flex-direction: column;
        padding-top: 1rem;

        .icon {
          display: flex;
          flex-direction: row;
          margin-bottom: .5rem;
          align-items: center;

          img {
            height: 25px;
            width: 25px;
          }

          .text {
            font-size: .7rem;
            margin-left: .5rem;
          }
        }
      }

      .instruction-steps {
        h3 {
          margin-bottom: .5rem;
        }

        .instruction-step {
          margin-bottom: .5rem;
          font-size: .95rem;
        }
      }

      .ingredients {
        margin: 1rem 0;

        h3 {
          margin-bottom: .5rem;
        }

        .ingredient {
          display: flex;
          flex-direction: row;
          margin-bottom: .5rem;
          align-items: center;

          .image {
            width: 2.5rem;
            height: 2.5rem;
            margin-right: 1rem;
            object-fit: cover;
          }

          .details {
            display: flex;
            flex-direction: column;
            width: 70%;
          }

          .add-to-shopping-list {
            width: 1.25rem;
            height: 1.25rem;
          }
        }
      }
    }
  }
}
</style>