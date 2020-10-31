<template>
  <div class="shopping-list">
    <div class="container">
      <h1>Einkaufsliste</h1>
      <div class="shopping-list" v-if="!loading">
        <div class="shopping-list-entry" v-for="shoppingListEntry in shoppingList">
          <img class="image" :src="shoppingListEntry.ingredient.image|ingredientImageUrl"/>
          <div class="content">
            <span class="title">{{ shoppingListEntry.ingredient.originalName }}</span>
            <span class="quantity">{{ shoppingListEntry.details.amount }} x {{ shoppingListEntry.details.unit }}</span>
          </div>
        </div>
      </div>
    </div>
    <LoadingIndicator v-if="loading"/>
  </div>
</template>

<script>
import LoadingIndicator from "@/components/LoadingIndicator";
import apiClient from "@/apiClient";

export default {
  name: 'ShoppingList',
  components: {LoadingIndicator},
  async mounted() {
    await this.loadShoppingListItems();
  },
  methods: {
    async loadShoppingListItems() {
      apiClient.get(`/api/shopping-list?apiKey=${process.env.VUE_APP_API_KEY}`).then(response => {
        this.shoppingList = response.data;
        this.loading = false;
      })
    },
  },
  filters: {
    ingredientImageUrl(image) {
      if (image) {
        return 'https://spoonacular.com/cdn/ingredients_100x100/' + image
      }

      return require('../assets/no-image.png')
    }
  },
  data() {
    return {
      loading: true,
      shoppingList: []
    }
  }
}
</script>

<style lang="less">
.shopping-list {
  margin-top: 1rem;

  .shopping-list-entry {
    display: flex;
    flex-direction: row;
    margin-bottom: 1rem;

    .image {
      width: 3rem;
      height: 3rem;
      object-fit: cover;
      margin-right: 1rem;
    }

    .content {
      display: flex;
      flex-direction: column;
    }
  }
}
</style>