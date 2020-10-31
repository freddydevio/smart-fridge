<template>
    <div class="navigation">
        <router-link to="/foods" class="navigation-item">
            <div class="active-menu-entry" :class="{'active': $route.currentRoute === 'foods'}"></div>
            <img src="../assets/dish.svg"/>
            <span class="text">Lebensmittel</span>
        </router-link>
        <router-link to="/scan" class="navigation-item">
            <div class="active-menu-entry" :class="{'active': $route.currentRoute === 'scan'}"></div>
            <img src="@/assets/bar-code.png"/>
            <span class="text">Scannen</span>
        </router-link>
        <router-link to="/" class="navigation-item">
            <div class="logo">
                <span class="text">smart</span>
                <span class="text">fridge</span>
            </div>
        </router-link>
        <router-link to="/recipes" class="navigation-item">
            <div class="active-menu-entry" :class="{'active': $route.currentRoute === 'recipes'}"></div>
            <img src="../assets/recipe-book.svg"/>
            <span class="text">Rezepte</span>
        </router-link>
        <router-link to="#" class="navigation-item">
            <div class="active-menu-entry" :class="{'active': $route.currentRoute === 'shopping-list'}"></div>
            <img src="../assets/shopping-list.png"/>
            <span class="text">Einkaufsliste</span>
        </router-link>
    </div>
</template>

<script>
    export default {
        name: 'Navigation',
    }
</script>

<style lang="less">
    .navigation {
        display: flex;
        flex-direction: row;
        position: fixed;
        bottom: 0;
        justify-content: center;
        align-items: center;
        padding: 0 .75rem 2rem 0.75rem;
        width: 100%;
        background: white;
        box-shadow: 0 0 15rem 1rem rgba(0, 0, 0, .15);

        .navigation-item {
            margin: 0 .5rem;
            text-decoration: none;
            color: black;
            display: flex;
            flex-direction: column;
            align-items: center;

            .active-menu-entry {
                width: 100%;
                height: .25rem;
                margin-bottom: .5rem;
                border-radius: 2rem;
            }

            &.router-link-active {
                .active-menu-entry {
                    background: #1D3557;
                }
            }

            .text {
                font-size: .7rem;
            }

            img {
                width: 26px;
                height: auto;
            }

            .logo {
                display: flex;
                flex-direction: column;
                line-height: 1;
                margin: 0 .7rem;

                .text {
                    font-size: 1rem;
                    font-family: 'Sansita Swashed', cursive;
                }
            }
        }
    }
</style>
