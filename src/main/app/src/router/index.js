import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Recipes from '../views/Recipes.vue'
import Foods from "../views/Foods";
import Settings from "../views/Settings";
import Scan from "../views/Scan";
import ShoppingList from "@/views/ShoppingList";
import Recipe from "@/views/Recipe";

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  }, {
    path: '/recipes',
    name: 'Recipes',
    component: Recipes
  }, {
    path: '/foods',
    name: 'Foods',
    component: Foods
  }, {
    path: '/scan',
    name: 'Scan',
    component: Scan
  }, {
    path: '/shopping-list',
    name: 'ShoppingList',
    component: ShoppingList
  }, {
    path: '/recipes/:recipeId',
    name: 'Recipe',
    component: Recipe
  }, {
    path: '/settings',
    name: 'Settings',
    component: Settings
  }
];

const router = new VueRouter({
  routes
});

export default router
