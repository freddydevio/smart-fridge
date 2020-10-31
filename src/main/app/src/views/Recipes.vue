<template>
  <div class="recipes">
    <div class="container">
      <h1>Rezeptauswahl</h1>
      <h4>Basierend auf deinen vorhandenen Lebensmittel</h4>
      <div class="recipes-list" v-if="!loading">
        <Recipe v-for="recipe in recipeList" :recipe="recipe"/>
      </div>
    </div>
    <LoadingIndicator v-if="loading"/>
  </div>
</template>

<script>
import Recipe from "../components/Recipe";
import LoadingIndicator from "../components/LoadingIndicator";
import apiClient from "../apiClient";

export default {
  name: 'Recipes',
  components: {
    LoadingIndicator,
    Recipe,
  },
  async mounted() {
    await this.loadRecipes();
  },
  methods: {
    async loadFridgeItems() {
      return apiClient.get('/api/fridge-store')
          .then(response => {
            return response.data;
          })
    },
    async loadRecipes() {
      const fridgeItems = await this.loadFridgeItems();
      const fridgeItemNames = Object.keys(fridgeItems).map(val => fridgeItems[val].name);

      apiClient.post('/api/recipe-search', {
          ingredients: fridgeItemNames,
          apiKey: process.env.VUE_APP_API_KEY
        }).then(response => {
        this.recipeList = response.data.recipeList;
        this.loading = false;
      });
    }
  },
  data() {
    return {
      recipeList: [],
      loading: true
    }
  }
}
</script>

<style lang="less">
.recipes {
  .recipes-list {
    margin-top: 1rem;
  }
}
</style>