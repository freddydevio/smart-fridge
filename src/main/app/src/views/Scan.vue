<template>
    <div class="scan">
        <div class="container">
            <h1>Lebensmittel hinzufügen</h1>
            <h4>Scanne deine Lebensmittel</h4>
            <div class="content">
                <div v-if="loading">
                    <StreamBarcodeReader
                            @decode="onDecode"
                    ></StreamBarcodeReader>
                </div>
                <div class="product" v-if="!loading">
                    <div class="content">
                        <span class="name">Produkt: {{product.product_name}}</span><br/>
                        <span class="quantity">Menge: {{product.quantity}}</span>
                        <div class="form-group">
                            <label for="quantityInput">Anzahl</label>
                            <input type="number" min="0" id="quantityInput" v-model="quantity">
                        </div>
                        <div class="form-group">
                            <label for="expireDateInput">Haltbarkeitsdatum</label>
                            <input type="date" min="0" id="expireDateInput" v-model="expireDate">
                        </div>
                        <button v-on:click="onAddProduct">Produkt hinzufügen</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {StreamBarcodeReader} from "vue-barcode-reader";
    import Product from "../entities/product";
    import apiClient from "../apiClient";
    import axios from "axios";

    export default {
        name: 'Scan',
        components: {
            StreamBarcodeReader
        },
        mounted() {
            // this.loadProductInfo(4060800302533);
        },
        methods: {
            onAddProduct() {
                let product = new Product();
                product.name = this.product.product_name;
                product.imageUrl = this.product.image_url;
                product.quantity = this.product.quantity;
                product.count = this.quantity;
                product.expireDate = this.expireDate;

                apiClient.post('/api/fridge-store', product)
                    .then(response => {
                        if (response.status === 200) {
                            this.$router.push('/foods');
                        }
                    });
            },
            onDecode(result) {
                if (result.length === 13) {
                    this.loadProductInfo(result);
                }
            },
            loadProductInfo(code) {
                axios.get(`https://world.openfoodfacts.org/api/v0/product/${code}.json`)
                    .then(response => {
                        const result = response.data;
                        this.product = result.product;
                    })
                    .then(() => this.loading = false);

            }
        },
        data() {
            return {
                expireDate: new Date().toISOString().substr(0, 10),
                quantity: 0,
                product: {},
                loading: true,
            }
        }
    }
</script>

<style lang="less">
    .scan {
        .content {
            margin-top: 1rem;
        }

        .scanner-container {
            video {
                height: 20rem;
                width: 100%;
                background: #000000;
            }
        }
    }
</style>