var app = new Vue({
  el: '#app',
  mounted() {
    this.loadFridgeItems();
  },
  methods: {
      switchMenuEntry(menuItem) {
        this.currentMenuItem = menuItem;

        switch(menuItem) {
            case "Fridge":
                this.loadFridgeItems();
                break;
            case "Logs":
                this.loadLogs();
                break;
        }
      },
      loadLogs() {
        axios.get('/api/recipe-search-log/')
            .then(response => {
               this.searchLogs = response.data;
            });
      },
      loadFridgeItems() {
        axios.get('/api/fridge-store/')
            .then(response => {
               this.fridgeItems = response.data;
            });
      },
      deleteFridgeEntry(fridgeItemId) {
        axios.delete(`/api/fridge-store/${fridgeItemId}`)
          .then(response => {
            console.log(response);
          })
          .then(() => this.loadFridgeItems());
      },
      onFridgeItemSubmit(e) {
        e.preventDefault();

        axios.post('/api/fridge-store/', this.fridgeItem)
            .then(response => {
               this.fridgeItem = {
                   name: "",
                   expireDate: ""
               };
            })
            .then(() => this.loadFridgeItems());
      },
      async loadRecipes() {
        const fridgeItems = await axios.get('/api/fridge-store/')
          .then(response => {
             return response.data;
          });

        axios.post('/api/recipe-search/', {
            "ingredients": fridgeItems.map(fridgeItem => fridgeItem.name),
            "apiKey":"e4d45d7a61bc40a58531bba3234cefc0"
        })
        .then(response => {
           const recipeList = response.data.recipeList;
           console.log(recipeList);
           this.recipeList = recipeList;
        })
        console.log(fridgeItems);
      }
  },
  data: {
    fridgeItem: {
        name: "",
        expireDate: ""
    },
    recipeList: [],
    fridgeItems: [],
    searchLogs: [],
    menuItems: ["Fridge", "Recipes", "Logs"],
    currentMenuItem: "Fridge",
    message: 'Hello Vue!'
  }
});