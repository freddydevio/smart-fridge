<template>
  <div class="recipe" v-on:click="openRecipeDetail">
    <div class="image" :style="{
            'background': 'url(' + recipe.imageUrl + ') center',
            'background-size': 'cover'
          }"></div>
    <div class="content">
      <span class="title">{{ recipe.title }}</span>
      <div class="details">
        <span class="missed-ingredients-count">{{ recipe.missedIngredientCount }} Lebensmittel fehlen</span>
      </div>
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
    </div>
  </div>
</template>

<script>
export default {
  name: 'Recipe',
  props: ['recipe'],
  methods: {
    openRecipeDetail() {
      this.$router.push(`/recipes/${this.recipe.recipeId}`);
    }
  }
}
</script>

<style lang="less" scoped>
.recipe {
  display: flex;
  flex-direction: column;
  border-radius: .5rem;
  overflow: hidden;
  background: white;
  margin: 2rem 0;
  min-height: 17rem;
  box-shadow: 0 7px 30px -10px rgba(150,170,180,0.5);

  .image {
    height: 8rem;
    width: 100%;
  }

  .content {
    padding: 1rem;
    .details {
      margin-bottom: .5rem;
      font-size: .7rem;
    }

    .title {
      font-weight: 600;
      font-size: .95rem;
      color: darken(#a2a2a2, 60);
      margin-bottom: .5rem;
    }

    .detail-icons {
      display: flex;
      flex-direction: column;

      border-top: 1px solid lighten(#a2a2a2, 20);
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
  }
}
</style>
