<template>
    <div class="foods">
        <div class="container">
            <h1>Deine Lebensmittel</h1>
            <div class="fridge-items">
                <div class="fridge-item" v-for="fridgeItem in fridgeItems">
                    <div class="row">
                        <div class="col">
                            <div class="image" :style="{
                                'background': 'url(' + fridgeItem.imageUrl + ') center',
                                'background-size': 'cover'
                              }"></div>
                        </div>
                        <div class="col">
                            <div class="headline">{{fridgeItem.name}}</div>
                            <div class="sub-headline">{{fridgeItem.count}} x {{fridgeItem.quantity}}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import apiClient from "../apiClient";

    export default {
        name: 'Foods',
        mounted() {
            this.loadFridgeItems();
        },
        methods: {
            loadFridgeItems() {
                apiClient.get('/api/fridge-store')
                    .then(response => {
                        this.fridgeItems = response.data;
                    })
            }
        },
        data() {
            return {
                fridgeItems: []
            }
        }
    }
</script>

<style lang="less">
    .foods {
        .fridge-items {
            margin-top: 1rem;

            .fridge-item {
                margin-bottom: 1rem;

                .row {
                    display: flex;
                    flex-direction: row;

                    .col {
                        display: flex;
                        flex-direction: column;
                        width: 50%;
                    }
                }

                .image {
                    width: 90%;
                    height: 8rem;
                    border-radius: .25rem;
                }
            }
        }
    }
</style>